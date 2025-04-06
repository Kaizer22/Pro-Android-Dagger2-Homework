package ru.otus.daggerhomework.di

import dagger.Subcomponent
import ru.otus.daggerhomework.presentation.ReceiverFragment

@Subcomponent
interface ReceiverFragmentComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): ReceiverFragmentComponent
    }

    fun inject(fragment: ReceiverFragment)
}
