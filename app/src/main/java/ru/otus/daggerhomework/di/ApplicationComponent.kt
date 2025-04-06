package ru.otus.daggerhomework.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class ApplicationContext

@Singleton
@Component
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @ApplicationContext @BindsInstance context: Context
        ): ApplicationComponent
    }

    fun mainActivityComponentFactory(): MainActivityComponent.Factory
}