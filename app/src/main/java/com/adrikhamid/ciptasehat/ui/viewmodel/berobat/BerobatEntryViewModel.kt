package com.adrikhamid.ciptasehat.ui.viewmodel.berobat

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.adrikhamid.ciptasehat.data.entity.Berobat
import com.adrikhamid.ciptasehat.repositori.BerobatRepo

class BerobatEntryViewModel(private val BerobatRepo: BerobatRepo) : ViewModel() {
    var uiStateBerobat by mutableStateOf(UIStateBerobat())
        private set

    private fun validasiInput(uiState: DetailBerobat = uiStateBerobat.detailBerobat): Boolean {
        return with(uiState) {
            pasienNama.isNotBlank() && dokterNama.isNotBlank() && jenisPerawatan.isNotBlank()
        }
    }

    fun updateUiState(detailBerobat: DetailBerobat) {
        uiStateBerobat =
            UIStateBerobat(detailBerobat = detailBerobat, isEntryValid = validasiInput(detailBerobat))
    }

    suspend fun saveBerobat() {
        if (validasiInput()) {
            BerobatRepo.insertBerobat(uiStateBerobat.detailBerobat.toBerobat())
        }
    }
}

data class UIStateBerobat(
    val detailBerobat: DetailBerobat = DetailBerobat(),
    val isEntryValid: Boolean = false
)

data class DetailBerobat(
    val id: Int = 0,
    val pasienNama: String = "",
    val dokterNama: String = "",
    val jenisPerawatan: String = "",
)

fun DetailBerobat.toBerobat(): Berobat = Berobat(
    id = id,
    pasienNama = pasienNama,
    dokterNama = dokterNama,
    jenisPerawatan = jenisPerawatan,
)
fun Berobat.toUiStateBerobat(isEntryValid: Boolean = false): UIStateBerobat = UIStateBerobat(
    detailBerobat = this.toDetailBerobat(),
    isEntryValid = isEntryValid
)

fun Berobat.toDetailBerobat(): DetailBerobat = DetailBerobat(
    id = id,
    pasienNama = pasienNama,
    dokterNama = dokterNama,
    jenisPerawatan = jenisPerawatan,
)