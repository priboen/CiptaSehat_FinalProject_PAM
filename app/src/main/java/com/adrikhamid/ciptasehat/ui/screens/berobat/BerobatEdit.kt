package com.adrikhamid.ciptasehat.ui.screens.berobat

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.adrikhamid.ciptasehat.R
import com.adrikhamid.ciptasehat.navigasi.CiptaSehatTopBar
import com.adrikhamid.ciptasehat.navigasi.DestinasiNavigasi
import com.adrikhamid.ciptasehat.ui.viewmodel.PenyediaViewModel
import com.adrikhamid.ciptasehat.ui.viewmodel.berobat.BerobatEditViewModel
import kotlinx.coroutines.launch

object BerobatEditDestinasi : DestinasiNavigasi {
    override val route = "berobat_edit"
    override val judul = R.string.data_obat
    const val berobatIdArg = "itemId"
    val routeWithArgs = "$route/{$berobatIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BerobatItemEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BerobatEditViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            CiptaSehatTopBar(
                judul = stringResource(BerobatEditDestinasi.judul),
                bisaNavigasiKembali = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        BerobatEntryBody(
            uiStateBerobat = viewModel.BerobatUiState,
            onBerobatValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateBerobat()
                    navigateBack()
                }
            }, modifier = modifier.padding(innerPadding)
        )
    }
}