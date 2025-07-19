package com.example.thomun.presentation.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thomun.domain.usecase.GetSearchResultsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchResultsUseCase: GetSearchResultsUseCase
) : ViewModel() {

    var state by mutableStateOf(SearchState())
        private set

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.SetQuery -> state = state.copy(query = event.query)
            is SearchEvent.ClearResults -> state = state.copy(sections = emptyList())
            is SearchEvent.Search -> search(event.query)
        }
    }

    private fun search(query: String) {
        state = state.copy(loading = true, error = false, errorMessage = "")
        viewModelScope.launch {
            getSearchResultsUseCase.invoke(query).onSuccess {
                state = state.copy(
                    loading = false,
                    sections = it.sections
                )
            }.onFailure {
                state = state.copy(
                    loading = false,
                    error = true,
                    errorMessage = "Something went wrong, please try again."
                )
            }
        }
    }
} 