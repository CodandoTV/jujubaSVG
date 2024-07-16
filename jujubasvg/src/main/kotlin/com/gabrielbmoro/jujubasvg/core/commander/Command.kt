package com.gabrielbmoro.jujubasvg.core.commander

import androidx.compose.ui.graphics.Color
import com.gabrielbmoro.jujubasvg.model.NodeCoordinate

public sealed class Command {

    /**
     * Update the background color of a node.
     * @param id The id of the node.
     * @param color The color for the background.
     */
    public data class UpdateBackgroundColor(
        val id: String,
        val color: Color,
    ) : Command()

    /**
     * Update the stroke color of a node.
     * @param id The id of the node.
     * @param color The color for the stroke.
     */
    public data class UpdateStrokeColor(
        val id: String,
        val color: Color,
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
        val color: Color,
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
     */
    public data class AddRoundedImage(
        val elementId: String,
        val imageId: String,
        val imageUrl: String,
        val widthInPx: Int,
        val heightInPx: Int,
        val coordinate: NodeCoordinate
    ) : Command()
}