package com.gabrielbmoro.jujubasvg.core.commander

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

public class JujubaCommander {

    private val _state = MutableStateFlow("")
    public val state: StateFlow<String> = _state

    public suspend fun execute(command: Command) {
        when (command) {
            is Command.UpdateBackgroundColor -> {
                _state.emit(
                    "updateBackgroundColor(\'${command.id}\',\'${command.colorInHex}\');"
                )
            }

            is Command.UpdateStrokeColor -> {
                _state.emit(
                    "updateStrokeColor(\'${command.id}\',\'${command.colorInHex}\');"
                )
            }

            is Command.UpdateStrokeWidth -> {
                _state.emit(
                    "updateStrokeWidth(\'${command.id}\',\'${command.widthInPx}\');"
                )
            }

            is Command.RemoveNode -> {
                _state.emit(
                    "removeNode(\'${command.id}\');"
                )
            }

            is Command.UpdateRootBackgroundColor -> {
                _state.emit(
                    "updateRootBackgroundColor(\'${command.colorInHex}\');"
                )
            }
        }
    }
}
