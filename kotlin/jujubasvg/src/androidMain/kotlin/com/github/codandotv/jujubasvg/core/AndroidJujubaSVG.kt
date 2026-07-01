package com.github.codandotv.jujubasvg.core

import androidx.annotation.RawRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalResources
import com.github.codandotv.jujubasvg.core.commander.JujubaCommander
import com.github.codandotv.jujubasvg.model.NodeInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStream

internal fun InputStream.fileTextContent(): String {
    val content = StringBuilder()

    use { inputStream ->
        inputStream.bufferedReader().use { bufferReader ->
            bufferReader.readLines().forEach { line ->
                content.append(line.plus("\n"))
            }
        }
    }

    return content.toString()
}

@Composable
fun JujubaSVG(
    @RawRes svgRawRes: Int,
    commander: JujubaCommander,
    onElementClick: (NodeInfo) -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White
) {
    var svgText: String? by remember {
        mutableStateOf(null)
    }
    val resources = LocalResources.current

    if (svgText != null) {
        JujubaSVG(
            svgText = svgText!!,
            commander = commander,
            onElementClick = onElementClick,
            modifier = modifier,
            backgroundColor = backgroundColor,
        )
    }

    LaunchedEffect(key1 = Unit) {
        launch(Dispatchers.IO) {
            svgText = resources.openRawResource(svgRawRes).fileTextContent()
        }
    }
}
