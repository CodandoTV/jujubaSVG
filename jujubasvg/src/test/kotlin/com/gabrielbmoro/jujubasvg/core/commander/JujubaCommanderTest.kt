package com.gabrielbmoro.jujubasvg.core.commander

import com.gabrielbmoro.jujubasvg.model.NodeCoordinate
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
            val jsCommand = "updateBackgroundColor('12','#0000');"
            val commander = JujubaCommander()

            commander.execute(Command.UpdateBackgroundColor("12", "#0000"))
            advanceUntilIdle()

            val result = commander.state.value
            assertEquals(jsCommand, result)
        }

    @Test
    fun `given an update stroke color command when it is invoked then emit the right jsCommand`() =
        runTest {
            val jsCommand = "updateStrokeColor('12','#0000');"
            val commander = JujubaCommander()

            commander.execute(Command.UpdateStrokeColor("12", "#0000"))
            advanceUntilIdle()

            val result = commander.state.value
            assertEquals(jsCommand, result)
        }

    @Test
    fun `given an update stroke width command when it is invoked then emit the right jsCommand`() =
        runTest {
            val jsCommand = "updateStrokeWidth('12',45);"
            val commander = JujubaCommander()

            commander.execute(Command.UpdateStrokeWidth("12", 45))
            advanceUntilIdle()

            val result = commander.state.value
            assertEquals(jsCommand, result)
        }

    @Test
    fun `given a remove node command when it is invoked then emit the right jsCommand`() =
        runTest {
            val jsCommand = "removeNode('12');"
            val commander = JujubaCommander()

            commander.execute(Command.RemoveNode("12"))
            advanceUntilIdle()

            val result = commander.state.value
            assertEquals(jsCommand, result)
        }

    @Test
    fun `given a change root background color command when it is invoked then emit the right jsCommand`() =
        runTest {
            val jsCommand = "updateRootBackgroundColor('#000000');"
            val commander = JujubaCommander()

            commander.execute(Command.UpdateRootBackgroundColor("#000000"))
            advanceUntilIdle()

            val result = commander.state.value
            assertEquals(jsCommand, result)
        }

    @Test
    fun `given a addRoundedImage command when it is invoked then emit the right jsCommand`() =
        runTest {
            val jsCommand =
                "addRoundedImage('elementId','imageId','imageUrl','45','48','0.0','1.0');"
            val commander = JujubaCommander()

            commander.execute(
                Command.AddRoundedImage(
                    "elementId",
                    "imageId",
                    "imageUrl",
                    45,
                    48,
                    NodeCoordinate(
                        0f,
                        1f
                    ),
                )
            )
            advanceUntilIdle()

            val result = commander.state.value
            assertEquals(jsCommand, result)
        }
}