package com.gabrielbmoro.jujubasvg.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.gabrielbmoro.jujubasvg.core.commander.JujubaCommander

@Composable
public fun rememberJujubaCommander(): JujubaCommander {
    return remember { JujubaCommander() }
}
