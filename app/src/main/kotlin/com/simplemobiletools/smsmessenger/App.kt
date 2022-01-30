package com.simplemobiletools.smsmessenger

import android.app.Application
import com.androidnetworking.AndroidNetworking
import com.simplemobiletools.commons.extensions.checkUseEnglish

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        checkUseEnglish()
        AndroidNetworking.initialize(applicationContext)
    }
}
