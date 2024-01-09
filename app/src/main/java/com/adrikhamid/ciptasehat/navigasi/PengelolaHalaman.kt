package com.adrikhamid.ciptasehat.navigasi

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.adrikhamid.ciptasehat.R
import com.adrikhamid.ciptasehat.ui.screens.dokter.DokterEntryScreen
import com.adrikhamid.ciptasehat.ui.screens.dokter.HalamanEntry


@Composable
fun CiptaApp(navController: NavHostController = rememberNavController()) {
    HostNavigas(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CiptaSehatTopBar(
    judul: String,
    bisaNavigasiKembali: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = { Text(judul) },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (bisaNavigasiKembali) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack, contentDescription = stringResource(
                            id = R.string.back
                        )
                    )
                }
            }
        }
    )
}

@Composable
fun HostNavigas(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = HalamanEntry.route,
        modifier = modifier
    ) {
        composable(HalamanEntry.route) {
            DokterEntryScreen(navigateBack = { navController.popBackStack() })
        }
    }
}