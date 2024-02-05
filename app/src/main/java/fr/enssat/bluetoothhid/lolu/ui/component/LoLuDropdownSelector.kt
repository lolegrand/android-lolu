package fr.enssat.bluetoothhid.lolu.ui.component

import androidx.compose.foundation.background
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import fr.enssat.bluetoothhid.lolu.ui.theme.LoLuAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoLuDropdownSelector(
    modifier: Modifier = Modifier,
    selectedItem: String,
    items: List<String>,
    onItemSelected: (String) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = { newValue ->
            expanded = newValue
        }
    ) {
        TextField(
            modifier = Modifier.menuAnchor(),
            readOnly = true,
            value = selectedItem,
            onValueChange = { onItemSelected(it) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                unfocusedIndicatorColor = LoLuAppTheme.colors.primary,
                unfocusedContainerColor = LoLuAppTheme.colors.background,
                focusedContainerColor = LoLuAppTheme.colors.background,
                focusedIndicatorColor = LoLuAppTheme.colors.primary,
            )
        )


        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
            modifier = Modifier.background(color = LoLuAppTheme.colors.nuance100)
        ) {
            items.forEach { name ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = name
                        )
                    },
                    onClick = {
                        onItemSelected(name)
                        expanded = false
                    },
                )
            }
        }
    }

}