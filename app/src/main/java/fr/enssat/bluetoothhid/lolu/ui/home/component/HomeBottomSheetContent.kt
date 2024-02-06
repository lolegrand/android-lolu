package fr.enssat.bluetoothhid.lolu.ui.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.enssat.bluetoothhid.data.vo.HID
import fr.enssat.bluetoothhid.lolu.R
import fr.enssat.bluetoothhid.lolu.ui.component.buttons.ButtonType
import fr.enssat.bluetoothhid.lolu.ui.component.buttons.LoLuButton
import fr.enssat.bluetoothhid.lolu.ui.theme.LoLuAppTheme
import fr.enssat.bluetoothhid.lolu.ui.theme.LoLuTheme

@Composable
fun HomeBottomSheetContent(
    onValidateCreation: (HID) -> Unit
) {
    // State
    var textFieldValue by remember {
        mutableStateOf(TextFieldValue(""))
    }

    var error by remember {
        mutableStateOf<String?>(null)
    }

    // View
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(id = R.string.home_bottom_sheet_title),
            style = LoLuAppTheme.typography.h1,
            textAlign = TextAlign.Center,
            color = LoLuAppTheme.colors.primary
        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            supportingText = {
                Text(
                    text = error ?: "" ,
                    color = LoLuAppTheme.colors.error,
                    fontWeight = FontWeight.Bold
                )
            },
            label = {
                Text(
                    text = stringResource(id = R.string.home_bottom_sheet_placeholder)
                )
            },
            modifier = Modifier.fillMaxWidth(),
            value = textFieldValue,
            onValueChange = {
                textFieldValue = it
            },
            colors = if (error == null) {
                TextFieldDefaults.colors(
                    unfocusedContainerColor = LoLuAppTheme.colors.background,
                    focusedContainerColor = LoLuAppTheme.colors.background
                )
            } else {
                TextFieldDefaults.colors(
                    unfocusedContainerColor = LoLuAppTheme.colors.background,
                    focusedContainerColor = LoLuAppTheme.colors.background,
                    unfocusedIndicatorColor = LoLuAppTheme.colors.error
                )
            }
        )

        Spacer(modifier = Modifier.height(30.dp))

        val errorMessage = stringResource(id = R.string.home_bottom_sheet_error_msg)
        LoLuButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.home_bottom_sheet_validate),
            onClick = {
                if (textFieldValue.text.isBlank()) {
                    error = errorMessage
                } else {
                    onValidateCreation(HID(textFieldValue.text))
                }
            },
            type = ButtonType.PRIMARY
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeBottomSheetContentPreview() {
    LoLuTheme {
        HomeBottomSheetContent({})
    }
}