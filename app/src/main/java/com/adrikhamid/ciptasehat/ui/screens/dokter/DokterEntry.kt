package com.adrikhamid.ciptasehat.ui.screens.dokter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import com.adrikhamid.ciptasehat.data.objek.SpesialisDokter.spesialis
import com.adrikhamid.ciptasehat.navigasi.CiptaSehatTopBar
import com.adrikhamid.ciptasehat.ui.viewmodel.PenyediaViewModel
import com.adrikhamid.ciptasehat.ui.viewmodel.dokter.DetailDokter
import com.adrikhamid.ciptasehat.ui.viewmodel.dokter.DokterEntryViewModel
import com.adrikhamid.ciptasehat.ui.viewmodel.dokter.UIStateDokter
import kotlinx.coroutines.launch

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
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large)),
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        DokterEntryForm(
            onSelectionChanged = { uiStateDokter.detailDokter.jkDokter },
            onSpecialistChanged = { uiStateDokter.detailDokter.spesialis },
            pilihanJenisKelamin = jk.map { id -> context.resources.getString(id) },
            detailDokter = uiStateDokter.detailDokter,
            onValueChange = onDokterValueChange
        )
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DokterEntryForm(
    modifier: Modifier = Modifier,
    onSelectionChanged: (String) -> Unit,
    onSpecialistChanged: (DetailDokter) -> Unit,
    pilihanJenisKelamin: List<String>,
    detailDokter: DetailDokter,
    onValueChange: (DetailDokter) -> Unit = {},
    enabled: Boolean = true
) {

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
                    )

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
        Text(text = "Masukan Spesialis Dokter :")
        DropdownSpesialis(onValueSpecialist = { onSpecialistChanged(detailDokter.copy(spesialis = it)) })

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownSpesialis(
    onValueSpecialist: (String) -> Unit,
    modifier: Modifier = Modifier,

    ) {
    val listDokter = spesialis
    val selectedItem = remember {
        mutableStateOf(listDokter[0])
    }
    val expanded = remember {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(32.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded.value,
            onExpandedChange = {
                expanded.value = !expanded.value
            }
        ) {
            TextField(
                value = selectedItem.value,
                onValueChange = { onValueSpecialist(selectedItem.value) },
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value)
                }, modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false }) {
                listDokter.forEach { item ->
                    DropdownMenuItem(text = { item }, onClick = {
                        selectedItem.value = item
                        onValueSpecialist(item)
                        expanded.value = false
                    })
                }
            }
        }
    }
}