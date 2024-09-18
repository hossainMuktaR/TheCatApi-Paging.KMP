package com.hossian.thecatapikmp.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Cat(
    val breeds: List<Breed>,
    val id: String,
    val url: String,
    val width: Int,
    val height: Int
) {
    companion object {
        val example = Cat(
            breeds = listOf(
                Breed(
                    id = "1",
                    name = "York Chocolate",
                    temperament = "Playful, Social, Intelligent, Curious, Friendly",
                    origin = "United States",
                    lifeSpan = "13 - 15",
                    wikipediaURL = null,
                    description = "York Chocolate cats are known to be true lap cats with a sweet temperament. They love to be cuddled and petted. Their curious nature makes them follow you all the time and participate in almost everything you do, even if it's related to water: unlike many other cats, York Chocolates love it."
                )
            ),
            id = "1",
            url = "https://Google.com",
            width = 1080,
            height = 720
        )
    }
}

@Serializable
data class Breed(
    val id: String,
    val name: String,
    val temperament: String,
    val origin: String,
    @SerialName("life_span") val lifeSpan: String,
    @SerialName("wikipedia_url") val wikipediaURL: String?,
    val description: String
)
