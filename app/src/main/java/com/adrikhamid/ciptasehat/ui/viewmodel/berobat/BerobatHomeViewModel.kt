package com.adrikhamid.ciptasehat.ui.viewmodel.berobat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adrikhamid.ciptasehat.data.entity.Berobat
import com.adrikhamid.ciptasehat.data.entity.Dokter
import com.adrikhamid.ciptasehat.repositori.BerobatRepo
import com.adrikhamid.ciptasehat.repositori.DokterRepo
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class BerobatHomeViewModel(private val berobatRepo: BerobatRepo) : ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val homeUiState: StateFlow<HomeUiState> = berobatRepo.getAllBerobatStream().filterNotNull()
        .map { HomeUiState(listBerobat = it.toList()) }.stateIn(
            scope = viewModelScope, started = SharingStarted.WhileSubscribed(
                TIMEOUT_MILLIS
            ), initialValue = HomeUiState()
        )
}
data class HomeUiState(
    val listBerobat: List<Berobat> = listOf()
)