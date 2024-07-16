package com.gabrielbmoro.jujubasvg.core.commander

import android.util.Log
import com.gabrielbmoro.jujubasvg.core.Const.TAG
import com.gabrielbmoro.jujubasvg.core.ext.toHex
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

public class JujubaCommander {

    private val _command = MutableSharedFlow<String>()
    public val command: SharedFlow<String> = _command.asSharedFlow()

    public suspend fun execute(vararg command: Command) {
        val commandJS = command.map {
            convertToJSCode(it)
        }.reduce { acc, s -> acc.plus("\n").plus(s) }

        Log.d(TAG, "execute: $commandJS")
        _command.emit(commandJS)
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
        }
    }
}
