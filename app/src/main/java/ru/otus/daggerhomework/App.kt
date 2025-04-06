package ru.otus.daggerhomework

import android.app.Application
import android.content.Context
import ru.otus.daggerhomework.di.DaggerApplicationComponent

object SingleAppComponent {
    private lateinit var appContext: Context
    val INSTANCE by lazy {
        DaggerApplicationComponent.factory()
            .create(appContext)
    }

    fun initAppComponent(context: Context) {
        this.appContext = context
    }
}

class App : Application() {
    override fun onCreate() {
        SingleAppComponent.initAppComponent(applicationContext)
        super.onCreate()
    }
}