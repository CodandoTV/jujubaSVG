package com.github.codandotv.jujubasvg.core.bridge

import android.webkit.JavascriptInterface
import com.github.codandotv.jujubasvg.model.NodeCoordinate
import com.github.codandotv.jujubasvg.model.NodeInfo

internal class JujubaSVGWebInterface(
    private val onElementClick: (NodeInfo) -> Unit,
) {
    @JavascriptInterface
    fun onElementClicked(id: String, x: Float, y: Float) {
        onElementClick(
            NodeInfo(
                id = id,
                coordinate = NodeCoordinate(
                    x = x,
                    y = y
                )
            )
        )
    }
}
