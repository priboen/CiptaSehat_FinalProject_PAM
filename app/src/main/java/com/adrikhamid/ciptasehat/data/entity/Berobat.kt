package com.adrikhamid.ciptasehat.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "tbl_berobat"
)
data class Berobat(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val pasienNama: String = "",
    val dokterNama: String = "",
    val jenisPerawatan: String = "",
)
