package com.hossian.thecatapikmp.domain.usecases

import com.hossian.thecatapikmp.common.Resource
import com.hossian.thecatapikmp.data.repository.CatRepositoryImpl
import com.hossian.thecatapikmp.domain.model.Cat
import com.hossian.thecatapikmp.domain.repository.CatRepository
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCatsImageUseCase(
    private val repository: CatRepository = CatRepositoryImpl()
) {
    fun execute(page: Int, limit: Int): Flow<Resource<List<Cat>>> = flow {
        try {
            emit(Resource.Loading())
            val coin = repository.getCats(page, limit)
            emit(Resource.Success(coin))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }    }

}