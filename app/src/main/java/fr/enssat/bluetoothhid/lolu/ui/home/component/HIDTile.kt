package fr.enssat.bluetoothhid.lolu.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.enssat.bluetoothhid.data.vo.HID
import fr.enssat.bluetoothhid.lolu.ui.theme.LoLuAppTheme
import fr.enssat.bluetoothhid.lolu.ui.theme.LoLuTheme

@Composable
fun HIDTile(
    hid: HID,
    onClick: () -> Unit
) {
    Box (
        modifier = Modifier.fillMaxWidth()
            .height(50.dp)
            .background(
                color = LoLuAppTheme.colors.primary,
                shape = RoundedCornerShape(5.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            modifier = Modifier.padding(10.dp),
            text = hid.name,
            style = LoLuAppTheme.typography.h2,
            color = LoLuAppTheme.colors.nuance100,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}


@Preview
@Composable
fun HIDTilePreview() {
    LoLuTheme {
        Column {

            HIDTile(hid = HID("Coucou")) {}

            Spacer(modifier = Modifier.height(10.dp))

            HIDTile(hid = HID("C")) {}

            Spacer(modifier = Modifier.height(10.dp))

            HIDTile(hid = HID("Coucou un text beaucoup trop long mais c'est pas ma faute c'est pour tester")) {}
        }
    }
}