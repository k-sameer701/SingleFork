package com.example.singlefork

import android.app.Application
import com.example.singlefork.data.AppContainer
import com.example.singlefork.data.DefaultAppContainer

class RecipeApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}