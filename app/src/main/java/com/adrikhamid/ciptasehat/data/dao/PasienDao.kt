package com.adrikhamid.ciptasehat.data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.adrikhamid.ciptasehat.data.entity.Pasien
import kotlinx.coroutines.flow.Flow

interface PasienDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pasien: Pasien)

    @Update
    suspend fun update(pasien: Pasien)

    @Delete
    suspend fun delete(pasien: Pasien)

    @Query("SELECT * from tbl_pasien WHERE pasien_id = :id")
    fun getPasien(id: Int): Flow<Pasien>

    @Query("SELECT * from tbl_pasien ORDER BY namaPasien ASC")
    fun getAllPasien():Flow<List<Pasien>>
}