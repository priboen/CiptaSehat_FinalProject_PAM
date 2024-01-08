package com.adrikhamid.ciptasehat

import android.app.Application
import com.adrikhamid.ciptasehat.repositori.ContainerApp
import com.adrikhamid.ciptasehat.repositori.ContainerDataApp

class CiptaSehat : Application() {
    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        container = ContainerDataApp(this)
    }
}