package com.gabrielbmoro.jujubasvg.core.ext

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

/**
 * Transform a Compose color to hexadecimal following this pattern:'#ffffff.
 * @param id The id of the node.
 * @param color The color in hex.
 */
@OptIn(ExperimentalStdlibApi::class)
public fun Color.toHex(): String {
    return "#" + this
        .toArgb()
        .toHexString()
        .substring(2)
}
