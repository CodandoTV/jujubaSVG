package com.gabrielbmoro.jujubasvg.core

import android.annotation.SuppressLint
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RawRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.gabrielbmoro.jujubasvg.core.bridge.JujubaSVGWebInterface
import com.gabrielbmoro.jujubasvg.core.commander.Command
import com.gabrielbmoro.jujubasvg.core.commander.JujubaCommander
import com.gabrielbmoro.jujubasvg.core.ext.fileTextContent
import com.gabrielbmoro.jujubasvg.core.ext.fileTextLines
import com.gabrielbmoro.jujubasvg.model.NodeInfo
import com.github.gabrielbmoro.jujubasvg.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.text.StringBuilder

@Composable
public fun JujubaSVG(
    @RawRes svgRawRes: Int,
    commander: JujubaCommander,
    onElementClick: (NodeInfo) -> Unit,
    backgroundColor: Color = Color.White,
    modifier: Modifier
) {
    val resources = LocalContext.current.resources
    var svgText by remember {
        mutableStateOf<String?>(null)
    }

    svgText?.let {
        JujubaSVG(
            svgText = it,
            commander = commander,
            onElementClick = onElementClick,
            modifier = modifier,
            backgroundColor = backgroundColor
        )
    }

    LaunchedEffect(key1 = Unit) {
        launch(Dispatchers.IO) {
            svgText = resources.openRawResource(svgRawRes).fileTextContent()
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
public fun JujubaSVG(
    svgText: String,
    commander: JujubaCommander,
    onElementClick: (NodeInfo) -> Unit,
    backgroundColor: Color = Color.White,
    modifier: Modifier
) {
    val context = LocalContext.current

    val coroutineScope = rememberCoroutineScope()
    val webViewComponent = remember {
        WebView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            addJavascriptInterface(
                JujubaSVGWebInterface(
                    onElementClick = onElementClick
                ),
                Const.BASE_INTERFACE_NAME
            )
        }
    }
    var isWebViewReady by remember {
        mutableStateOf(false)
    }

    AndroidView(
        factory = { _ ->
            coroutineScope.launch {
                val jsCodeDeferred = coroutineScope.async(Dispatchers.IO) {
                    context.resources.openRawResource(R.raw.base_js).fileTextContent()
                }

                val htmlCode = coroutineScope.async(Dispatchers.IO) {
                    val htmlBuilder = StringBuilder()

                    context.resources.openRawResource(R.raw.jujuba).fileTextLines()
                        .forEach { line ->
                            when (line) {
                                Const.SVG_CODE_SIGN -> {
                                    htmlBuilder.append(svgText)
                                }

                                Const.JS_CODE_SIGN -> {
                                    val jsCode = jsCodeDeferred.await()
                                    htmlBuilder.append(jsCode)
                                }

                                else -> {
                                    htmlBuilder.append(line)
                                }
                            }
                        }
                    htmlBuilder.toString()
                }.await()

                webViewComponent.settings.javaScriptEnabled = true
                webViewComponent.settings.useWideViewPort = true
                webViewComponent.loadDataWithBaseURL(
                    null,
                    htmlCode,
                    Const.MIME_TYPE,
                    Const.ENCONDING,
                    ""
                )
                Log.d(Const.TAG, "WebviewComponent: Initialized...")
            }

            webViewComponent
        }, modifier = modifier
    )

    LaunchedEffect(isWebViewReady) {
        if (isWebViewReady) {
            commander.command.collect { jsCommand ->
                webViewComponent.evaluateJavascript(jsCommand) {
                    Log.d(Const.TAG, "WebviewComponent: $jsCommand -> result: $it")
                }
            }
        }
    }

    LaunchedEffect(key1 = isWebViewReady) {
        if (isWebViewReady) {
            commander.execute(
                Command.UpdateRootBackgroundColor(
                    backgroundColor
                )
            )
        }
    }

    LaunchedEffect(Unit) {
        webViewComponent.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                isWebViewReady = true

                Log.d(Const.TAG, "WebviewComponent: Ready to receive commands")
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            webViewComponent.removeJavascriptInterface(
                Const.BASE_INTERFACE_NAME
            )
            Log.d(Const.TAG, "WebviewComponent: Releasing resources...")
        }
    }
}