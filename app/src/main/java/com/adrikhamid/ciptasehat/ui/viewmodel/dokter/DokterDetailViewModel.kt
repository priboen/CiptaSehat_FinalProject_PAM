package com.adrikhamid.ciptasehat.ui.viewmodel.dokter

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adrikhamid.ciptasehat.repositori.DokterRepo
import com.adrikhamid.ciptasehat.ui.screens.dokter.DokterDetailDestinasi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DokterDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val dokterRepo: DokterRepo
) : ViewModel() {

    private val dokterId: Int = checkNotNull(savedStateHandle[DokterDetailDestinasi.dokterIdArg])
    val uiState: StateFlow<DokterDetailUiState> =
        dokterRepo.getDokterStream(dokterId).filterNotNull()
            .map { DokterDetailUiState(detailDokter = it.toDetailDokter()) }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = DokterDetailUiState()
            )

    suspend fun deleteDokter() {
        dokterRepo.deleteDokter(uiState.value.detailDokter.toDokter())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class DokterDetailUiState(
    val outOfStock: Boolean = true,
    val detailDokter: DetailDokter = DetailDokter()
)