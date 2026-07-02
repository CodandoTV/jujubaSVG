package com.github.codandotv.jujubasvg.core.ext

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

/**
 * Transforms a Compose Color into a hexadecimal String following the pattern "#ffffffff".
 */
@Suppress("MagicNumber")
internal fun Color.toHex(): String {
    val argb = this.toArgb()
    return buildString {
        append('#')
        append(((argb shr 16) and 0xFF).toString(16).padStart(2, '0')) // alpha
        append(((argb shr 8) and 0xFF).toString(16).padStart(2, '0'))  // red
        append((argb and 0xFF).toString(16).padStart(2, '0'))          // green
        append(((argb shr 24) and 0xFF).toString(16).padStart(2, '0')) // blue
    }
}
