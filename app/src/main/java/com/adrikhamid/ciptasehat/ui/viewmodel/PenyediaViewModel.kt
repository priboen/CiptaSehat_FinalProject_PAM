package com.adrikhamid.ciptasehat.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.adrikhamid.ciptasehat.CiptaSehat
import com.adrikhamid.ciptasehat.ui.viewmodel.berobat.BerobatDetailViewModel
import com.adrikhamid.ciptasehat.ui.viewmodel.berobat.BerobatEditViewModel
import com.adrikhamid.ciptasehat.ui.viewmodel.berobat.BerobatEntryViewModel
import com.adrikhamid.ciptasehat.ui.viewmodel.berobat.BerobatHomeViewModel
import com.adrikhamid.ciptasehat.ui.viewmodel.dokter.DokterDetailViewModel
import com.adrikhamid.ciptasehat.ui.viewmodel.dokter.DokterEditViewModel
import com.adrikhamid.ciptasehat.ui.viewmodel.dokter.DokterEntryViewModel
import com.adrikhamid.ciptasehat.ui.viewmodel.dokter.DokterHomeViewModel
import com.adrikhamid.ciptasehat.ui.viewmodel.pasien.PasienDetailViewModel
import com.adrikhamid.ciptasehat.ui.viewmodel.pasien.PasienEditViewModel
import com.adrikhamid.ciptasehat.ui.viewmodel.pasien.PasienEntryViewModel
import com.adrikhamid.ciptasehat.ui.viewmodel.pasien.PasienHomeViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer { DokterHomeViewModel(ciptaSehat().container.dokterRepo) }
        initializer { DokterEntryViewModel(ciptaSehat().container.dokterRepo) }
        initializer {
            DokterEditViewModel(
                createSavedStateHandle(),
                ciptaSehat().container.dokterRepo
            )
        }
        initializer {
            DokterDetailViewModel(
                createSavedStateHandle(),
                ciptaSehat().container.dokterRepo
            )
        }
        //Pasien
        initializer { PasienHomeViewModel(ciptaSehat().container.pasienRepo) }
        initializer { PasienEntryViewModel(ciptaSehat().container.pasienRepo) }
        initializer {
            PasienDetailViewModel(
                createSavedStateHandle(),
                ciptaSehat().container.pasienRepo
            )
        }
        initializer {
            PasienEditViewModel(
                createSavedStateHandle(),
                ciptaSehat().container.pasienRepo
            )
        }
        //Berobat
        initializer {
            BerobatHomeViewModel(ciptaSehat().container.berobatRepo)
        }
        initializer {
            BerobatEntryViewModel(ciptaSehat().container.berobatRepo)
        }
        initializer {
            BerobatEditViewModel(
                createSavedStateHandle(),
                ciptaSehat().container.berobatRepo
            )
        }
        initializer {
            BerobatDetailViewModel(createSavedStateHandle(), ciptaSehat().container.berobatRepo)
        }
    }
}

fun CreationExtras.ciptaSehat(): CiptaSehat =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as CiptaSehat)