package com.adrikhamid.ciptasehat.ui.viewmodel.dokter

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adrikhamid.ciptasehat.repositori.DokterRepo
import com.adrikhamid.ciptasehat.ui.screens.dokter.DokterEditDestinasi
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class DokterEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val dokterRepo: DokterRepo
) : ViewModel() {

    var dokterUiState by mutableStateOf(UIStateDokter())
        private set

    private val itemId: Int = checkNotNull(savedStateHandle[DokterEditDestinasi.dokterIdArg])

    init {
        viewModelScope.launch {
            dokterUiState = dokterRepo.getDokterStream(itemId)
                .filterNotNull()
                .first()
                .toUiStateDokter(true)
        }
    }

    suspend fun updateDokter() {
        if (validasiInput(dokterUiState.detailDokter)) {
            dokterRepo.updateDokter(dokterUiState.detailDokter.toDokter())
        }
        else {
            println("Data tidak valid")
        }
    }

    fun updateUiState(detailDokter: DetailDokter) {
        dokterUiState =
            UIStateDokter(detailDokter = detailDokter, isEntryValid = validasiInput(detailDokter))
    }

    private fun validasiInput(uiState: DetailDokter = dokterUiState.detailDokter ): Boolean {
        return with(uiState) {
            namaDokter.isNotBlank() && nohp.isNotBlank() && spesialis.isNotBlank()
        }
    }


}