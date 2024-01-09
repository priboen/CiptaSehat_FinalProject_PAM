package com.adrikhamid.ciptasehat.ui.viewmodel.pasien

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.adrikhamid.ciptasehat.data.entity.Pasien
import com.adrikhamid.ciptasehat.repositori.PasienRepo

class PasienEntryViewModel(private val pasienRepo: PasienRepo) : ViewModel() {
    var uiStatePasien by mutableStateOf(UIStatePasien())
        private set

    private fun validasiInput(uiState: DetailPasien = uiStatePasien.detailPasien): Boolean {
        return with(uiState) {
            namaPasien.isNotBlank() && alamat.isNotBlank()
        }
    }

    fun updateUiState(detailPasien: DetailPasien) {
        uiStatePasien =
            UIStatePasien(detailPasien = detailPasien, isEntryValid = validasiInput(detailPasien))
    }

    suspend fun savePasien() {
        if (validasiInput()) {
            pasienRepo.insertPasien(uiStatePasien.detailPasien.toPasien())
        }
    }
}

data class UIStatePasien(
    val detailPasien: DetailPasien = DetailPasien(),
    val isEntryValid: Boolean = false
)

data class DetailPasien(
    val id: Int = 0,
    val namaPasien: String = "",
    val tglLahir: String = "",
    val alamat: String = ""
)

fun DetailPasien.toPasien(): Pasien = Pasien(
    id = id,
    namaPasien = namaPasien,
    tglLahir = tglLahir,
    alamat = alamat,
)
fun Pasien.toUiStatePasien(isEntryValid: Boolean = false): UIStatePasien = UIStatePasien(
    detailPasien = this.toDetailPasien(),
    isEntryValid = isEntryValid
)

fun Pasien.toDetailPasien(): DetailPasien = DetailPasien(
    id = id,
    namaPasien = namaPasien,
    tglLahir = tglLahir,
    alamat = alamat,
)