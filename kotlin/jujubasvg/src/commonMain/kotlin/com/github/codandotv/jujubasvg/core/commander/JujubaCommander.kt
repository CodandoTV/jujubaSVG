package com.github.codandotv.jujubasvg.core.commander

import com.github.codandotv.jujubasvg.core.ext.toHex
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

class JujubaCommander {

    private val _command = Channel<String>(Channel.UNLIMITED)
    val command: Flow<String> = _command.receiveAsFlow()

    suspend fun execute(vararg command: Command) {
        val commandJS = command.map {
            convertToJSCode(it)
        }.reduce { acc, s -> acc.plus("\n").plus(s) }

        _command.send(commandJS)
    }

    private fun convertToJSCode(command: Command): String {
        return when (command) {
            is Command.UpdateBackgroundColor -> {
                "updateBackgroundColor(\'${command.id}\',\'${command.color.toHex()}\');"
            }

            is Command.UpdateStrokeColor -> {
                "updateStrokeColor(\'${command.id}\',\'${command.color.toHex()}\');"
            }

            is Command.UpdateStrokeWidth -> {
                "updateStrokeWidth(\'${command.id}\',${command.widthInPx});"
            }

            is Command.RemoveNode -> {
                "removeNode(\'${command.id}\');"
            }

            is Command.UpdateRootBackgroundColor -> {
                "updateRootBackgroundColor(\'${command.color.toHex()}\');"
            }

            is Command.AddRoundedImage -> {
                "addRoundedImage(" +
                        "\'${command.elementId}\'," +
                        "\'${command.imageId}\'," +
                        "\'${command.imageUrl}\'," +
                        "\'${command.widthInPx}\'," +
                        "\'${command.heightInPx}\'," +
                        "\'${command.coordinate.x}\'," +
                        "\'${command.coordinate.y}\'" +
                        ");"
            }

            is Command.CustomCommand -> {
                command.jsCode
            }
        }
    }
}
