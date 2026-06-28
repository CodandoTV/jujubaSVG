package com.github.codandotv.jujubasvg.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Immutable
@Stable
data class NodeInfo(
    val id: String,
    val coordinate: NodeCoordinate,
)
