package com.github.codandotv.jujubasvg.sampleapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.codandotv.jujubasvg.core.JujubaSVG
import com.github.codandotv.jujubasvg.core.commander.Command
import com.github.codandotv.jujubasvg.core.rememberJujubaCommander
import com.github.codandotv.jujubasvg.sampleapp.components.SelectionCommandType
import com.github.codandotv.jujubasvg.sampleapp.components.SelectionSheet
import com.github.codandotv.jujubasvg.sampleapp.resources.Res
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("LongMethod")
@Composable
public fun RootApp() {
    var styleSheetSelectedOption by remember { mutableStateOf(SelectionCommandType.CHANGE_BACKGROUND_COLOR) }

    var svgText by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        withContext(Dispatchers.Default) {
            svgText = Res.readBytes("files/brazil.svg").decodeToString()
        }
    }

    BottomSheetScaffold(
        modifier = Modifier.fillMaxSize(),
        sheetPeekHeight = 145.dp,
        sheetContent = {
            SelectionSheet(
                onChangeOption = { styleSheetSelectedOption = it },
                selectedCommandType = styleSheetSelectedOption,
            )
        }
    ) {
        if (svgText != null) {
            val jujubaCommander = rememberJujubaCommander()
            val coroutineScope = rememberCoroutineScope()

            JujubaSVG(
                svgText = svgText!!,
                onElementClick = { nodeInfo ->
                    println("NodeInfo $nodeInfo")
                    coroutineScope.launch {
                        jujubaCommander.execute(
                            when (styleSheetSelectedOption) {
                                SelectionCommandType.CHANGE_BACKGROUND_COLOR -> Command.UpdateBackgroundColor(
                                    nodeInfo.id,
                                    getRainbowColor()
                                )

                                SelectionCommandType.CHANGE_SVG_BACKGROUND_COLOR -> Command
                                    .UpdateRootBackgroundColor(
                                        getRainbowColor()
                                    )

                                SelectionCommandType.ADD_ROUNDED_IMAGE -> Command.AddRoundedImage(
                                    elementId = nodeInfo.id,
                                    imageId = "nasa",
                                    imageUrl = "https://i.imgur.com/LQIsf.jpeg",
                                    widthInPx = 100,
                                    heightInPx = 100,
                                    coordinate = nodeInfo.coordinate,
                                )

                                SelectionCommandType.REMOVE_ELEMENT -> Command.RemoveNode(nodeInfo.id)
                                SelectionCommandType.CUSTOM_COMMAND -> Command.CustomCommand(
                                    "updateBackgroundColor(\'${nodeInfo.id}\',\'#000000\');"
                                )
                            }

                        )
                    }
                },
                commander = jujubaCommander,
                backgroundColor = Color(BACKGROUND_COLOR),
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Suppress("MagicNumber")
private fun getRainbowColor(): Color {
    val colors = listOf(
        Color(0xFFFF0000),
        Color(0xFFFF7F00),
        Color(0xFFFFFF00),
        Color(0xFF00FF00),
        Color(0xFF0000FF),
        Color(0xFF4B0082),
        Color(0xFF8B00FF)
    )
    val randomIndex = Random.nextInt(0, 6)
    return colors[randomIndex % colors.size]
}

private const val BACKGROUND_COLOR = 0xffffb700
