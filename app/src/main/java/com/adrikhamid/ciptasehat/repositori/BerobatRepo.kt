package com.adrikhamid.ciptasehat.repositori

import com.adrikhamid.ciptasehat.data.entity.Berobat
import kotlinx.coroutines.flow.Flow

interface BerobatRepo {
    fun getAllBerobatStream(): Flow<List<Berobat>>
    fun getBerobatStream(id:Int): Flow<Berobat?>
    suspend fun insertBerobat(berobat: Berobat)
    suspend fun updateBerobat(berobat: Berobat)
    suspend fun deleteBerobat(berobat: Berobat)
}