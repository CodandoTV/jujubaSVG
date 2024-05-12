package com.gabrielbmoro.jujubasvg.core

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.gabrielbmoro.moviedbjujubasvg.R
import java.lang.StringBuilder

@SuppressLint("SetJavaScriptEnabled")
@Composable
actual fun JujubaSVG(svgText: String, modifier: Modifier) {
    AndroidView(
        factory = { context ->
            val htmlBuilder = StringBuilder()

            context.resources.openRawResource(R.raw.jujuba).use { inputStream ->
                inputStream.bufferedReader().use { bufferedReader ->
                    bufferedReader.readLines().forEach { line ->
                        if (line.contains("<!-- svg here -->")) {
                            htmlBuilder.append(svgText)
                        } else {
                            htmlBuilder.append(line)
                        }
                    }
                }
            }
            WebView(context).apply {
                settings.javaScriptEnabled = true
                settings.useWideViewPort = true

                loadDataWithBaseURL(
                    null,
                    htmlBuilder.toString(),
                    "text/html",
                    "utf-8",
                    ""
                )
            }.apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }
        }, modifier = modifier
    )
}