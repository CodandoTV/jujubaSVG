package com.github.codandotv.jujubasvg.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import co.touchlab.kermit.Logger
import com.github.codandotv.jujubasvg.core.bridge.OnClickedJSMessageHandler
import com.github.codandotv.jujubasvg.core.commander.Command
import com.github.codandotv.jujubasvg.core.commander.JujubaCommander
import com.github.codandotv.jujubasvg.core.ext.fileTextContent
import com.github.codandotv.jujubasvg.core.ext.fileTextLines
import com.github.codandotv.jujubasvg.model.NodeInfo
import com.github.codandotv.jujubasvg.resources.Res
import com.multiplatform.webview.jsbridge.WebViewJsBridge
import com.multiplatform.webview.web.rememberWebViewStateWithHTMLData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlin.text.StringBuilder

@Suppress("LongMethod")
@Composable
fun JujubaSVG(
    svgText: String,
    commander: JujubaCommander,
    onElementClick: (NodeInfo) -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White
) {
    var htmlCode by remember {
        mutableStateOf<String?>(null)
    }

    val jsMessageHandler = remember {
        OnClickedJSMessageHandler(
            onElementClick = onElementClick
        )
    }

    var readyToReceiveFirstCommand by remember {
        mutableStateOf(false)
    }

    var backgroundColorApplied by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        val jsCodeDeferred = async(Dispatchers.Default) {
            Res.readBytes("files/base_js.js").fileTextContent()
        }

        htmlCode = async(Dispatchers.Default) {
            val htmlBuilder = StringBuilder()
            Res.readBytes("files/jujuba.html")
                .fileTextLines()
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
            commander.command
                .onStart {
                    readyToReceiveFirstCommand = true
                }
                .onEach { jsCommand ->
                    jsBridge.webView?.evaluateJavaScript(jsCommand) {
                        Logger.d {
                            "WebviewComponent: $jsCommand -> result: $it"
                        }
                    }
                }
                .launchIn(this)
        }

        LaunchedEffect(readyToReceiveFirstCommand, webViewState.isLoading) {
            val isReady = readyToReceiveFirstCommand && webViewState.isLoading.not()
            if (isReady && backgroundColorApplied.not()) {
                commander.execute(
                    Command.UpdateRootBackgroundColor(color = backgroundColor)
                )
                backgroundColorApplied = true
            }
        }

        DisposableEffect(Unit) {
            onDispose {
                jsBridge.unregister(jsMessageHandler)
            }
        }
    }
}
