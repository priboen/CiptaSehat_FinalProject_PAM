package com.adrikhamid.ciptasehat.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_pasien")
data class Pasien(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pasien_id")
    val id: Int = 0,
    val namaPasien: String = "",
    val tglLahir: String = "",
    val alamat: String = ""
)