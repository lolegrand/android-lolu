package fr.enssat.bluetoothhid.lolu.ui.createShortcut

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import fr.enssat.bluetoothhid.lolu.ui.createShortcut.component.CreateShortcutContent

@Composable
fun CreateShortcutScreen(
    viewModel: CreateShortcutViewModel = hiltViewModel(),
    onCreationCompleted: () -> Unit
) {

    CreateShortcutContent(
        onCreateValidate = { bgRed, bgGreen, bgBlue, icRed, icGreen, icBlue, iconName ->
            viewModel.createNewIcon(bgRed, bgGreen, bgBlue, icRed, icGreen, icBlue, iconName)
            onCreationCompleted()
        }
    )

}