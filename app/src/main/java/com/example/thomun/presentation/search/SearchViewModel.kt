package com.example.thomun.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thomun.domain.usecase.GetSearchResultsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchResultsUseCase: GetSearchResultsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<SearchState>(SearchState.Loading)
    val state: StateFlow<SearchState> = _state

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.Search -> search(event.query)
            is SearchEvent.ShowError -> _state.value = SearchState.Error(event.message)
        }
    }

    private fun search(query: String) {
        _state.value = SearchState.Loading
        viewModelScope.launch {
            getSearchResultsUseCase.invoke().onSuccess {
                _state.value = SearchState.Success(it)
            }.onFailure {
                _state.value = SearchState.Error(it.message ?: "Unknown error")
            }
        }
    }
} 