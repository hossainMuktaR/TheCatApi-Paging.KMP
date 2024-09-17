package com.hossian.thecatapikmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform