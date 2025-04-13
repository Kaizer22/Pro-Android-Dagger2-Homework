package ru.otus.daggerhomework.di

import android.content.Context
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.otus.daggerhomework.presentation.PopulateColorState
import javax.inject.Qualifier

@Qualifier
annotation class ActivityContext

@Subcomponent(
    modules = [MainActivityModule::class],
)
@ActivityScope
interface MainActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @ActivityContext @BindsInstance activityContext: Context,
        ): MainActivityComponent
    }


    fun providesMutableUiState(): MutableStateFlow<PopulateColorState>
    fun providesUiState(): StateFlow<PopulateColorState>

    fun producerFragmentComponent(): ProducerFragmentComponent.Factory
    fun receiverFragmentComponent(): ReceiverFragmentComponent.Factory
}

@Module(
    subcomponents = [
        ReceiverFragmentComponent::class,
        ProducerFragmentComponent::class,
    ]
)
class MainActivityModule {

    @Provides
    @ActivityScope
    fun providesUiState(
        mutable: MutableStateFlow<PopulateColorState>
    ): StateFlow<PopulateColorState> = mutable.asStateFlow()

    @Provides
    @ActivityScope
    fun provideMutableUiState(): MutableStateFlow<PopulateColorState> =
        MutableStateFlow(PopulateColorState())
}