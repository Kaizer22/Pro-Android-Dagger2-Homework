package ru.otus.daggerhomework.di

import android.content.Context
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.otus.daggerhomework.presentation.PopulateColorState
import javax.inject.Qualifier

@Qualifier
annotation class ActivityContext

@Qualifier
annotation class UiState

@Qualifier
annotation class MutableUiState

@Subcomponent(
    modules = [MainActivityModule::class],
)
interface MainActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @ActivityContext @BindsInstance activityContext: Context,
        ): MainActivityComponent
    }

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
    private val _uiState = MutableStateFlow(PopulateColorState())

    @Provides
    @UiState
    fun providesUiState(): StateFlow<PopulateColorState> = _uiState

    @Provides
    @MutableUiState
    fun provideMutableUiState(): MutableStateFlow<PopulateColorState> = _uiState
}