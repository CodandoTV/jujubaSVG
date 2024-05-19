package com.gabrielbmoro.jujubasvg.core.commander

sealed class Command {
    data class UpdateBackgroundColor(
        val id: String,
        val colorInHex: String,
    ) : Command()

    data class UpdateStrokeColor(
        val id: String,
        val colorInHex: String,
    ) : Command()

    data class UpdateStrokeWidth(
        val id: String,
        val widthInPx: Int,
    ) : Command()
}