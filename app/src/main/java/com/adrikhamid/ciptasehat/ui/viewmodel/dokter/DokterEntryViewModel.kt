package com.adrikhamid.ciptasehat.ui.viewmodel.dokter

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.adrikhamid.ciptasehat.data.entity.Dokter
import com.adrikhamid.ciptasehat.repositori.DokterRepo

class DokterEntryViewModel(private val dokterRepo: DokterRepo) : ViewModel() {
    var uiStateDokter by mutableStateOf(UIStateDokter())
        private set

    private fun validasiInput(uiState: DetailDokter = uiStateDokter.detailDokter): Boolean {
        return with(uiState) {
            namaDokter.isNotBlank() && nohp.isNotBlank() && spesialis.isNotBlank()
        }
    }

    fun updateUiState(detailDokter: DetailDokter) {
        uiStateDokter =
            UIStateDokter(detailDokter = detailDokter, isEntryValid = validasiInput(detailDokter))
    }

    suspend fun saveDokter() {
        if (validasiInput()) {
            dokterRepo.insertDokter(uiStateDokter.detailDokter.toDokter())
        }
    }
}

data class UIStateDokter(
    val detailDokter: DetailDokter = DetailDokter(),
    val isEntryValid: Boolean = false
)

data class DetailDokter(
    val id: Int = 0,
    val namaDokter: String = "",
    val jkDokter: String = "",
    val nohp: String = "",
    val spesialis: String = ""
)

fun DetailDokter.toDokter(): Dokter = Dokter(
    id = id,
    namaDokter = namaDokter,
    jkDokter = jkDokter,
    nohp = nohp,
    spesialis = spesialis
)
fun Dokter.toUiStateDokter(isEntryValid: Boolean = false): UIStateDokter = UIStateDokter(
    detailDokter = this.toDetailDokter(),
    isEntryValid = isEntryValid
)

fun Dokter.toDetailDokter(): DetailDokter = DetailDokter(
    id = id,
    namaDokter = namaDokter,
    jkDokter = jkDokter,
    nohp = nohp,
    spesialis = spesialis
)