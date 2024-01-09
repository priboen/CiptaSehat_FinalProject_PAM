package com.adrikhamid.ciptasehat.ui.screens

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adrikhamid.ciptasehat.R

@Composable
fun DashBoard(
    modifier: Modifier = Modifier,
) {
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