package io.polygon.kotlin.model

data class EquityAggregates(
    val symbol: String,
    val aggs: List<EquityAggregate>
)

data class EquityAggregate(
    val price: Double,
    val timestamp: Long
)