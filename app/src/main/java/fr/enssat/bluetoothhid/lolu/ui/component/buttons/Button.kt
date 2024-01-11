package fr.enssat.bluetoothhid.lolu.ui.component.buttons

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import fr.enssat.bluetoothhid.lolu.ui.theme.LoLuAppTheme
import fr.enssat.bluetoothhid.lolu.ui.theme.LoLuTheme

@Composable
fun LoLuButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    type: ButtonType
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = type.backgroundColor),
        onClick = onClick
    ) {
        Text(
            fontWeight = FontWeight.Bold,
            color = type.textColor,
            style = LoLuAppTheme.typography.p1,
            text = text
        )
    }
}


@Preview
@Composable
fun LoLuButtonPrev() {
    LoLuTheme{
        Column {
            LoLuButton(
                text = "Quitter",
                type = ButtonType.DIALOG_ABORT,
                onClick = { }
            )

            LoLuButton(
                text = "Valider",
                type = ButtonType.DIALOG_VALIDATE,
                onClick = { }
            )
        }
    }
}