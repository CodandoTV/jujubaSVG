package com.gabrielbmoro.jujubasvg.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gabrielbmoro.jujubasvg.core.commander.JujubaCommander

@Composable
expect fun JujubaSVG(
    svgText: String,
    commander: JujubaCommander,
    onElementClick: (String) -> Unit,
    modifier: Modifier
)