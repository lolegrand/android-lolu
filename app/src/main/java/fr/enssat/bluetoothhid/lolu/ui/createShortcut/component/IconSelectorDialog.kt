package fr.enssat.bluetoothhid.lolu.ui.createShortcut.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import fr.enssat.bluetoothhid.lolu.ui.materialIcons
import fr.enssat.bluetoothhid.lolu.ui.theme.LoLuAppTheme

@Composable
fun IconSelectorDialog(
    onDismiss: () -> Unit,
    onIconSelected: (String) -> Unit
) {

    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = onDismiss
    ) {
        Box(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxSize()
                .clip(RoundedCornerShape(18.dp))
                .background(LoLuAppTheme.colors.background)
        ) {
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(materialIcons.entries.toList()) { (name, icon) ->

                    Box(
                        modifier = Modifier.fillMaxWidth(.5f).aspectRatio(1f)
                            .clickable { onIconSelected(name) },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier.size(75.dp),
                            imageVector = icon,
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }

                }
            }
        }

    }
}