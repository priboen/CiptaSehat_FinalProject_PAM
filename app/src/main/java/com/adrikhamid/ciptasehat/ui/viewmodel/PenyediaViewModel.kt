package com.adrikhamid.ciptasehat.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.adrikhamid.ciptasehat.CiptaSehat
import com.adrikhamid.ciptasehat.ui.viewmodel.dokter.DokterEntryViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer { DokterEntryViewModel(ciptaSehat().container.dokterRepo) }
    }
}

fun CreationExtras.ciptaSehat(): CiptaSehat =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as CiptaSehat)