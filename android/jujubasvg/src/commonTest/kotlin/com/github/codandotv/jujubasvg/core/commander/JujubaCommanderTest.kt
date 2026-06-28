package com.github.codandotv.jujubasvg.core.commander

import androidx.compose.ui.graphics.Color
import com.github.codandotv.jujubasvg.model.NodeCoordinate
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class JujubaCommanderTest {

    @Test
    fun `given an update background color command when it is invoked then emit the right jsCommand`() =
        runTest {
            val jsCommand = "updateBackgroundColor('12','#0000ffff');"
            val commander = JujubaCommander()

            var result: String? = null
            val job = launch {
                commander.command.collect {
                    result = it
                }
            }

            commander.execute(Command.UpdateBackgroundColor("12", Color.Blue))

            job.cancel()
            assertEquals(jsCommand, result)
        }
}
