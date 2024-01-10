package com.adrikhamid.ciptasehat.ui.viewmodel.pasien

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adrikhamid.ciptasehat.repositori.PasienRepo
import com.adrikhamid.ciptasehat.ui.screens.pasien.PasienEditDestinasi
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class PasienEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val pasienRepo: PasienRepo
) : ViewModel() {

    var PasienUiState by mutableStateOf(UIStatePasien())
        private set

    private val itemId: Int = checkNotNull(savedStateHandle[PasienEditDestinasi.pasienIdArg])

    init {
        viewModelScope.launch {
            PasienUiState = pasienRepo.getPasienStream(itemId)
                .filterNotNull()
                .first()
                .toUiStatePasien(true)
        }
    }

    suspend fun updatePasien() {
        if (validasiInput(PasienUiState.detailPasien)) {
            pasienRepo.updatePasien(PasienUiState.detailPasien.toPasien())
        }
        else {
            println("Data tidak valid")
        }
    }

    fun updateUiState(detailPasien: DetailPasien) {
        PasienUiState =
            UIStatePasien(detailPasien = detailPasien, isEntryValid = validasiInput(detailPasien))
    }

    private fun validasiInput(uiState: DetailPasien = PasienUiState.detailPasien ): Boolean {
        return with(uiState) {
            namaPasien.isNotBlank() && alamat.isNotBlank()
        }
    }


}