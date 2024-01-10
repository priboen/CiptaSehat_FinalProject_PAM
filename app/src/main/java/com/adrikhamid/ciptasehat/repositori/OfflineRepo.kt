package com.adrikhamid.ciptasehat.repositori

import com.adrikhamid.ciptasehat.data.dao.BerobatDao
import com.adrikhamid.ciptasehat.data.dao.DokterDao
import com.adrikhamid.ciptasehat.data.dao.PasienDao
import com.adrikhamid.ciptasehat.data.entity.Berobat
import com.adrikhamid.ciptasehat.data.entity.Dokter
import com.adrikhamid.ciptasehat.data.entity.Pasien
import kotlinx.coroutines.flow.Flow


class OffRepoDokter(private val dokterDao: DokterDao) : DokterRepo {
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

class OffRepoPasien(private val pasienDao: PasienDao) : PasienRepo {
    override fun getAllPasienStream(): Flow<List<Pasien>> {
        return pasienDao.getAllPasien()
    }

    override fun getPasienStream(id: Int): Flow<Pasien?> {
        return pasienDao.getPasien(id)
    }

    override suspend fun insertPasien(pasien: Pasien) {
        return pasienDao.insert(pasien)
    }

    override suspend fun updatePasien(pasien: Pasien) {
        return pasienDao.update(pasien)
    }

    override suspend fun deletePasien(pasien: Pasien) {
        pasienDao.delete(pasien)
    }
}

class OffRepoBerobat(private val berobatDao: BerobatDao) : BerobatRepo {
    override fun getAllBerobatStream(): Flow<List<Berobat>> {
        return berobatDao.getAllBerobat()
    }

    override fun getBerobatStream(id: Int): Flow<Berobat?> {
        return berobatDao.getBerobat(id)
    }

    override suspend fun insertBerobat(berobat: Berobat) {
        return berobatDao.insert(berobat)
    }

    override suspend fun updateBerobat(berobat: Berobat) {
        berobatDao.update(berobat)
    }

    override suspend fun deleteBerobat(berobat: Berobat) {
        return berobatDao.delete(berobat)
    }
}