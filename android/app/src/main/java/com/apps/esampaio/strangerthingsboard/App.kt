package com.apps.esampaio.strangerthingsboard

import android.app.Application

/**
 * Created by eduar on 02/06/2018.
 */

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        var instance: App? = null
            private set
    }
}
