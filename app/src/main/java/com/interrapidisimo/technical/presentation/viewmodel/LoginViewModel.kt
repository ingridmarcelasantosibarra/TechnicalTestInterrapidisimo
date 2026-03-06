package com.interrapidisimo.technical.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interrapidisimo.technical.core.utils.Resource
import com.interrapidisimo.technical.domain.model.AppVersion
import com.interrapidisimo.technical.domain.usecase.CheckVersionUseCase
import com.interrapidisimo.technical.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val checkVersionUseCase: CheckVersionUseCase,
    private val loginUseCase: LoginUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    init {
        checkVersion()
    }

    private fun checkVersion() {
        viewModelScope.launch {
            _uiState.update { it.copy(isVersionLoading = true) }
            when (val result = checkVersionUseCase()) {
                is Resource.Success -> {
                    val appVersion = result.data
                    val status = when {
                        appVersion.isOutdated() -> VersionStatus.Outdated
                        appVersion.isNewer() -> VersionStatus.Newer
                        else -> VersionStatus.UpToDate
                    }
                    _uiState.update {
                        it.copy(
                            version = appVersion,
                            versionStatus = status,
                            isVersionLoading = false
                        )
                    }
                }
                is Resource.Error -> _uiState.update {
                    it.copy(versionError = result.message, isVersionLoading = false)
                }
                else -> {}
            }
        }
    }

    fun login() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoginLoading = true, loginError = null) }
            when (val result = loginUseCase()) {
                is Resource.Success -> _uiState.update {
                    it.copy(isLoginSuccess = true, isLoginLoading = false)
                }
                is Resource.Error -> _uiState.update {
                    it.copy(loginError = result.message, isLoginLoading = false)
                }
                else -> {}
            }
        }
    }

    fun onLoginNavigated() {
        _uiState.update { it.copy(isLoginSuccess = false) }
    }
}

data class LoginUiState(
    val version: AppVersion? = null,
    val versionStatus: VersionStatus? = null,
    val isVersionLoading: Boolean = false,
    val versionError: String? = null,
    val isLoginLoading: Boolean = false,
    val isLoginSuccess: Boolean = false,
    val loginError: String? = null
)

sealed class VersionStatus {
    data object UpToDate : VersionStatus()
    data object Outdated : VersionStatus()
    data object Newer : VersionStatus()
}