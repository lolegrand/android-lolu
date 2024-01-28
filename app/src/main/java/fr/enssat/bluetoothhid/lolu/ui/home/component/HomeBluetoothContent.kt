package fr.enssat.bluetoothhid.lolu.ui.home.component

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import fr.enssat.bluetoothhid.lolu.bluetooth.BluetoothViewModel
import fr.enssat.bluetoothhid.lolu.ui.component.buttons.ButtonType
import fr.enssat.bluetoothhid.lolu.ui.component.buttons.LoLuButton
import fr.enssat.bluetoothhid.lolu.ui.theme.LoLuAppTheme

@SuppressLint("MissingPermission")
@Composable
fun HomeBluetoothContent(
    bluetoothViewModel: BluetoothViewModel
) {

    val discoveredDevices by bluetoothViewModel.discoveredDevice.collectAsState()
    val connectedDevice by bluetoothViewModel.connectedDevice.collectAsState()
    val scanState by bluetoothViewModel.scanState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (connectedDevice == null) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(vertical = 10.dp, horizontal = 20.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(color = Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    text = "Aucun appareil connecté",
                    textAlign = TextAlign.Center,
                    style = LoLuAppTheme.typography.h1,
                    color = LoLuAppTheme.colors.nuance100
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(10.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(color = LoLuAppTheme.colors.primary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    text = "Connecté à : " + connectedDevice?.name,
                    textAlign = TextAlign.Center,
                    style = LoLuAppTheme.typography.h1,
                    color = LoLuAppTheme.colors.nuance100
                )
            }
        }

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {

            items(discoveredDevices) { discoveredDevice ->
                if (discoveredDevice == connectedDevice) {
                    return@items
                }

                Column(
                    modifier = Modifier
                        .clickable {
                            bluetoothViewModel.connectToDevice(discoveredDevice)
                        }
                        .padding(vertical = 10.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = discoveredDevice.name,
                        style = LoLuAppTheme.typography.h1
                    )
                }
            }

        }

        if (!scanState) {
            LoLuButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 30.dp, top = 20.dp)
                    .height(50.dp),
                text = "Start Discovering",
                onClick = {
                    bluetoothViewModel.startDiscovering()
                },
                type = ButtonType.PRIMARY
            )
        } else {
            LoLuButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 30.dp, top = 20.dp)
                    .height(50.dp),
                text = "Stop Discovering",
                onClick = {
                    bluetoothViewModel.stopDiscovering()
                },
                type = ButtonType.PRIMARY_REVERSED,
                trailing = {
                    CircularProgressIndicator(
                        modifier = Modifier.size(30.dp).padding(start = 20.dp),
                        color = LoLuAppTheme.colors.primary,

                    )
                }
            )
        }
    }
}