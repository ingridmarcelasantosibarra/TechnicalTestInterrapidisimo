package com.interrapidisimo.technical.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interrapidisimo.technical.core.utils.Resource
import com.interrapidisimo.technical.domain.model.Tabla
import com.interrapidisimo.technical.domain.usecase.FetchTablesUseCase
import com.interrapidisimo.technical.domain.usecase.GetLocalTablesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(
    private val fetchTablesUseCase: FetchTablesUseCase,
    private val getLocalTablesUseCase: GetLocalTablesUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(TablesUiState())
    val uiState: StateFlow<TablesUiState> = _uiState.asStateFlow()

    init {
        observeLocalTables()
        fetchTables()
    }

    private fun observeLocalTables() {
        getLocalTablesUseCase()
            .onEach { tablas ->
                _uiState.update {
                    it.copy(tablas = tablas, isLoading = false)
                }
            }
            .launchIn(viewModelScope)
    }

    private fun fetchTables() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = fetchTablesUseCase()
            if (result is Resource.Error) {
                _uiState.update {
                    it.copy(isLoading = false, error = result.message)
                }
            }
        }
    }


}

data class TablesUiState(
    val tablas: List<Tabla> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)