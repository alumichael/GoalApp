package com.michealalu.goalapp

import android.R
import android.app.Application
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class GoalApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        //dark //night mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    }

}