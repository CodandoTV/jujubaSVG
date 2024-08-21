package com.gabrielbmoro.jujubasvg.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Immutable
@Stable
public data class NodeInfo(
    val id: String,
    val coordinate: NodeCoordinate,
)
