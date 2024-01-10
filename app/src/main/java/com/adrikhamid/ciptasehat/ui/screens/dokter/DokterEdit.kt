package com.adrikhamid.ciptasehat.ui.screens.dokter

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.adrikhamid.ciptasehat.R
import com.adrikhamid.ciptasehat.navigasi.CiptaSehatTopBar
import com.adrikhamid.ciptasehat.navigasi.DestinasiNavigasi
import com.adrikhamid.ciptasehat.ui.viewmodel.PenyediaViewModel
import com.adrikhamid.ciptasehat.ui.viewmodel.dokter.DokterEditViewModel
import kotlinx.coroutines.launch

object DokterEditDestinasi : DestinasiNavigasi {
    override val route = "dokter_edit"
    override val judul = R.string.edit_dokter
    const val dokterIdArg = "dokterId"
    val routeWithArgs = "$route/{$dokterIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DokterItemEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DokterEditViewModel = viewModel(factory = PenyediaViewModel.Factory)
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
        DokterEntryBody(
            uiStateDokter = viewModel.dokterUiState,
            onDokterValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateDokter()
                    navigateBack()
                }
            },
            modifier = modifier.padding(innerPadding)
        )
    }
}