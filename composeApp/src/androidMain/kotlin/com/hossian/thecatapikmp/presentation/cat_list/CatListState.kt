package com.hossian.thecatapikmp.presentation.cat_list

import com.hossian.thecatapikmp.domain.model.Cat

data class CatListState (
    val isLoading: Boolean = false,
    var cats: List<Cat> = emptyList(),
    val error: String = ""
)
