package fr.enssat.bluetoothhid.lolu.ui.hidDetail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.enssat.bluetoothhid.lolu.ui.component.tiles.ShortcutTileAdd
import fr.enssat.bluetoothhid.lolu.ui.theme.LoLuAppTheme
import fr.enssat.bluetoothhid.lolu.ui.theme.LoLuTheme

@Composable
fun HIDDetailEmpty(
    onCreateShortcut: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Il sembelrait que vous n'aillez pas encore de shotcut. Créez-en un !",
            style = LoLuAppTheme.typography.h2,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(25.dp))

        ShortcutTileAdd {
            onCreateShortcut()
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun HIDDetailEmptyPreview() {
    LoLuTheme {
        HIDDetailEmpty {}
    }
}
