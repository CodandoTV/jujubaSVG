package com.gabrielbmoro.jujubasvg.core.commander

expect class JujubaCommander {

    suspend fun execute(command: Command)

}