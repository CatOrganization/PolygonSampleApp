package io.polygon.kotlin.model

data class TickerSnapshot(
    val symbol: String,
    val lastPrice: Double,
    val todaysChange: Double,
    val todaysChangePercentage: Double
)