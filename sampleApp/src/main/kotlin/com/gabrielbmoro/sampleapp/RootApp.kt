package com.gabrielbmoro.sampleapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.gabrielbmoro.jujubasvg.core.JujubaSVG
import com.gabrielbmoro.jujubasvg.core.commander.Command
import com.gabrielbmoro.jujubasvg.core.rememberJujubaCommander
import com.gabrielbmoro.sample.R
import com.gabrielbmoro.sampleapp.components.SelectionSheet
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
internal fun RootApp() {
    val options = listOf(
        "Change element background color",
        "Change root SVG background color",
        "Add rounded image",
        "Remove element"
    )
    var styleSheetSelectedOption by remember { mutableIntStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        val jujubaCommander = rememberJujubaCommander()
        val coroutineScope = rememberCoroutineScope()

        JujubaSVG(
            svgRawRes = R.raw.brazil,
            onElementClick = { nodeInfo ->
                println("NodeInfo $nodeInfo")
                coroutineScope.launch {
                    jujubaCommander.execute(
                        when (styleSheetSelectedOption) {
                            SelectSampleOptions.UPDATE_BACKGROUND_COLOR_OPTION -> Command.UpdateBackgroundColor(
                                nodeInfo.id,
                                getRainbowColor()
                            )

                            SelectSampleOptions.UPDATE_ROOT_BACKGROUND_COLOR_OPTION -> Command
                                .UpdateRootBackgroundColor(
                                    getRainbowColor()
                                )

                            SelectSampleOptions.ADD_ROUNDED_IMAGE_OPTION -> Command.AddRoundedImage(
                                elementId = nodeInfo.id,
                                imageId = "nasa",
                                imageUrl = "https://i.imgur.com/LQIsf.jpeg",
                                widthInPx = 100,
                                heightInPx = 100,
                                coordinate = nodeInfo.coordinate,
                            )

                            SelectSampleOptions.REMOVE_NODE_OPTION -> Command.RemoveNode(nodeInfo.id)
                            else -> Command.UpdateBackgroundColor(nodeInfo.id, getRainbowColor())
                        }

                    )
                }
            },
            commander = jujubaCommander,
            backgroundColor = Color(BACKGROUND_COLOR),
            modifier = Modifier.fillMaxSize()
        )

        SelectionSheet(
            options = options,
            onChangeOption = { styleSheetSelectedOption = it },
            selected = styleSheetSelectedOption
        )
    }
}


/**
 * Returns a random core Color from the rainbow.
 */
@Suppress("MagicNumber")
private fun getRainbowColor(): Color {
    val colors = listOf(
        Color(0xFFFF0000), // Red
        Color(0xFFFF7F00), // Orange
        Color(0xFFFFFF00), // Yellow
        Color(0xFF00FF00), // Green
        Color(0xFF0000FF), // Blue
        Color(0xFF4B0082), // Indigo
        Color(0xFF8B00FF)  // Violet
    )
    val randomIndex = Random.nextInt(0, 6)
    return colors[randomIndex % colors.size]
}

private object SelectSampleOptions {
    const val UPDATE_BACKGROUND_COLOR_OPTION = 0
    const val UPDATE_ROOT_BACKGROUND_COLOR_OPTION = 1
    const val ADD_ROUNDED_IMAGE_OPTION = 2
    const val REMOVE_NODE_OPTION = 3
}

private const val BACKGROUND_COLOR = 0xffffb700
