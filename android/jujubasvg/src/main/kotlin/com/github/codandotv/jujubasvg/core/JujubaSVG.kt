package com.github.codandotv.jujubasvg.core

import android.annotation.SuppressLint
import android.util.Log
import androidx.annotation.RawRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalResources
import com.github.codandotv.jujubasvg.core.bridge.OnClickedJSMessageHandler
import com.github.codandotv.jujubasvg.core.commander.JujubaCommander
import com.github.codandotv.jujubasvg.core.ext.fileTextContent
import com.github.codandotv.jujubasvg.core.ext.fileTextLines
import com.github.codandotv.jujubasvg.model.NodeInfo
import com.github.gabrielbmoro.jujubasvg.R
import com.multiplatform.webview.jsbridge.WebViewJsBridge
import com.multiplatform.webview.web.rememberWebViewStateWithHTMLData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.text.StringBuilder

@Composable
public fun JujubaSVG(
    @RawRes svgRawRes: Int,
    commander: JujubaCommander,
    onElementClick: (NodeInfo) -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White
) {
    val resources = LocalResources.current
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

@Suppress("LongMethod")
@SuppressLint("SetJavaScriptEnabled")
@Composable
public fun JujubaSVG(
    svgText: String,
    commander: JujubaCommander,
    onElementClick: (NodeInfo) -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White
) {
    val resources = LocalResources.current

    var htmlCode by remember {
        mutableStateOf<String?>(null)
    }

    val jsMessageHandler = remember {
        OnClickedJSMessageHandler(
            onElementClick = onElementClick
        )
    }

    LaunchedEffect(Unit) {
        val jsCodeDeferred = async(Dispatchers.IO) {
            resources.openRawResource(R.raw.base_js).fileTextContent()
        }

        htmlCode = async(Dispatchers.IO) {
            val htmlBuilder = StringBuilder()

            resources.openRawResource(R.raw.jujuba).fileTextLines()
                .forEach { line ->
                    when (line) {
                        Const.SVG_CODE_SIGN -> {
                            htmlBuilder.append(svgText)
                        }

                        Const.JS_CODE_SIGN -> {
                            val jsCode = jsCodeDeferred.await()
                            htmlBuilder.append(jsCode)
                            htmlBuilder.append(Const.JS_CLICK_EVENT)
                        }

                        else -> {
                            htmlBuilder.append(line)
                        }
                    }
                }
            htmlBuilder.toString()
        }.await()
    }

    if (htmlCode != null) {
        val webViewState = rememberWebViewStateWithHTMLData(
            data = htmlCode!!
        )

        val jsBridge = remember {
            WebViewJsBridge()
                .apply {
                    register(jsMessageHandler)
                }
        }

        com.multiplatform.webview.web.WebView(
            state = webViewState,
            modifier = modifier,
            webViewJsBridge = jsBridge,
        )


        LaunchedEffect(Unit) {
            commander.command.collect { jsCommand ->
                jsBridge.webView?.evaluateJavaScript(jsCommand) {
                    Log.d(Const.TAG, "WebviewComponent: $jsCommand -> result: $it")
                }
            }
        }

        DisposableEffect(Unit) {
            onDispose {
                jsBridge.unregister(jsMessageHandler)
                webViewState.nativeWebView.destroy()
            }
        }
    }
}
