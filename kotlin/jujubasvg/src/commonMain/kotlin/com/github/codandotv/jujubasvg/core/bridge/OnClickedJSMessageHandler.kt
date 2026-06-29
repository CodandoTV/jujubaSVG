package com.github.codandotv.jujubasvg.core.bridge

import com.github.codandotv.jujubasvg.model.NodeCoordinate
import com.github.codandotv.jujubasvg.model.NodeInfo
import com.multiplatform.webview.jsbridge.IJsMessageHandler
import com.multiplatform.webview.jsbridge.JsMessage
import com.multiplatform.webview.web.WebViewNavigator
import kotlinx.serialization.json.Json

internal class OnClickedJSMessageHandler(
    private val onElementClick: (NodeInfo) -> Unit
) : IJsMessageHandler {
    override fun methodName(): String {
        return METHOD_NAME
    }

    override fun handle(
        message: JsMessage,
        navigator: WebViewNavigator?,
        callback: (String) -> Unit
    ) {
        runCatching {
            val nodeClicked = Json.decodeFromString<NodeClicked>(
                message.params
            )
            onElementClick(
                NodeInfo(
                    id = nodeClicked.id,
                    coordinate = NodeCoordinate(
                        x = nodeClicked.x,
                        y = nodeClicked.x
                    )
                )
            )
        }
    }

    internal companion object {
        const val METHOD_NAME = "onElementClicked"
    }
}
