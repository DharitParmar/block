package com.example.employeedirectory.common.platform

import android.app.*
import dagger.hilt.android.HiltAndroidApp
import timber.log.*
import timber.log.Timber.DebugTree

@HiltAndroidApp
class EmployeeDirectoryApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(DebugTree())
    }

}