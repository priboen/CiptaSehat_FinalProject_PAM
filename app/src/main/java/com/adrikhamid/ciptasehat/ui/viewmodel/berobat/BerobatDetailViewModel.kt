package com.adrikhamid.ciptasehat.ui.viewmodel.berobat

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adrikhamid.ciptasehat.repositori.BerobatRepo
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class BerobatDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val berobatRepo: BerobatRepo
) : ViewModel() {

    private val BerobatId: Int = checkNotNull(savedStateHandle[BerobatDetailDestinasi.BerobatIdArg])
    val uiState: StateFlow<BerobatDetailUiState> =
        berobatRepo.getBerobatStream(BerobatId).filterNotNull()
            .map { BerobatDetailUiState(detailBerobat = it.toDetailBerobat()) }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = BerobatDetailUiState()
            )

    suspend fun deleteBerobat() {
        berobatRepo.deleteBerobat(uiState.value.detailBerobat.toBerobat())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class BerobatDetailUiState(
    val outOfStock: Boolean = true,
    val detailBerobat: DetailBerobat = DetailBerobat()
)