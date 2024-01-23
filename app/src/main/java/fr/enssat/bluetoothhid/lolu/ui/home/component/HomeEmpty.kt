package fr.enssat.bluetoothhid.lolu.ui.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.enssat.bluetoothhid.lolu.ui.component.buttons.ButtonType
import fr.enssat.bluetoothhid.lolu.ui.component.buttons.LoLuButton
import fr.enssat.bluetoothhid.lolu.ui.theme.LoLuAppTheme
import fr.enssat.bluetoothhid.lolu.ui.theme.LoLuTheme


@Composable
fun HomeEmptyView(
    onCreateNewHid : () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Apparement, vous n'avez pas encore créer de HID. Créez en un ci-dessous.",
            style = LoLuAppTheme.typography.h2,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(25.dp))

        LoLuButton(
            modifier = Modifier.fillMaxWidth(.95f).height(50.dp),
            text = "Créer un HID",
            onClick = onCreateNewHid,
            type = ButtonType.PRIMARY
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeEmptyViewPreview() {
    LoLuTheme {
        HomeEmptyView { }
    }
}
