package com.adrikhamid.ciptasehat.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adrikhamid.ciptasehat.R
import com.adrikhamid.ciptasehat.navigasi.CiptaSehatTopBar
import com.adrikhamid.ciptasehat.navigasi.DestinasiNavigasi
import kotlinx.coroutines.delay

object DestinasiHome : DestinasiNavigasi {
    override val route = "home"
    override val judul = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppHomeScreen(
    navigasiDokter: () -> Unit,
    navigasiPasien: () -> Unit,
    navigasiBerobat: () -> Unit,
) {
    Scaffold(
        topBar = {
            CiptaSehatTopBar(
                judul = stringResource(id = DestinasiHome.judul),
                bisaNavigasiKembali = false
            )
        }
    ) { innerPadding ->
        DashBoard(
            onDokterClick = navigasiDokter,
            onPasienClick = navigasiPasien,
            onBerobatClicl = navigasiBerobat,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashBoard(
    modifier: Modifier = Modifier,
    onDokterClick: () -> Unit,
    onPasienClick: () -> Unit,
    onBerobatClicl: () -> Unit
) {
    val img_dokter = painterResource(id = R.drawable.dokter)
    val img_pasien = painterResource(id = R.drawable.pasien)
    val img_obat = painterResource(id = R.drawable.berobat)
    val logo = painterResource(id = R.drawable.logo)


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .background(color = Color(android.graphics.Color.parseColor("#f5f5f5")))
            .padding(
                dimensionResource(id = R.dimen.padding_extra_large)
            )
            .padding(top = dimensionResource(id = R.dimen.padding_extra_large)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = logo, contentDescription = null, Modifier.requiredSize(150.dp))
        Text(
            text = "Cipta Sehat",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Divider()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .padding(end = 12.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                    .padding(
                        top = dimensionResource(
                            id = R.dimen.padding_medium
                        )
                    ), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .height(65.dp)
                        .width(75.dp)
                        .background(
                            color = Color(android.graphics.Color.parseColor("#4a4848")),
                            shape = RoundedCornerShape(20.dp)
                        ), contentAlignment = Alignment.Center
                ) {
                    Card(onClick = onDokterClick) {
                        Image(
                            painter = img_dokter,
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds,
                        )
                    }
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dimensionResource(id = R.dimen.padding_medium))
                        .height(40.dp)
                        .background(
                            color = Color(android.graphics.Color.parseColor("#2accf5")),
                            shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp)
                        ),
                ) {
                    Text(
                        text = stringResource(id = R.string.data_dokter),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(android.graphics.Color.parseColor("#f5f5f5"))
                    )
                }

            }
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .padding(end = 12.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                    .padding(
                        top = dimensionResource(
                            id = R.dimen.padding_medium
                        )
                    ), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .height(65.dp)
                        .width(75.dp)
                        .background(
                            color = Color(android.graphics.Color.parseColor("#7868e5")),
                            shape = RoundedCornerShape(20.dp)
                        ), contentAlignment = Alignment.Center
                ) {
                    Card(onClick = onPasienClick) {
                        Image(
                            painter = img_pasien,
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds
                        )
                    }
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = dimensionResource(
                                id = R.dimen.padding_medium
                            )
                        )
                        .height(40.dp)
                        .background(
                            color = Color(
                                android.graphics.Color.parseColor("#2accf5")
                            ),
                            shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp)
                        ),
                ) {
                    Text(
                        text = stringResource(id = R.string.data_pasien),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(android.graphics.Color.parseColor("#f5f5f5"))
                    )
                }


            }


            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .padding(end = 12.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                    .padding(
                        top = dimensionResource(
                            id = R.dimen.padding_medium
                        )
                    ), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .height(65.dp)
                        .width(75.dp)
                        .background(
                            color = Color(android.graphics.Color.parseColor("#7868e5")),
                            shape = RoundedCornerShape(20.dp)
                        ), contentAlignment = Alignment.Center
                ) {
                    Card(onClick = onBerobatClicl) {
                        Image(
                            painter = img_obat,
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds
                        )
                    }
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = dimensionResource(
                                id = R.dimen.padding_medium
                            )
                        )
                        .height(40.dp)
                        .background(
                            color = Color(
                                android.graphics.Color.parseColor("#2accf5")
                            ),
                            shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp)
                        ),
                ) {
                    Text(
                        text = stringResource(id = R.string.data_obat),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(android.graphics.Color.parseColor("#f5f5f5"))
                    )
                }

            }

        }
        Carousel()
    }
}

@Composable
fun Carousel() {
    val images = listOf(
        R.drawable.banner,
        R.drawable.banner1,
        R.drawable.banner3,
    )
    ImagesSliderWithIndicator(images = images)
}

@Composable
fun ImageSliderItem(
    imageRes: Int
) {
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth()
    )
}

@Composable
fun Indicator(active: Boolean) {
    val color = if (active) Color.Cyan else Color.Gray
    val size = if (active) 12.dp else 7.dp
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(color)
            .size(size)
    )
}

@Composable
fun ImagesSliderWithIndicator(images: List<Int>) {
    val currentIndex = remember {
        mutableStateOf(0)
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            currentIndex.value = (currentIndex.value + 1) % images.size
        }
    }

    Column(
        modifier = Modifier
            .padding(top = 10.dp)
    ) {
        ImageSliderItem(imageRes = images[currentIndex.value])
    }
    Row(
        modifier = Modifier.padding(top = 10.dp, bottom = 55.dp)
    ) {
        images.forEachIndexed { index, i ->
            Indicator(
                active = index == currentIndex.value
            )
            if (index < images.size - 1) {
                Spacer(modifier = Modifier.width(15.dp))
            }
        }
    }
}