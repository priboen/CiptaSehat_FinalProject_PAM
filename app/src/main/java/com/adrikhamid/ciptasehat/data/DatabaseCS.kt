package com.adrikhamid.ciptasehat.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.adrikhamid.ciptasehat.data.dao.BerobatDao
import com.adrikhamid.ciptasehat.data.dao.DokterDao
import com.adrikhamid.ciptasehat.data.dao.PasienDao
import com.adrikhamid.ciptasehat.data.entity.Dokter
import com.adrikhamid.ciptasehat.data.entity.Pasien
import kotlinx.coroutines.InternalCoroutinesApi

@Database(entities = [Dokter::class, Pasien::class], version = 1, exportSchema = false)
abstract class DatabaseCS : RoomDatabase() {
    abstract fun dokterDao(): DokterDao
    abstract fun pasienDao(): PasienDao
    abstract fun berobatDao(): BerobatDao

    companion object {
        @Volatile
        private var Instance: DatabaseCS? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context): DatabaseCS {
            return (Instance ?: synchronized(this) {
                Room.databaseBuilder(context, DatabaseCS::class.java, "ciptaSehat_database").build()
                    .also { Instance = it }
            })
        }
    }
}