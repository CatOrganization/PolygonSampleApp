package io.polygon.android.repository

import io.polygon.kotlin.model.EquityAggregate
import io.polygon.kotlin.model.EquityAggregates
import io.polygon.kotlin.model.EquityLastTrade
import io.polygon.kotlin.persistence.sharedprefs.PolygonKeySPAO
import io.polygon.kotlin.sdk.rest.*
import io.polygon.kotlin.sdk.rest.stocks.LastTradeDTO
import io.polygon.kotlin.sdk.rest.stocks.LastTradeResultDTO
import io.polygon.kotlin.sdk.rest.stocks.getLastTrade
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.lang.IllegalStateException

class EquityRepository : KoinComponent {

    val polygonKeySPAO by inject<PolygonKeySPAO>()

    private val polygonRestClient by lazy {
        PolygonRestClient(polygonKeySPAO.polygonApiKey ?: throw IllegalStateException("No Key!"))
    }

    suspend fun getLastTrade(symbol: String) =
        polygonRestClient.stocksClient
            .getLastTrade(symbol)
            .toDomainModel()

    private fun LastTradeResultDTO.toDomainModel() =
        EquityLastTrade(this.symbol!!, this.lastTrade!!.price!!)

    suspend fun getHourAggregates(symbol: String, fromDate: String, toDate: String) =
        polygonRestClient.getAggregates(
            AggregatesParameters(
                ticker = symbol,
                fromDate = fromDate,
                toDate = toDate,
                timespan = "hour"
            )
        ).toDomainModel()

    private fun AggregatesDTO.toDomainModel() =
        EquityAggregates(
            symbol = this.ticker!!,
            aggs = this.results.map { it.toDomainModel() }
        )

    private fun AggregateDTO.toDomainModel() =
        EquityAggregate(this.close!!, this.timestampMillis!!)
}