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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.adrikhamid.ciptasehat.R
import com.adrikhamid.ciptasehat.ui.screens.DashBoard
import com.adrikhamid.ciptasehat.ui.screens.DestinasiHome
import com.adrikhamid.ciptasehat.ui.screens.dokter.DokterDetailDestinasi
import com.adrikhamid.ciptasehat.ui.screens.dokter.DokterDetailsScreen
import com.adrikhamid.ciptasehat.ui.screens.dokter.DokterEditDestinasi
import com.adrikhamid.ciptasehat.ui.screens.dokter.DokterEntryDestinasi
import com.adrikhamid.ciptasehat.ui.screens.dokter.DokterEntryScreen
import com.adrikhamid.ciptasehat.ui.screens.dokter.DokterHomeDestinasi
import com.adrikhamid.ciptasehat.ui.screens.dokter.DokterHomeScreen
import com.adrikhamid.ciptasehat.ui.screens.dokter.DokterItemEditScreen
import com.adrikhamid.ciptasehat.ui.screens.pasien.EntryPasienScreen
import com.adrikhamid.ciptasehat.ui.screens.pasien.PasienDetailDestinasi
import com.adrikhamid.ciptasehat.ui.screens.pasien.PasienDetailsScreen
import com.adrikhamid.ciptasehat.ui.screens.pasien.PasienEditDestinasi
import com.adrikhamid.ciptasehat.ui.screens.pasien.PasienEntryDestinasi
import com.adrikhamid.ciptasehat.ui.screens.pasien.PasienHomeDestinasi
import com.adrikhamid.ciptasehat.ui.screens.pasien.PasienHomeScreen


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
        startDestination = DestinasiHome.route,
        modifier = modifier
    ) {
        composable(DestinasiHome.route) {
            DashBoard(
                onDokterClick = { navController.navigate(DokterHomeDestinasi.route) },
                onPasienClick = { navController.navigate(PasienEntryDestinasi.route) })
        }
        composable(DokterHomeDestinasi.route) {
            DokterHomeScreen(
                navigateToItemEntry = { navController.navigate(DokterEntryDestinasi.route) },
                onDetailClick = { itemId -> navController.navigate("${DokterDetailDestinasi.route}/$itemId") },
                onNavigateBack = { navController.popBackStack() })
        }
        composable(DokterEntryDestinasi.route) {
            DokterEntryScreen(navigateBack = { navController.popBackStack() })
        }
        composable(
            DokterDetailDestinasi.routeWithArgs,
            arguments = listOf(navArgument(DokterDetailDestinasi.dokterIdArg) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt(DokterDetailDestinasi.dokterIdArg)
            itemId?.let {
                DokterDetailsScreen(
                    navigateToEditItem = { navController.navigate("${DokterEditDestinasi.route}/$it") },
                    navigateBack = { navController.popBackStack() })
            }
        }
        composable(
            DokterEditDestinasi.routeWithArgs,
            arguments = listOf(navArgument(DokterEditDestinasi.dokterIdArg) {
                type = NavType.IntType
            })
        ) {
            DokterItemEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() })
        }

        //Pasien
        composable(PasienHomeDestinasi.route) {
            PasienHomeScreen(
                navigateToItemEntry = { navController.navigate(PasienEntryDestinasi.route) },
                onDetailClick = { itemId -> navController.navigate("${PasienDetailDestinasi.route}/$itemId") }
            )
        }
        composable(PasienEntryDestinasi.route) {
            EntryPasienScreen(navigateBack = { navController.popBackStack() })
        }
        composable(
            PasienDetailDestinasi.routeWithArgs,
            arguments = listOf(navArgument(PasienDetailDestinasi.pasienIdArg) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt(PasienDetailDestinasi.pasienIdArg)
            itemId?.let {
                PasienDetailsScreen(
                    navigateToEditItem = { navController.navigate("${PasienEditDestinasi.route}/$it") },
                    navigateBack = { navController.popBackStack() })
            }
        }
        composable(
            PasienEditDestinasi.routeWithArgs,
            arguments = listOf(navArgument(PasienEditDestinasi.pasienIdArg) {
                type = NavType.IntType
            })
        ) {
            DokterItemEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() })
        }

        //Berobat
    }
}