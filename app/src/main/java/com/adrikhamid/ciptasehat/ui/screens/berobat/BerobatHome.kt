package com.adrikhamid.ciptasehat.ui.screens.berobat

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.adrikhamid.ciptasehat.R
import com.adrikhamid.ciptasehat.data.entity.Berobat
import com.adrikhamid.ciptasehat.data.entity.Pasien
import com.adrikhamid.ciptasehat.navigasi.CiptaSehatTopBar
import com.adrikhamid.ciptasehat.navigasi.DestinasiNavigasi
import com.adrikhamid.ciptasehat.ui.screens.pasien.BodyPasien
import com.adrikhamid.ciptasehat.ui.screens.pasien.DataPasien
import com.adrikhamid.ciptasehat.ui.screens.pasien.ListPasien
import com.adrikhamid.ciptasehat.ui.screens.pasien.PasienHomeDestinasi
import com.adrikhamid.ciptasehat.ui.viewmodel.PenyediaViewModel
import com.adrikhamid.ciptasehat.ui.viewmodel.berobat.BerobatHomeViewModel
import com.adrikhamid.ciptasehat.ui.viewmodel.pasien.PasienHomeViewModel

object BerobatHomeDestinasi: DestinasiNavigasi{
    override val route = "berobat_home"
    override val judul = R.string.data_obat

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasienHomeScreen(
    navigateToItemEntry: () -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (Int) -> Unit = {},
    viewModel: BerobatHomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CiptaSehatTopBar(
                judul = stringResource(id = BerobatHomeDestinasi.judul),
                bisaNavigasiKembali = true,
                navigateUp = onNavigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.entry_dokter)
                )
            }
        },
    ) { innerPadding ->
        val uiStateBerobat by viewModel.homeUiState.collectAsState()
        BodyBerobat(
            itemBerobat = uiStateBerobat.listBerobat,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            onBerobatClick = onDetailClick
        )
    }
}
@Composable
fun BodyBerobat(
    itemBerobat: List<Berobat>,
    modifier: Modifier = Modifier,
    onBerobatClick: (Int) -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.background(color = Color(android.graphics.Color.parseColor("#ffffff")))
    ) {
        if (itemBerobat.isEmpty()) {
            Text(
                text = stringResource(R.string.deskripsi_no_item),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            ListBerobat(
                itemBerobat = itemBerobat,
                modifier = Modifier
                    .padding(horizontal = dimensionResource(id = R.dimen.padding_small)),
                onItemClick = { onBerobatClick(it.id) }
            )
        }
    }
}
@Composable
fun ListBerobat(
    itemBerobat: List<Berobat>,
    modifier: Modifier = Modifier,
    onItemClick: (Berobat) -> Unit
) {
    LazyColumn(modifier = Modifier) {
        items(items = itemBerobat, key = { it.id }) { person ->
            DataBerobat(
                berobat = person,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clickable { onItemClick(person) }
            )
        }
    }
}
@Composable
fun DataBerobat(
    berobat: Berobat,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = berobat.pasienNama,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                )
                Text(
                    text = berobat.dokterNama,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = berobat.jenisPerawatan,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}