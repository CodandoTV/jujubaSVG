package com.gabrielbmoro.jujubasvg.core.commander

public sealed class Command {
    public data class UpdateBackgroundColor(
        val id: String,
        val colorInHex: String,
    ) : Command()

    public data class UpdateStrokeColor(
        val id: String,
        val colorInHex: String,
    ) : Command()

    public data class UpdateStrokeWidth(
        val id: String,
        val widthInPx: Int,
    ) : Command()

    public data class RemoveNode(
        val id: String,
    ) : Command()
}