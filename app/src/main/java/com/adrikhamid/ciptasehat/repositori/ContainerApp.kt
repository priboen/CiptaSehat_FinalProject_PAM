package com.adrikhamid.ciptasehat.repositori

import android.content.Context
import com.adrikhamid.ciptasehat.data.DatabaseCS

interface ContainerApp {
    val dokterRepo: DokterRepo
    val pasienRepo: PasienRepo
    val berobatRepo: BerobatRepo
}

class ContainerDataApp(private val context: Context) : ContainerApp {
    override val dokterRepo: DokterRepo by lazy {
        OffRepoDokter(DatabaseCS.getDatabase(context).dokterDao())
    }

    override val pasienRepo: PasienRepo by lazy {
        OffRepoPasien(DatabaseCS.getDatabase(context).pasienDao())
    }

    override val berobatRepo: BerobatRepo by lazy {
        OffRepoBerobat(DatabaseCS.getDatabase(context).berobatDao())
    }
}