package io.polygon.android.domain.usecase

import io.polygon.android.repository.EquityRepository
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
}