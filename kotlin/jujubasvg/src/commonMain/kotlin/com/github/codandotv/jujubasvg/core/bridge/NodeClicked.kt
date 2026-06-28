package com.github.codandotv.jujubasvg.core.bridge

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class NodeClicked(
    @SerialName("paramId")
    val id: String,

    @SerialName("paramX")
    val x: Float,

    @SerialName("paramY")
    val y: Float,
)