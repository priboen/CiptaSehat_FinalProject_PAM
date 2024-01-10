package com.adrikhamid.ciptasehat.ui.screens.pasien

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
import com.adrikhamid.ciptasehat.ui.screens.dokter.DokterEditDestinasi
import com.adrikhamid.ciptasehat.ui.screens.dokter.DokterEntryBody
import com.adrikhamid.ciptasehat.ui.viewmodel.PenyediaViewModel
import com.adrikhamid.ciptasehat.ui.viewmodel.dokter.DokterEditViewModel
import com.adrikhamid.ciptasehat.ui.viewmodel.pasien.PasienEditViewModel
import kotlinx.coroutines.launch

object PasienEditDestinasi : DestinasiNavigasi {
    override val route = "pasien_edit"
    override val judul = R.string.edit_pasien
    const val pasienIdArg = "itemId"
    val routeWithArgs = "$route/{$pasienIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasienItemEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PasienEditViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            CiptaSehatTopBar(
                judul = stringResource(DokterEditDestinasi.judul),
                bisaNavigasiKembali = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        PasienEntryBody(
            uiStatePasien = viewModel.PasienUiState,
            onPasienValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updatePasien()
                    navigateBack()
                }
            }, modifier = modifier.padding(innerPadding)
        )
    }
}