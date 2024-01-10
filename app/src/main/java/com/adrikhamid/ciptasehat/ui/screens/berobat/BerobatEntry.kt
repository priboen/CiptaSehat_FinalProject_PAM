package com.adrikhamid.ciptasehat.ui.screens.berobat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import com.adrikhamid.ciptasehat.R
import com.adrikhamid.ciptasehat.data.objek.JenisPerawatan.rawat
import com.adrikhamid.ciptasehat.navigasi.CiptaSehatTopBar
import com.adrikhamid.ciptasehat.navigasi.DestinasiNavigasi
import com.adrikhamid.ciptasehat.ui.screens.pasien.FormInputPasien
import com.adrikhamid.ciptasehat.ui.screens.pasien.PasienEntryBody
import com.adrikhamid.ciptasehat.ui.screens.pasien.PasienEntryDestinasi
import com.adrikhamid.ciptasehat.ui.viewmodel.PenyediaViewModel
import com.adrikhamid.ciptasehat.ui.viewmodel.berobat.BerobatEntryViewModel
import com.adrikhamid.ciptasehat.ui.viewmodel.berobat.DetailBerobat
import com.adrikhamid.ciptasehat.ui.viewmodel.berobat.UIStateBerobat
import com.adrikhamid.ciptasehat.ui.viewmodel.pasien.DetailPasien
import com.adrikhamid.ciptasehat.ui.viewmodel.pasien.PasienEntryViewModel
import com.adrikhamid.ciptasehat.ui.viewmodel.pasien.UIStatePasien
import kotlinx.coroutines.launch

object BerobatEntryDestinasi : DestinasiNavigasi {
    override val route = "berobast_entry"
    override val judul = R.string.data_obat
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryBerobatScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BerobatEntryViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CiptaSehatTopBar(
                judul = stringResource(BerobatEntryDestinasi.judul),
                bisaNavigasiKembali = true,
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        BerobatEntryBody(
            uiStateBerobat = viewModel.uiStateBerobat,
            onBerobatValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveBerobat()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun BerobatEntryBody(
    uiStateBerobat: UIStateBerobat,
    onBerobatValueChange: (DetailBerobat) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large)),
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.padding_medium))
            .background(color = Color(android.graphics.Color.parseColor("#ffffff")))
    ) {
        FormInputBerobat(
            detailBerobat = uiStateBerobat.detailBerobat,
            onValueChange = onBerobatValueChange,
            modifier = Modifier.fillMaxWidth(),
            onSelectionChanged = { uiStateBerobat.detailBerobat.jenisPerawatan },
            pilihanJenisRawat = rawat.map {id -> context.resources.getString(id)}
        )
        Button(
            onClick = onSaveClick,
            enabled = uiStateBerobat.isEntryValid,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(id = R.string.btn_submit))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInputBerobat(
    detailBerobat: DetailBerobat,
    modifier: Modifier = Modifier,
    onValueChange: (DetailBerobat) -> Unit = {},
    pilihanJenisRawat: List<String>,
    onSelectionChanged: (String) -> Unit,
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {
        OutlinedTextField(
            value = detailBerobat.pasienNama,
            onValueChange = { onValueChange(detailBerobat.copy(pasienNama = it)) },
            label = { Text(stringResource(R.string.nama)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = detailBerobat.dokterNama,
            onValueChange = { onValueChange(detailBerobat.copy(dokterNama = it)) },
            label = { Text(stringResource(R.string.nama_dokter)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        Column(
            modifier = modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.jenis_perawatan))
            pilihanJenisRawat.forEach { item ->
                Row(
                    modifier = modifier.selectable(
                        selected = detailBerobat.jenisPerawatan == item,
                        onClick = {
                            onValueChange(detailBerobat.copy(jenisPerawatan = item))
                            onSelectionChanged(item)
                        }
                    ), verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = detailBerobat.jenisPerawatan == item,
                        onClick = {
                            onValueChange(detailBerobat.copy(jenisPerawatan = item))
                            onSelectionChanged(item)
                        }
                    )
                    Text(text = item)
                }
            }
        }
        Divider(
            thickness = dimensionResource(id = R.dimen.padding_small),
            modifier = Modifier.padding(
                bottom = dimensionResource(
                    id = R.dimen.padding_medium
                )
            )
        )
    }
}