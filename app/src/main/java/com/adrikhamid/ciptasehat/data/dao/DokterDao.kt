package com.adrikhamid.ciptasehat.data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.adrikhamid.ciptasehat.data.entity.Dokter
import kotlinx.coroutines.flow.Flow

interface DokterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dokter: Dokter)

    @Update
    suspend fun update(dokter: Dokter)

    @Delete
    suspend fun delete(dokter: Dokter)

    @Query("SELECT * from tbl_dokter WHERE dokter_id = :id")
    fun getDokter(id: Int): Flow<Dokter>

    @Query("SELECT * from tbl_dokter ORDER BY namaDokter ASC")
    fun getAllDokter(): Flow<List<Dokter>>
}