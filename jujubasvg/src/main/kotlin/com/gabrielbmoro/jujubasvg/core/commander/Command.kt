package com.gabrielbmoro.jujubasvg.core.commander

import com.gabrielbmoro.jujubasvg.model.NodeCoordinate

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

    public data class UpdateRootBackgroundColor(
        val colorInHex: String,
    ) : Command()

    public data class AddRoundedImage(
        val imageId: String,
        val imageUrl: String,
        val widthInPx: Int,
        val heightInPx: Int,
        val coordinate: NodeCoordinate,
    ) : Command()
}