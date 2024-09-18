package com.hossian.thecatapikmp.data.remote

import com.hossian.thecatapikmp.common.CatApiRoutes
import com.hossian.thecatapikmp.common.Constant
import com.hossian.thecatapikmp.domain.model.Cat
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.URLBuilder
import io.ktor.http.Url
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class CatApiServiceImpl : CatApiService {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
        defaultRequest {
            header("x-api-key", Constant.API_KEY)
        }
    }

    override suspend fun getCats(page: Int, limit: Int): List<Cat> {
        return try {
            val url = URLBuilder(CatApiRoutes.GET_CATS).apply {
                parameters.append("has_breeds", "true")
                parameters.append("page", page.toString())
                parameters.append("limit", limit.toString())
            }.build()
            val response = client.get(url = url)
            println("the response: ${response.bodyAsText()}")
            response.body()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            emptyList()
        }
    }
}