package com.hossian.thecatapikmp.data.remote

import com.hossian.thecatapikmp.domain.model.Cat

interface CatApiService {
    suspend fun getCats(page: Int, limit: Int): List<Cat>
}