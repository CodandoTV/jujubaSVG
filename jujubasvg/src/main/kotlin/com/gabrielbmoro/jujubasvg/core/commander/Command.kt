package com.gabrielbmoro.jujubasvg.core.commander

import com.gabrielbmoro.jujubasvg.model.NodeCoordinate

public sealed class Command {

    /**
     * Update the background color of a node.
     * @param id The id of the node.
     * @param colorInHex The color in hex.
     */
    public data class UpdateBackgroundColor(
        val id: String,
        val colorInHex: String,
    ) : Command()

    /**
     * Update the stroke color of a node.
     * @param id The id of the node.
     * @param colorInHex The color in hex.
     */
    public data class UpdateStrokeColor(
        val id: String,
        val colorInHex: String,
    ) : Command()

    /**
     * Update the stroke width of a node.
     * @param id The id of the node.
     * @param widthInPx The width in pixels.
     */
    public data class UpdateStrokeWidth(
        val id: String,
        val widthInPx: Int,
    ) : Command()

    /**
     * Remove a node.
     * @param id The id of the node.
     */
    public data class RemoveNode(
        val id: String,
    ) : Command()

    /**
     * Update the root background color.
     */
    public data class UpdateRootBackgroundColor(
        val colorInHex: String,
    ) : Command()

    /**
     * Add a rounded image into the same parent of the elementId.
     * @param elementId The id of the element, the rounded image will
     * be added at the same level of this elementId, so they will have the same parent node.
     * @param imageId The id of the new element to be added
     * @param imageUrl The url where the image is available
     * @param widthInPx The width of the image in pixels
     * @param heightInPx The height of the image in pixels
     * @param coordinate The coordinates where the image will be added
     * @param isElementRounded if trye, we use cx, and cy as coordinates, if false, we use x, y as coordinates.
     */
    public data class AddRoundedImage(
        val elementId: String,
        val imageId: String,
        val imageUrl: String,
        val widthInPx: Int,
        val heightInPx: Int,
        val coordinate: NodeCoordinate,
        val isElementRounded: Boolean = false
    ) : Command()
}