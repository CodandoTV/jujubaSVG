package com.github.codandotv.sampleapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

public val commandOptions: List<SelectionSheetItem> = listOf(
    SelectionSheetItem(
        text = "Change element background color",
        type = SelectionCommandType.CHANGE_BACKGROUND_COLOR
    ),
    SelectionSheetItem(
        text = "Change root SVG background color",
        type = SelectionCommandType.CHANGE_SVG_BACKGROUND_COLOR
    ),
    SelectionSheetItem(
        text = "Add rounded image",
        type = SelectionCommandType.ADD_ROUNDED_IMAGE
    ),
    SelectionSheetItem(
        text = "Remove element",
        type = SelectionCommandType.REMOVE_ELEMENT
    ),
    SelectionSheetItem(
        text = "[Custom Command] Apply black background",
        type = SelectionCommandType.CUSTOM_COMMAND
    ),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun SelectionSheet(
    onChangeOption: (SelectionCommandType) -> Unit,
    selectedCommandType: SelectionCommandType,
    modifier: Modifier = Modifier
) {
    BottomSheetScaffold(
        sheetPeekHeight = 145.dp,
        sheetContent = {
            LazyColumn(modifier = modifier.fillMaxWidth()) {
                item {
                    Text(
                        "Select a command",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                items(items = commandOptions) { option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onChangeOption(option.type) }
                            .padding(vertical = 8.dp)

                    ) {
                        RadioButton(
                            selected = selectedCommandType == option.type,
                            onClick = { }
                        )
                        Text(
                            text = option.text,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        }
    ) {}
}

public data class SelectionSheetItem(
    val text: String,
    val type: SelectionCommandType,
)

public enum class SelectionCommandType {
    CHANGE_BACKGROUND_COLOR,
    CHANGE_SVG_BACKGROUND_COLOR,
    ADD_ROUNDED_IMAGE,
    REMOVE_ELEMENT,
    CUSTOM_COMMAND
}
