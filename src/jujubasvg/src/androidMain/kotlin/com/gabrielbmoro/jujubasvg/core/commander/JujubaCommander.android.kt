package com.gabrielbmoro.jujubasvg.core.commander

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

actual class JujubaCommander {

    private val _state = MutableStateFlow("")
    val state: StateFlow<String> = _state

    actual suspend fun execute(command: Command) {
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
        }
    }

    private fun concat(elementId: String): String {
        return "document.getElementById(\'$SVG_ID\')." +
                "getElementById(\'$elementId\')."
    }

    companion object {
        private const val SVG_ID = "jujubaSVG"
    }
}