package com.example.yoga.asanas.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yoga.domain.repository.YogaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AsanasViewModel @Inject constructor(
    private val repository: YogaRepository
) : ViewModel() {

    private val _asanas = MutableStateFlow<AsanasUiState>(AsanasUiState.Loading)
    val asanas = _asanas.asStateFlow()

    init {
        fetchAsanas()
    }

    private fun fetchAsanas() {
        viewModelScope.launch {
            val result = try {
                val asanas = repository.fetchPoses()
                AsanasUiState.Success(asanas)
            } catch (e: Exception) {
                AsanasUiState.Error(e.message.orEmpty())
            }
            _asanas.emit(result)
        }
    }
}
