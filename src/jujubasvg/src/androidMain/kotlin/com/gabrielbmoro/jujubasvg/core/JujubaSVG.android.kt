package com.gabrielbmoro.jujubasvg.core

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.gabrielbmoro.jujubasvg.core.bridge.JujubaSVGWebInterface
import com.gabrielbmoro.moviedbjujubasvg.R
import java.lang.StringBuilder

private const val BaseInterfaceName = "JujubaInterface"

@SuppressLint("SetJavaScriptEnabled")
@Composable
actual fun JujubaSVG(
    svgText: String,
    onElementClick: (String) -> Unit,
    modifier: Modifier
) {
    val context = LocalContext.current
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
            webViewComponent.settings.javaScriptEnabled = true
            webViewComponent.settings.useWideViewPort = true

            webViewComponent.loadDataWithBaseURL(
                null,
                htmlBuilder.toString(),
                "text/html",
                "utf-8",
                ""
            )

            webViewComponent
        }, modifier = modifier
    )

    DisposableEffect(Unit) {
        onDispose {
            webViewComponent.removeJavascriptInterface(BaseInterfaceName)
        }
    }
}