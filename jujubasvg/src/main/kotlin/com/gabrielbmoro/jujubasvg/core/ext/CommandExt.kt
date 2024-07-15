package com.gabrielbmoro.jujubasvg.core.ext

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

/**
 * Transforms a Compose Color into a hexadecimal String following the pattern "#ffffffff".
 */
internal fun Color.toHex(): String {
    val argb = this.toArgb()
    return buildString {
        append('#')
        append(Integer.toHexString((argb shr 16) and 0xFF).padStart(2, '0')) // alpha
        append(Integer.toHexString((argb shr 8) and 0xFF).padStart(2, '0'))  // red
        append(Integer.toHexString(argb and 0xFF).padStart(2, '0'))          // green
        append(Integer.toHexString((argb shr 24) and 0xFF).padStart(2, '0')) // blue
    }
}

