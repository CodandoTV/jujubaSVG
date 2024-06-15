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
                    concat(command.id) + "style.fill=\'${command.colorInHex}\';"
                )
            }

            is Command.UpdateStrokeColor -> {
                _state.emit(
                    concat(command.id) + "style.stroke=\'${command.colorInHex}\';"
                )
            }

            is Command.UpdateStrokeWidth -> {
                _state.emit(
                    concat(command.id) + "style.strokeWidth=\'${command.widthInPx}\';"
                )
            }

            is Command.RemoveNode -> {
                _state.emit(
                    concat(command.id) + "remove();"
                )
            }

            is Command.UpdateRootBackgroundColor -> {
                _state.emit(
                    "document.body.style.backgroundColor=\'${command.colorInHex}\';"
                )
            }
        }
    }

    private fun concat(elementId: String): String {
        return "document.getElementById(\'$SVG_ID\')." +
                "getElementById(\'$elementId\')."
    }

    private companion object {
        private const val SVG_ID = "jujubaSVG"
    }
}