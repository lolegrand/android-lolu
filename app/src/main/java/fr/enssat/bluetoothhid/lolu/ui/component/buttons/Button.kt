package fr.enssat.bluetoothhid.lolu.ui.component.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.enssat.bluetoothhid.lolu.ui.theme.LoLuAppTheme
import fr.enssat.bluetoothhid.lolu.ui.theme.LoLuTheme

@Composable
fun LoLuButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    type: ButtonType,
    trailing: (@Composable() (() -> Unit))? = null
) {
    val buttonModifier = if (ButtonType.PRIMARY_REVERSED == type) modifier
        .border(border = BorderStroke(3.dp, color = LoLuAppTheme.colors.primary), shape = type.shape)
    else modifier

    Button(
        modifier = buttonModifier,
        colors = ButtonDefaults.buttonColors(containerColor = type.backgroundColor),
        onClick = onClick,
        shape = type.shape,
    ) {
        Text(
            fontWeight = FontWeight.Bold,
            color = type.textColor,
            style = LoLuAppTheme.typography.p1,
            text = text
        )
        trailing?.invoke()
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

            LoLuButton(
                text = "Button",
                type = ButtonType.PRIMARY,
                onClick = { }
            )

            LoLuButton(
                text = "Button",
                type = ButtonType.PRIMARY_REVERSED,
                onClick = { }
            )
        }
    }
}