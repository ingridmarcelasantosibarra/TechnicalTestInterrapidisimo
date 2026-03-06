package com.interrapidisimo.technical.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interrapidisimo.technical.core.utils.Resource
import com.interrapidisimo.technical.domain.model.AppVersion
import com.interrapidisimo.technical.domain.model.User
import com.interrapidisimo.technical.domain.usecase.CheckVersionUseCase
import com.interrapidisimo.technical.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val checkVersionUseCase: CheckVersionUseCase,
    private val loginUseCase: LoginUseCase,
) : ViewModel() {
    private val _versionState = MutableStateFlow<Resource<AppVersion>>(Resource.Loading)
    val versionState: StateFlow<Resource<AppVersion>> = _versionState.asStateFlow()
    private val _loginState = MutableStateFlow<Resource<User>?>(null)
    val loginState: StateFlow<Resource<User>?> = _loginState.asStateFlow()

    init {
        checkVersion()
    }

    private fun checkVersion() {
        viewModelScope.launch {
            _versionState.value = Resource.Loading
            _versionState.value = checkVersionUseCase()
        }
    }

    fun login() {
        viewModelScope.launch {
            _loginState.value = Resource.Loading
            _loginState.value = loginUseCase()
        }
    }
}