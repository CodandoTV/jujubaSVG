package com.gabrielbmoro.jujubasvg.core

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
actual fun JujubaSVG(data: String, modifier: Modifier) {
    AndroidView(factory = {
        WebView(it).apply {
            settings.javaScriptEnabled = true
            settings.useWideViewPort = true
            loadDataWithBaseURL(null, data, "text/html", "utf-8", "")
        }.apply {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }
    }, modifier = modifier)
}