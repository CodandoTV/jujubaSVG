package com.gabrielbmoro.jujubasvg.core.commander

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class JujubaCommanderTest {

    @Test
    fun `given an update background color command when it is invoked then emit the right jsCommand`() =
        runTest {
            val jsCommand =
                "document.getElementById('jujubaSVG').getElementById('12').style.fill='#0000';"
            val commander = JujubaCommander()

            commander.execute(Command.UpdateBackgroundColor("12", "#0000"))
            advanceUntilIdle()

            val result = commander.state.value
            assertEquals(jsCommand, result)
        }

    @Test
    fun `given an update stroke color command when it is invoked then emit the right jsCommand`() =
        runTest {
            val jsCommand =
                "document.getElementById('jujubaSVG').getElementById('12').style.stroke='#0000';"
            val commander = JujubaCommander()

            commander.execute(Command.UpdateStrokeColor("12", "#0000"))
            advanceUntilIdle()

            val result = commander.state.value
            assertEquals(jsCommand, result)
        }

    @Test
    fun `given an update stroke width command when it is invoked then emit the right jsCommand`() =
        runTest {
            val jsCommand =
                "document.getElementById('jujubaSVG').getElementById('12').style.strokeWidth='45';"
            val commander = JujubaCommander()

            commander.execute(Command.UpdateStrokeWidth("12", 45))
            advanceUntilIdle()

            val result = commander.state.value
            assertEquals(jsCommand, result)
        }

    @Test
    fun `given a remove node command when it is invoked then emit the right jsCommand`() =
        runTest {
            val jsCommand =
                "document.getElementById('jujubaSVG').getElementById('12').remove();"
            val commander = JujubaCommander()

            commander.execute(Command.RemoveNode("12"))
            advanceUntilIdle()

            val result = commander.state.value
            assertEquals(jsCommand, result)
        }

    @Test
    fun `given a change root background color command when it is invoked then emit the right jsCommand`() =
        runTest {
            val jsCommand = "document.body.style.backgroundColor='#000000';"
            val commander = JujubaCommander()

            commander.execute(Command.UpdateRootBackgroundColor("#000000"))
            advanceUntilIdle()

            val result = commander.state.value
            assertEquals(jsCommand, result)
        }
}