package com.adrikhamid.ciptasehat.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.adrikhamid.ciptasehat.data.entity.Berobat
import com.adrikhamid.ciptasehat.data.entity.Dokter
import kotlinx.coroutines.flow.Flow
@Dao
interface BerobatDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(berobat: Berobat)

    @Update
    suspend fun update(berobat: Berobat)

    @Delete
    suspend fun delete(berobat: Berobat)

    @Query("SELECT * from tbl_berobat WHERE id = :id")
    fun getBerobat(id: Int): Flow<Berobat>

    @Query("SELECT * from tbl_dokter ORDER BY namaDokter ASC")
    fun getAllBerobat(): Flow<List<Berobat>>
}