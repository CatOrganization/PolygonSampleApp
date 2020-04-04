package io.polygon.kotlin.model

data class TickerDetails(
    val symbol: String,
    val name: String,

    val logoUrl: String,
    val tags: List<String>,
    val similars: List<String>
)