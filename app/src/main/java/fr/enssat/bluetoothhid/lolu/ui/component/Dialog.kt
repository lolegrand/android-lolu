package fr.enssat.bluetoothhid.lolu.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import fr.enssat.bluetoothhid.lolu.ui.component.buttons.ButtonType
import fr.enssat.bluetoothhid.lolu.ui.component.buttons.LoLuButton
import fr.enssat.bluetoothhid.lolu.ui.theme.LoLuAppTheme
import fr.enssat.bluetoothhid.lolu.ui.theme.LoLuTheme

@Composable
fun LoLuDialog(
    title: String,
    subtitle: String? = null,
    actionText1: String,
    actionText2: String? = null,
    onClickAction1: () -> Unit,
    onClickAction2: (() -> Unit)? = null,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Column(
            modifier = Modifier
                .padding(18.dp)
                .clip(RoundedCornerShape(18.dp))
                .background(LoLuAppTheme.colors.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 24.dp, end = 24.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = title,
                    color = LoLuAppTheme.colors.nuance15,
                    style = LoLuAppTheme.typography.h1
                )

                subtitle?.let {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 3.dp),
                        text = it,
                        color = LoLuAppTheme.colors.nuance15,
                        style = LoLuAppTheme.typography.p1
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 12.dp),
                horizontalArrangement = Arrangement.End
            ) {
                actionText2?.let {
                    LoLuButton(
                        text = it,
                        onClick = {
                            onClickAction2?.invoke()
                        },
                        type = ButtonType.DIALOG_ABORT
                    )

                    Spacer(modifier = Modifier.width(12.dp))
                }

                LoLuButton(
                    text = actionText1,
                    onClick = onClickAction1,
                    type = ButtonType.DIALOG_VALIDATE
                )
            }
        }
    }
}

@Composable
fun LoLuWaitingDialog(
    title: String
) {
    Dialog(
        onDismissRequest = { },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .height(200.dp)
                .background(LoLuAppTheme.colors.nuance100, shape = RoundedCornerShape(20.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier.fillMaxWidth().padding(top = 24.dp).padding(horizontal = 20.dp),
                textAlign = TextAlign.Center,
                text = title,
                color = LoLuAppTheme.colors.nuance15,
                style = LoLuAppTheme.typography.h1
            )

            Box(
                modifier = Modifier.fillMaxWidth().weight(1f),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator(
                    color = LoLuAppTheme.colors.primary
                )
            }
        }
    }
}

@Preview
@Composable
fun LoLuWaitingDialogPrev() {
    LoLuTheme {
        LoLuWaitingDialog("Waiting")
    }
}
