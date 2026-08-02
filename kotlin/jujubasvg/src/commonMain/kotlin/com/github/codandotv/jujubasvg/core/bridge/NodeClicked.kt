package com.github.codandotv.jujubasvg.core.bridge

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class NodeClicked(
    @SerialName("id")
    val id: String,

    @SerialName("elementX")
    val elementX: Float,

    @SerialName("elementY")
    val elementY: Float,

    @SerialName("cursorX")
    val cursorX: Float,

    @SerialName("cursorY")
    val cursorY: Float,
)
