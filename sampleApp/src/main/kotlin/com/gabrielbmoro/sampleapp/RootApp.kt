package com.gabrielbmoro.sampleapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gabrielbmoro.jujubasvg.core.JujubaSVG
import com.gabrielbmoro.jujubasvg.core.commander.Command
import com.gabrielbmoro.jujubasvg.core.rememberJujubaCommander
import com.gabrielbmoro.sample.R
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
internal fun RootApp() {
    val options = listOf(
        "Change element color",
        "Change root SVG color",
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
                            0 -> Command.UpdateBackgroundColor(nodeInfo.id, getRainbowColor())
                            1 -> Command.UpdateRootBackgroundColor(getRainbowColor())
                            2 -> Command.AddRoundedImage(
                                elementId = nodeInfo.id,
                                imageId = "nasa",
                                imageUrl = "https://i.imgur.com/LQIsf.jpeg",
                                widthInPx = 100,
                                heightInPx = 100,
                                coordinate = nodeInfo.coordinate,
                            )
                            3 -> Command.RemoveNode(nodeInfo.id)
                            else -> Command.UpdateBackgroundColor(nodeInfo.id, getRainbowColor())
                        }

                    )
                }
            },
            commander = jujubaCommander,
            backgroundColor = Color(0xffffb700),
            modifier = Modifier.fillMaxSize()
        )


        SelectionSheet(
            options = options,
            onChangeOption = { styleSheetSelectedOption = it },
            selected = styleSheetSelectedOption
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SelectionSheet(options: List<String>, onChangeOption: (Int) -> Unit, selected: Int) {
    BottomSheetScaffold(
        sheetPeekHeight = 145.dp,
        sheetContent = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Select a command",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(16.dp)
                )
                options.forEachIndexed { index, option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onChangeOption(index) }
                            .padding(vertical = 8.dp)

                    ) {
                        RadioButton(
                            selected = index == selected,
                            onClick = { }
                        )
                        Text(
                            text = option,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        }
    ) {}
}

/**
 * Returns a random core Color from the rainbow.
 */

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