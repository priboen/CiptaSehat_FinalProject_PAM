package com.adrikhamid.ciptasehat.repositori

import androidx.room.Dao
import com.adrikhamid.ciptasehat.data.entity.Pasien
import kotlinx.coroutines.flow.Flow

interface PasienRepo {
    fun getAllPasienStream(): Flow<List<Pasien>>
    fun getPasienStream(id: Int): Flow<Pasien?>
    suspend fun insertPasien(pasien: Pasien)
    suspend fun updatePasien(pasien: Pasien)
    suspend fun deletePasien(pasien: Pasien)
}