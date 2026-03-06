package com.interrapidisimo.technical.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interrapidisimo.technical.core.utils.Resource
import com.interrapidisimo.technical.domain.model.Locality
import com.interrapidisimo.technical.domain.usecase.LocalitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocalitiesViewModel @Inject constructor(
    private val localitiesUseCase: LocalitiesUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(LocalitiesUiState())
    val uiState: StateFlow<LocalitiesUiState> = _uiState.asStateFlow()

    init {
        getLocalities()
    }

    private fun getLocalities() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            when (val result = localitiesUseCase()) {
                is Resource.Success -> _uiState.update {
                    it.copy(localities = result.data, isLoading = false)
                }
                is Resource.Error -> _uiState.update {
                    it.copy(error = result.message, isLoading = false)
                }
                else -> {}
            }
        }
    }


}

data class LocalitiesUiState(
    val localities: List<Locality> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)