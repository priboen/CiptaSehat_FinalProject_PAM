package com.adrikhamid.ciptasehat.ui.screens.dokter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.adrikhamid.ciptasehat.R
import com.adrikhamid.ciptasehat.data.objek.SpesialisDokter.spesialis

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DokterEntryForm(
    modifier: Modifier = Modifier,
    onSelectionChanged: (String) -> Unit,
    pilihanJenisKelamin: List<String>
) {

    var namaDokter by remember {
        mutableStateOf("")
    }
    var nohp by remember {
        mutableStateOf("")
    }
    var jk by remember {
        mutableStateOf("")
    }
    var spesialis by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = namaDokter,
            shape = MaterialTheme.shapes.large,
            label = { Text(text = stringResource(id = R.string.nama)) },
            onValueChange = { namaDokter = it }
        )
        Spacer(modifier = Modifier.padding(10.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = nohp,
            shape = MaterialTheme.shapes.large,
            label = { Text(text = stringResource(id = R.string.nohp)) },
            onValueChange = { nohp = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.jk))
            pilihanJenisKelamin.forEach { item ->
                Row(
                    modifier = Modifier.selectable(
                        selected = jk == item,
                        onClick = {
                            jk = item
                            onSelectionChanged(item)
                        }
                    )

                ) {
                    RadioButton(selected = jk == item, onClick = {
                        jk = item
                        onSelectionChanged(item)
                    }
                    )
                    Text(text = item)
                }
            }
        }
        DropdownSpesialis()
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownSpesialis(
    modifier: Modifier = Modifier
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
                onValueChange = {},
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
                        expanded.value = false
                    })
                }
            }
        }
    }
}