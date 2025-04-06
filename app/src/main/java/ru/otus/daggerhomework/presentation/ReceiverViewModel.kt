package ru.otus.daggerhomework.presentation

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.StateFlow
import ru.otus.daggerhomework.di.ApplicationContext
import ru.otus.daggerhomework.di.UiState
import javax.inject.Inject

class ReceiverViewModel (
    @ApplicationContext private val context: Context,
    @UiState val colorGeneratorState: StateFlow<PopulateColorState>
): ViewModel() {

    fun observeColors() {
        if (context !is Application) throw RuntimeException("Application context is required")
        Toast.makeText(context, "Color received", Toast.LENGTH_LONG).show()
    }

    class Factory @Inject constructor(
        @ApplicationContext private val context: Context,
        @UiState val colorGeneratorState: StateFlow<PopulateColorState>
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T = ReceiverViewModel(
            context,
            colorGeneratorState
        ) as T
    }
}