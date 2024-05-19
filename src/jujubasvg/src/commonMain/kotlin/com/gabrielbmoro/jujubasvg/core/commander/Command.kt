package com.gabrielbmoro.jujubasvg.core.commander

sealed class Command {
    data class UpdateColor(
        val id: String,
        val colorInHex: String,
    ) : Command()
}