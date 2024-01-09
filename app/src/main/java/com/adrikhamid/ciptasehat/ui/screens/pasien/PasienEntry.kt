package com.adrikhamid.ciptasehat.ui.screens.pasien

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DokterEntryForm(
    modifier: Modifier = Modifier,
) {

    var namaPasien by remember {
        mutableStateOf("")
    }
    var tglLahir by remember {
        mutableStateOf("")
    }
    var alamat by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = namaPasien,
            shape = MaterialTheme.shapes.large,
            label = { Text(text = stringResource(id = R.string.nama)) },
            onValueChange = { namaPasien = it }
        )
        Spacer(modifier = Modifier.padding(10.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = tglLahir,
            shape = MaterialTheme.shapes.large,
            label = { Text(text = stringResource(id = R.string.tgl)) },
            onValueChange = { tglLahir = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.padding(10.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = alamat,
            shape = MaterialTheme.shapes.large,
            label = { Text(text = stringResource(id = R.string.alamat)) },
            onValueChange = { alamat = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.padding(10.dp))

    }

}