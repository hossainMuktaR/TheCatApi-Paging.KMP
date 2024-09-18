package com.hossian.thecatapikmp.domain.repository

import com.hossian.thecatapikmp.domain.model.Cat

interface CatRepository {
    suspend fun getCats(page: Int, limit: Int): List<Cat>

}