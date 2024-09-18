package com.hossian.thecatapikmp.presentation.cat_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hossian.thecatapikmp.common.Resource
import com.hossian.thecatapikmp.domain.model.Cat
import com.hossian.thecatapikmp.domain.usecases.GetCatsImageUseCase
import kotlinx.coroutines.launch

class CatListViewModel(
    private val getCoinsUseCase: GetCatsImageUseCase = GetCatsImageUseCase()
) : ViewModel() {

    private val _state = mutableStateOf(CatListState())
    val state: State<CatListState> = _state

    // pagination state
    private var canLoadMorePages = true
    private var page = 1
    private var limit = 15

    fun getCatImages() {
        if (!canLoadMorePages && state.value.isLoading) return
        viewModelScope.launch {
            getCoinsUseCase.execute(page, limit).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = CatListState(
                            isLoading = false,
                            cats = state.value.cats + (result.data ?: emptyList())
                        )
                        if (result.data?.count()!! < limit) {
                            this@CatListViewModel.canLoadMorePages = false
                        }
                        page += 1

                    }

                    is Resource.Error -> {
                        _state.value = CatListState(
                            error = result.message ?: "An unexpected error occured"
                        )
                    }

                    is Resource.Loading -> {
                        _state.value = state.value.copy(isLoading = true)
                    }
                }
            }
        }
    }
}
