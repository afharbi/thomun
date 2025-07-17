package com.example.thomun.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thomun.domain.usecase.GetHomeSectionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeSectionsUseCase: GetHomeSectionsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<HomeState>(HomeState.Loading)
    val state: StateFlow<HomeState> = _state


    init {
        loadHomeSections()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.LoadHome -> loadHomeSections()
            is HomeEvent.ShowError -> _state.value = HomeState.Error(event.message)
        }
    }

    private fun loadHomeSections() {
        Log.d("HomeViewModel", "Calling loadHomeSections")
        _state.value = HomeState.Loading
        viewModelScope.launch {
            delay(2000L)
            getHomeSectionsUseCase.invoke().onSuccess {
                Log.d("HomeViewModel", "API Success: $it")
                _state.value = HomeState.Success(data = it)
            }.onFailure {
                Log.e("HomeViewModel", "API Failure", it)
                _state.value = HomeState.Error(it.message ?: "Unknown error")
            }
        }
    }
} 