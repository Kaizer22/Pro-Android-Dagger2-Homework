package ru.otus.daggerhomework.presentation

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import ru.otus.daggerhomework.R
import ru.otus.daggerhomework.SingleAppComponent
import ru.otus.daggerhomework.di.MainActivityComponent

data class PopulateColorState(val color: Int? = null)

class MainActivity : FragmentActivity() {

    lateinit var mainActivityComponent: MainActivityComponent
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityComponent = SingleAppComponent.INSTANCE
            .mainActivityComponentFactory()
            .create(this)
        setContentView(R.layout.activity_main)
    }
}