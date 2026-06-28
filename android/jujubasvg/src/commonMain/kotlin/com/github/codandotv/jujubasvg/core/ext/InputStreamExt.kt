package com.github.codandotv.jujubasvg.core.ext

internal fun ByteArray.fileTextContent(): String {
    return decodeToString()
}

internal fun ByteArray.fileTextLines(): List<String> {
   return decodeToString().lines()
}
