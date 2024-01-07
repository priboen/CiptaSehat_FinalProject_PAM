package com.adrikhamid.ciptasehat.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_dokter")
data class Dokter(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "dokter_id")
    val id: Int = 0,
    val namaDokter: String = "",
    val jkDokter: String = "",
    val Spesialis: String = ""
)