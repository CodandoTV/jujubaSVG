package com.gabrielbmoro.jujubasvg.core

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RawRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.text.StringBuilder

private const val BaseInterfaceName = "JujubaInterface"
private const val DefaultRootBackgroundColor = "#FFFFFF"

@Composable
public fun JujubaSVG(
    @RawRes svgRawRes: Int,
    commander: JujubaCommander,
    onElementClick: (NodeInfo) -> Unit,
    backgroundColorInHex: String = DefaultRootBackgroundColor,
    modifier: Modifier
) {
    val resources = LocalContext.current.resources
    val svgText = remember {
        resources.openRawResource(svgRawRes)
            .bufferedReader()
            .use { it.readText() }
    }

    JujubaSVG(
        svgText = svgText,
        commander = commander,
        onElementClick = onElementClick,
        modifier = modifier,
        backgroundColorInHex = backgroundColorInHex
    )
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
public fun JujubaSVG(
    svgText: String,
    commander: JujubaCommander,
    onElementClick: (NodeInfo) -> Unit,
    backgroundColorInHex: String = DefaultRootBackgroundColor,
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
                BaseInterfaceName
            )
        }
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
                            when {
                                line.contains("<!-- svg here -->") -> {
                                    htmlBuilder.append(svgText)
                                }

                                line.contains("// baseJS here") -> {
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
                    "text/html",
                    "utf-8",
                    ""
                )
            }

            webViewComponent
        }, modifier = modifier
    )

    LaunchedEffect(Unit) {
        commander.state.collectLatest { jsCommand ->
            webViewComponent.evaluateJavascript(jsCommand) { }
        }
    }

    LaunchedEffect(Unit) {
        webViewComponent.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                coroutineScope.launch {
                    commander.execute(Command.UpdateRootBackgroundColor(backgroundColorInHex))
                }
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            webViewComponent.removeJavascriptInterface(BaseInterfaceName)
        }
    }
}