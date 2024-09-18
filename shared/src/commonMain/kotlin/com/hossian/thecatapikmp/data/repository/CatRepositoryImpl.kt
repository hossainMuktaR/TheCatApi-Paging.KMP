package com.hossian.thecatapikmp.data.repository

import com.hossian.thecatapikmp.data.remote.CatApiService
import com.hossian.thecatapikmp.data.remote.CatApiServiceImpl
import com.hossian.thecatapikmp.domain.model.Cat
import com.hossian.thecatapikmp.domain.repository.CatRepository

class CatRepositoryImpl(
    private val apiService: CatApiService = CatApiServiceImpl()
): CatRepository {

    override suspend fun getCats(page: Int, limit: Int): List<Cat> {
        return apiService.getCats(page, limit)
    }
}