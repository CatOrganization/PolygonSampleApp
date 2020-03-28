package io.polygon.android.marketwatcher.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.polygon.android.domain.usecase.EquityUseCase
import io.polygon.kotlin.model.EquityAggregate
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class HomeViewModel : ViewModel(), KoinComponent {

    private val equityUseCase by inject<EquityUseCase>()

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _equityName = MutableLiveData<String>()
    val equityName: LiveData<String> = _equityName

    private val _equityValue = MutableLiveData<String>()
    val equityValue: LiveData<String> = _equityValue

    private val _equityHistory = MutableLiveData<List<EquityAggregate>>()
    val equityHistory: LiveData<List<EquityAggregate>> = _equityHistory

    fun fetchEquityInfo() {
        _equityName.postValue("Redfin")

        viewModelScope.launch {
            val lastTrade = equityUseCase.getLastTrade("RDFN")
            _equityValue.postValue("\$${lastTrade.price}")
        }

        viewModelScope.launch {
            val aggs = equityUseCase.getPastWeekAggs("RDFN")
            _equityHistory.postValue(aggs.aggs)
        }
    }

}