package io.polygon.android.repository

import io.polygon.kotlin.model.*
import io.polygon.kotlin.persistence.sharedprefs.PolygonKeySPAO
import io.polygon.kotlin.sdk.rest.*
import io.polygon.kotlin.sdk.rest.reference.TickerDetailsDTO
import io.polygon.kotlin.sdk.rest.reference.getTickerDetails
import io.polygon.kotlin.sdk.rest.stocks.*
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

    suspend fun getTickerDetails(symbol: String) =
        polygonRestClient.referenceClient
            .getTickerDetails(symbol)
            .toDomainModel()

    private fun TickerDetailsDTO.toDomainModel() =
        TickerDetails(
            symbol = symbol!!,
            name = name!!,
            logoUrl = logoUrl!!,
            tags = tags,
            similars = similar
        )

    suspend fun getTickerSnapshot(symbol: String) =
        polygonRestClient.stocksClient
            .getSnapshot(symbol)
            .toDomainModel()

    private fun SnapshotSingleTickerDTO.toDomainModel() =
        TickerSnapshot(
            symbol = ticker.ticker!!,
            lastPrice = ticker.lastTrade.price!!,
            todaysChange = ticker.todaysChange!!,
            todaysChangePercentage = ticker.todaysChangePerc!!
        )
}