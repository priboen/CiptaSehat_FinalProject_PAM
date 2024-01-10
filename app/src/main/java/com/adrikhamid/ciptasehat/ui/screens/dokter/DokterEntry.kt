package com.adrikhamid.ciptasehat.ui.screens.dokter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.adrikhamid.ciptasehat.R
import com.adrikhamid.ciptasehat.data.objek.JenisKelamin.jk
import com.adrikhamid.ciptasehat.navigasi.CiptaSehatTopBar
import com.adrikhamid.ciptasehat.navigasi.DestinasiNavigasi
import com.adrikhamid.ciptasehat.ui.viewmodel.PenyediaViewModel
import com.adrikhamid.ciptasehat.ui.viewmodel.dokter.DetailDokter
import com.adrikhamid.ciptasehat.ui.viewmodel.dokter.DokterEntryViewModel
import com.adrikhamid.ciptasehat.ui.viewmodel.dokter.UIStateDokter
import kotlinx.coroutines.launch


object DokterEntryDestinasi : DestinasiNavigasi {
    override val route = "dokter_entry"
    override val judul = R.string.data_dokter
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DokterEntryScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DokterEntryViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CiptaSehatTopBar(
                judul = "Tambah Dokter",
                bisaNavigasiKembali = true,
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        DokterEntryBody(
            uiStateDokter = viewModel.uiStateDokter,
            onDokterValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveDokter()
                    navigateBack()
                }
            },
            modifier = modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        )
    }
}

@Composable
fun DokterEntryBody(
    uiStateDokter: UIStateDokter,
    onDokterValueChange: (DetailDokter) -> Unit,
    modifier: Modifier = Modifier,
    onSaveClick: () -> Unit,
) {
    val context = LocalContext.current
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large)),
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        DokterEntryForm(
            onSelectionChanged = { uiStateDokter.detailDokter.jkDokter },
            pilihanJenisKelamin = jk.map { id -> context.resources.getString(id) },
            detailDokter = uiStateDokter.detailDokter,
            onSaveClick = onSaveClick,
            uiStateDokter = uiStateDokter,
            onValueChange = onDokterValueChange
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DokterEntryForm(
    modifier: Modifier = Modifier,
    onSelectionChanged: (String) -> Unit,
    pilihanJenisKelamin: List<String>,
    detailDokter: DetailDokter,
    onValueChange: (DetailDokter) -> Unit = {},
    onSaveClick: () -> Unit,
    uiStateDokter: UIStateDokter,
    enabled: Boolean = true,
) {
    val options = listOf(
        "Dokter Umum",
        "Dokter Gigi",
        "Dokter Mata",
        "Dokter Kulit dan Kelamin",
        "Dokter Telinga, Hidung dan Tenggorokan",
        "Dokter Saraf",
        "Dokter Bedah",
        "Dokter Anak"
    )
    var chosenDropdown by remember {
        mutableStateOf(
            detailDokter.spesialis
        )
    }
    var dropDownExposed by remember { mutableStateOf(false) }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = detailDokter.namaDokter,
            shape = MaterialTheme.shapes.large,
            label = { Text(text = stringResource(id = R.string.nama)) },
            onValueChange = { onValueChange(detailDokter.copy(namaDokter = it)) },
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = detailDokter.nohp,
            shape = MaterialTheme.shapes.large,
            label = { Text(text = stringResource(id = R.string.nohp)) },
            onValueChange = { onValueChange(detailDokter.copy(nohp = it)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            enabled = enabled,
            singleLine = true
        )
        Column(
            modifier = modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.jk))
            pilihanJenisKelamin.forEach { item ->
                Row(
                    modifier = modifier.selectable(
                        selected = detailDokter.jkDokter == item,
                        onClick = {
                            onValueChange(detailDokter.copy(jkDokter = item))
                            onSelectionChanged(item)
                        }
                    ), verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = detailDokter.jkDokter == item,
                        onClick = {
                            onValueChange(detailDokter.copy(jkDokter = item))
                            onSelectionChanged(item)
                        }
                    )
                    Text(text = item)
                }
            }
        }
        Text(text = "Masukan bidang dokter :")
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(32.dp)
        ) {
            ExposedDropdownMenuBox(
                expanded = dropDownExposed,
                onExpandedChange = {
                    dropDownExposed = !dropDownExposed
                }
            ) {
                OutlinedTextField(
                    value = chosenDropdown,
                    readOnly = true,
                    onValueChange = { },
                    label = { Text("Spesialis Dokter") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = dropDownExposed
                        )
                    },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(1f)
                )
                ExposedDropdownMenu(
                    expanded = dropDownExposed,
                    onDismissRequest = {
                        dropDownExposed = false
                    }
                ) {
                    options.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = { Text(text = selectionOption) },
                            onClick = {
                                chosenDropdown = selectionOption
                                onValueChange(detailDokter.copy(spesialis = selectionOption))
                                dropDownExposed = false
                            }
                        )
                    }
                }
            }
        }
        Button(
            onClick = onSaveClick,
            enabled = uiStateDokter.isEntryValid,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.btn_submit))
        }
    }
}