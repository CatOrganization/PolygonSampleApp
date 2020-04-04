package io.polygon.android.domain.usecase

import android.util.Log
import io.polygon.android.repository.EquityRepository
import io.polygon.kotlin.model.TickerSnapshot
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.threeten.bp.Duration
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

class EquityUseCase : KoinComponent {

    private val repository by inject<EquityRepository>()

    private val dateFormatter by lazy {
        DateTimeFormatter.ofPattern("yyyy-MM-dd")
    }

    suspend fun getLastTrade(symbol: String) =
        repository.getLastTrade(symbol)

    suspend fun getPastWeekAggs(symbol: String) =
        repository.getHourAggregates(
            symbol,
            dateFormatter.format(LocalDateTime.now().minus(Duration.ofDays(7))),
            dateFormatter.format(LocalDateTime.now())
        )

    suspend fun getTickerDetails(symbol: String) =
        repository.getTickerDetails(symbol)

    suspend fun getTickerSnapshot(symbol: String) = try {
        repository.getTickerSnapshot(symbol)
    } catch (error: Throwable) {
        Log.e("EquityRepository", "Error getting snapshot", error)
        TickerSnapshot(symbol = symbol, lastPrice = 0.0, todaysChangePercentage = 0.0, todaysChange = 0.0)
    }
}