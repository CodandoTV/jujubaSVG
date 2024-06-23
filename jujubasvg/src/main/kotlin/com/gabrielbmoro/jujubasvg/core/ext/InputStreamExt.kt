package com.gabrielbmoro.jujubasvg.core.ext

import java.io.InputStream

internal fun InputStream.fileTextContent(): String {
    val content = StringBuilder()

    use { inputStream ->
        inputStream.bufferedReader().use { bufferReader ->
            bufferReader.readLines().forEach { line ->
                content.append(line)
            }
        }
    }

    return content.toString()
}

internal fun InputStream.fileTextLines(): List<String> {
    val lines = mutableListOf<String>()

    use { inputStream ->
        inputStream.bufferedReader().use { bufferReader ->
            bufferReader.readLines().forEach { line ->
                lines.add(line)
            }
        }
    }

    return lines
}