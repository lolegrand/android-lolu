package fr.enssat.bluetoothhid.lolu.ui.component.tiles


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.enssat.bluetoothhid.lolu.ui.theme.LoLuTheme

@Composable
fun ShortcutTile(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    iconColor: Color,
    icon: ImageVector,
    onClick: (() -> Unit)?,
    withDelete: Boolean = false
) {

    Box(
        modifier = Modifier,
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = modifier
                .padding(7.dp)
                .clip(RoundedCornerShape(10.dp))
                .clickable(enabled = onClick != null) {
                    if (onClick != null) {
                        onClick()
                    }
                }
                .background(
                    color = backgroundColor
                )
                .width(60.dp)
                .aspectRatio(1f)
                .border(3.dp, iconColor, RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                imageVector = icon,
                contentDescription = null,
                tint = iconColor
            )
        }

        if (withDelete) {
            Icon(
                modifier = Modifier.align(Alignment.TopEnd)
                    .padding(end = 10.dp)
                    .clip(CircleShape)
                    .background(color = Color.Red)
                    .clickable(enabled = onClick != null) {
                        if (onClick != null) {
                            onClick()
                        }
                    },
                imageVector = Icons.Rounded.Clear,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

@Composable
fun ShortcutTileAdd(
    onClick: () -> Unit
) {
    ShortcutTile(
        backgroundColor = Color(0xFFFFFFFF),
        iconColor = Color(0xFF000000),
        icon = Icons.Filled.Add,
        onClick = { onClick() }
    )
}

@Preview
@Composable
private fun ShortcutTilePreview() {
    LoLuTheme {
        Column {
            ShortcutTile(
                backgroundColor = Color(0xFFFF0000),
                iconColor = Color(0xFF0000FF),
                icon = Icons.Filled.Add,
                onClick = { }
            )

            Spacer(modifier = Modifier.height(20.dp))

            ShortcutTile(
                backgroundColor = Color(0xFFFF0000),
                iconColor = Color(0xFF0000FF),
                icon = Icons.Filled.Add,
                onClick = { },
                withDelete = true
            )

            Spacer(modifier = Modifier.height(20.dp))

            ShortcutTileAdd { }
        }
    }
}