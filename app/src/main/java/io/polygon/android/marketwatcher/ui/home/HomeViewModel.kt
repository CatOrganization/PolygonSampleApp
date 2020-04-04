package io.polygon.android.marketwatcher.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.polygon.android.domain.usecase.EquityUseCase
import io.polygon.kotlin.model.EquityAggregate
import io.polygon.kotlin.model.TickerSnapshot
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class HomeViewModel : ViewModel(), KoinComponent {

    private val equityUseCase by inject<EquityUseCase>()

    private val _equityData = MutableLiveData<List<EquityData>>()
    val equityData: LiveData<List<EquityData>> = _equityData

    fun fetchEquityInfo(equities: List<EquityIdentifiers>) {
        viewModelScope.launch {
            val data = equities
                .map { equity ->
                    async {
                        val snapshot = async { equityUseCase.getTickerSnapshot(equity.symbol) }
                        val aggs = async { equityUseCase.getPastWeekAggs(equity.symbol) }

                        EquityData(
                            identifiers = equity,
                            values = snapshot.await().toValues(),
                            aggs = aggs.await().aggs
                        )
                    }
                }
                .map { it.await() }

            _equityData.postValue(data)
        }
    }

    private fun TickerSnapshot.toValues() =
        EquityValues(lastPrice, todaysChange, todaysChangePercentage)

}

data class EquityData(
    val identifiers: EquityIdentifiers,
    val values: EquityValues,
    val aggs: List<EquityAggregate>
)

data class EquityIdentifiers(
    val symbol: String,
    val name: String
)

data class EquityValues(
    val value: Double,
    val todaysChange: Double,
    val todaysChangePercentage: Double
)