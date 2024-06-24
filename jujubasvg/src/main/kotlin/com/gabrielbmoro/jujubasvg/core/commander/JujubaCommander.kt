package com.gabrielbmoro.jujubasvg.core.commander

import android.util.Log
import com.gabrielbmoro.jujubasvg.core.Const.TAG
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

public class JujubaCommander {

    private val _state = MutableStateFlow("")
    public val state: StateFlow<String> = _state

    public suspend fun execute(vararg command: Command) {
        val commandJS = command.map {
            convertToJSCode(it)
        }.reduce { acc, s -> acc.plus("\n").plus(s) }

        Log.d(TAG, "execute: $commandJS")
        _state.emit(commandJS)
    }

    private fun convertToJSCode(command: Command): String {
        return when (command) {
            is Command.UpdateBackgroundColor -> {
                "updateBackgroundColor(\'${command.id}\',\'${command.colorInHex}\');"
            }

            is Command.UpdateStrokeColor -> {
                "updateStrokeColor(\'${command.id}\',\'${command.colorInHex}\');"
            }

            is Command.UpdateStrokeWidth -> {
                "updateStrokeWidth(\'${command.id}\',${command.widthInPx});"
            }

            is Command.RemoveNode -> {
                "removeNode(\'${command.id}\');"
            }

            is Command.UpdateRootBackgroundColor -> {
                "updateRootBackgroundColor(\'${command.colorInHex}\');"
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
