package com.gabrielbmoro.jujubasvg.core.bridge

import android.webkit.JavascriptInterface

internal class JujubaSVGWebInterface(
    private val onElementClick: (String) -> Unit,
) {
    @JavascriptInterface
    fun onElementClicked(id: String) {
        onElementClick(id)
    }
}