package com.example.yoga.asana_detail.presentation

import com.example.yoga.domain.model.Asana

sealed interface DetailUiState {
    object Loading : DetailUiState
    class Success(val asana: Asana) : DetailUiState
    class Error(val message: String) : DetailUiState
}
