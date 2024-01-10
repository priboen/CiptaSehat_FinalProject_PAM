package com.adrikhamid.ciptasehat.ui.viewmodel.pasien

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adrikhamid.ciptasehat.repositori.PasienRepo
import com.adrikhamid.ciptasehat.ui.screens.pasien.PasienDetailDestinasi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class PasienDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val pasienRepo: PasienRepo
) : ViewModel() {

    private val pasienId: Int = checkNotNull(savedStateHandle[PasienDetailDestinasi.pasienIdArg])
    val uiState: StateFlow<PasienDetailUiState> =
        pasienRepo.getPasienStream(pasienId).filterNotNull()
            .map { PasienDetailUiState(detailPasien = it.toDetailPasien()) }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = PasienDetailUiState()
            )

    suspend fun deletePasien() {
        pasienRepo.deletePasien(uiState.value.detailPasien.toPasien())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class PasienDetailUiState(
    val outOfStock: Boolean = true,
    val detailPasien: DetailPasien = DetailPasien()
)
