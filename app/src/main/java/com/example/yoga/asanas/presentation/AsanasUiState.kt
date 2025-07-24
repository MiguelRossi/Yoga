package com.example.yoga.asanas.presentation

import com.example.yoga.domain.model.Asana

sealed interface AsanasUiState {
    object Loading : AsanasUiState
    data class Success(val asanas: List<Asana>) : AsanasUiState
    data class Error(val message: String) : AsanasUiState
}
