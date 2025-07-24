package com.example.yoga.asana_detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yoga.domain.repository.YogaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: YogaRepository
) : ViewModel() {

    private val _pose = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val pose = _pose.asStateFlow()

    fun onScreenStarted(poseId: Int) {
        viewModelScope.launch {
            val result = try {
                val asana = repository.fetchPose(poseId)
                DetailUiState.Success(asana = asana)
            } catch (e: Exception) {
                DetailUiState.Error(message = e.message.orEmpty())
            }
            _pose.emit(value = result)
        }
    }
}
