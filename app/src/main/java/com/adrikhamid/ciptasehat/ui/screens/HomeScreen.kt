package com.adrikhamid.ciptasehat.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adrikhamid.ciptasehat.R
import com.adrikhamid.ciptasehat.navigasi.DestinasiNavigasi

object DestinasiHome : DestinasiNavigasi {
    override val route = "home"
    override val judul = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashBoard(
    modifier: Modifier = Modifier,
    onDokterClick: () -> Unit,
    onPasienClick: () -> Unit
) {
    val img_dokter = painterResource(id = R.drawable.dokter)
    val img_pasien = painterResource(id = R.drawable.pasien)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .background(color = Color(android.graphics.Color.parseColor("#ede7f8")))
            .padding(
                dimensionResource(id = R.dimen.padding_medium)
            ), horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
                            color = Color(android.graphics.Color.parseColor("#7868e5")),
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
                            color = Color(android.graphics.Color.parseColor("#dad8ff")),
                            shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp)
                        ),
                ) {
                    Text(
                        text = stringResource(id = R.string.data_dokter),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(android.graphics.Color.parseColor("#7868e5"))
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
                                android.graphics.Color.parseColor("#dad8ff")
                            ),
                            shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp)
                        ),
                ) {
                    Text(
                        text = stringResource(id = R.string.data_pasien),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(android.graphics.Color.parseColor("#7868e5"))
                    )
                }

            }

        }
        Text(text = "Jadwal")
    }
}