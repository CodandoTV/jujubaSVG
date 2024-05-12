package com.gabrielbmoro.jujubasvg.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun JujubaSVG(svgText: String, onElementClick: (String) -> Unit, modifier: Modifier)