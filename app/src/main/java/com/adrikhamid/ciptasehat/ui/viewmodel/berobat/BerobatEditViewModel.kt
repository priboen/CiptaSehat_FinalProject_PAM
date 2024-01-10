package com.adrikhamid.ciptasehat.ui.viewmodel.berobat

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adrikhamid.ciptasehat.repositori.BerobatRepo
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class BerobatEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val berobatRepo: BerobatRepo
) : ViewModel() {

    var BerobatUiState by mutableStateOf(UIStateBerobat())
        private set

    private val itemId: Int = checkNotNull(savedStateHandle[BerobatEditDestinasi.BerobatIdArg])

    init {
        viewModelScope.launch {
            BerobatUiState = berobatRepo.getBerobatStream(itemId)
                .filterNotNull()
                .first()
                .toUiStateBerobat(true)
        }
    }

    suspend fun updateBerobat() {
        if (validasiInput(BerobatUiState.detailBerobat)) {
            berobatRepo.updateBerobat(BerobatUiState.detailBerobat.toBerobat())
        }
        else {
            println("Data tidak valid")
        }
    }

    fun updateUiState(detailBerobat: DetailBerobat) {
        BerobatUiState =
            UIStateBerobat(detailBerobat = detailBerobat, isEntryValid = validasiInput(detailBerobat))
    }

    private fun validasiInput(uiState: DetailBerobat = BerobatUiState.detailBerobat ): Boolean {
        return with(uiState) {
            pasienNama.isNotBlank() && dokterNama.isNotBlank() && jenisPerawatan.isNotBlank()
        }
    }


}