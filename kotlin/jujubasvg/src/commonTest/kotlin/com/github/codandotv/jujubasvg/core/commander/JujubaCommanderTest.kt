package com.github.codandotv.jujubasvg.core.commander

import androidx.compose.ui.graphics.Color
import com.github.codandotv.jujubasvg.model.NodeCoordinate
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.yield
import kotlin.test.Test
import kotlin.test.assertEquals

class JujubaCommanderTest {

    @Test
    fun updateBackgroundColor_emitsCorrectJS() = runTest {
        val commander = JujubaCommander()
        val emitted = mutableListOf<String>()
        val job = backgroundScope.launch {
            commander.command.collect { emitted.add(it) }
        }
        yield()

        commander.execute(Command.UpdateBackgroundColor(id = "n1", color = Color.White))
        yield()

        assertEquals("updateBackgroundColor('n1','#ffffffff');", emitted.single())
        job.cancel()
    }

    @Test
    fun updateStrokeColor_emitsCorrectJS() = runTest {
        val commander = JujubaCommander()
        val emitted = mutableListOf<String>()
        val job = backgroundScope.launch {
            commander.command.collect { emitted.add(it) }
        }
        yield()

        commander.execute(Command.UpdateStrokeColor(id = "n2", color = Color.Black))
        yield()

        assertEquals("updateStrokeColor('n2','#000000ff');", emitted.single())
        job.cancel()
    }

    @Test
    fun updateStrokeWidth_emitsCorrectJS() = runTest {
        val commander = JujubaCommander()
        val emitted = mutableListOf<String>()
        val job = backgroundScope.launch {
            commander.command.collect { emitted.add(it) }
        }
        yield()

        commander.execute(Command.UpdateStrokeWidth(id = "n3", widthInPx = 5))
        yield()

        assertEquals("updateStrokeWidth('n3',5);", emitted.single())
        job.cancel()
    }

    @Test
    fun removeNode_emitsCorrectJS() = runTest {
        val commander = JujubaCommander()
        val emitted = mutableListOf<String>()
        val job = backgroundScope.launch {
            commander.command.collect { emitted.add(it) }
        }
        yield()

        commander.execute(Command.RemoveNode(id = "n4"))
        yield()

        assertEquals("removeNode('n4');", emitted.single())
        job.cancel()
    }

    @Test
    fun updateRootBackgroundColor_emitsCorrectJS() = runTest {
        val commander = JujubaCommander()
        val emitted = mutableListOf<String>()
        val job = backgroundScope.launch {
            commander.command.collect { emitted.add(it) }
        }
        yield()

        commander.execute(Command.UpdateRootBackgroundColor(color = Color.White))
        yield()

        assertEquals("updateRootBackgroundColor('#ffffffff');", emitted.single())
        job.cancel()
    }

    @Test
    fun customCommand_emitsRawJS() = runTest {
        val commander = JujubaCommander()
        val emitted = mutableListOf<String>()
        val job = backgroundScope.launch {
            commander.command.collect { emitted.add(it) }
        }
        yield()

        val jsCode = "console.log('hello world');"
        commander.execute(Command.CustomCommand(jsCode = jsCode))
        yield()

        assertEquals(jsCode, emitted.single())
        job.cancel()
    }

    @Test
    fun addRoundedImage_emitsCorrectJS() = runTest {
        val commander = JujubaCommander()
        val emitted = mutableListOf<String>()
        val job = backgroundScope.launch {
            commander.command.collect { emitted.add(it) }
        }
        yield()

        commander.execute(
            Command.AddRoundedImage(
                elementId = "container",
                imageId = "img1",
                imageUrl = "https://example.com/image.png",
                widthInPx = 100,
                heightInPx = 200,
                coordinate = NodeCoordinate(x = 10.5f, y = 20.75f),
            )
        )
        yield()

        assertEquals(
            "addRoundedImage(" +
                    "'container'," +
                    "'img1'," +
                    "'https://example.com/image.png'," +
                    "'100'," +
                    "'200'," +
                    "'10.5'," +
                    "'20.75'" +
                    ");",
            emitted.single(),
        )
        job.cancel()
    }

    @Test
    fun execute_multipleCommands_concatenatesWithNewlines() = runTest {
        val commander = JujubaCommander()
        val emitted = mutableListOf<String>()
        val job = backgroundScope.launch {
            commander.command.collect { emitted.add(it) }
        }
        yield()

        commander.execute(
            Command.RemoveNode(id = "n1"),
            Command.RemoveNode(id = "n2"),
        )
        yield()

        assertEquals(
            "removeNode('n1');\nremoveNode('n2');",
            emitted.single(),
        )
        job.cancel()
    }
}
