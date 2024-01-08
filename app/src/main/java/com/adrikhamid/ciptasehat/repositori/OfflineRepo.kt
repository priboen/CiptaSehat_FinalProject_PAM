package com.adrikhamid.ciptasehat.repositori

import com.adrikhamid.ciptasehat.data.dao.DokterDao
import com.adrikhamid.ciptasehat.data.entity.Dokter
import kotlinx.coroutines.flow.Flow

class OfflineRepo {
}

class OffRepoDokter(private val dokterDao: DokterDao):DokterRepo{
    override fun getAllDokterStream(): Flow<List<Dokter>> {
        return dokterDao.getAllDokter()
    }

    override fun getDokterStream(id: Int): Flow<Dokter?> {
        return dokterDao.getDokter(id)
    }

    override suspend fun insertDokter(dokter: Dokter) {
        return dokterDao.insert(dokter)
    }

    override suspend fun updateDokter(dokter: Dokter) {
        return dokterDao.update(dokter)
    }

    override suspend fun deleteDokter(dokter: Dokter) {
        return dokterDao.delete(dokter)
    }

}