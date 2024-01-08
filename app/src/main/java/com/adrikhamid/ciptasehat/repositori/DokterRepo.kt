package com.adrikhamid.ciptasehat.repositori

import com.adrikhamid.ciptasehat.data.entity.Dokter
import kotlinx.coroutines.flow.Flow

interface DokterRepo {
    fun getAllDokterStream():Flow<List<Dokter>>
    fun getDokterStream(id:Int):Flow<Dokter?>
    suspend fun insertDokter(dokter: Dokter)
    suspend fun updateDokter(dokter: Dokter)
    suspend fun deleteDokter(dokter: Dokter)
}