package ru.otus.daggerhomework.presentation

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import ru.otus.daggerhomework.di.ActivityContext
import ru.otus.daggerhomework.di.MutableUiState
import ru.otus.daggerhomework.utils.ColorGenerator
import javax.inject.Inject

class ProducerViewModel (
    private val colorGenerator: ColorGenerator,
    @ActivityContext private val context: Context,
    @MutableUiState
    private val colorGeneratorState: MutableStateFlow<PopulateColorState>
): ViewModel() {

    private fun generateColor() {
        if (context !is Activity) throw RuntimeException("Activity context is required")
        Toast.makeText(context, "Color sent", Toast.LENGTH_LONG).show()
    }

    fun onGenerateColorClick() {
        colorGeneratorState.update { s ->
            s.copy(color = colorGenerator.generateColor())
        }
        generateColor()
    }

    class Factory @Inject constructor(
        private val colorGenerator: ColorGenerator,
        @ActivityContext private val context: Context,
        @MutableUiState private val colorGeneratorState: MutableStateFlow<PopulateColorState>
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T = ProducerViewModel(
            colorGenerator,
            context,
            colorGeneratorState
        ) as T
    }
}