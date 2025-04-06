package ru.otus.daggerhomework.di

import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import ru.otus.daggerhomework.utils.ColorGenerator
import ru.otus.daggerhomework.utils.ColorGeneratorImpl
import ru.otus.daggerhomework.presentation.ProducerFragment

@Subcomponent(
    modules = [ProducerFragmentModule::class]
)
interface ProducerFragmentComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): ProducerFragmentComponent
    }

    fun inject(fragment: ProducerFragment)
}

@Module
interface ProducerFragmentModule {
    @Binds
    fun colorGenerator(colorGeneratorImpl: ColorGeneratorImpl): ColorGenerator
}
