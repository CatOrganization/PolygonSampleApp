package io.polygon.android.marketwatcher.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import io.polygon.android.marketwatcher.R
import io.polygon.android.marketwatcher.ui.groupie.EquityCardItem
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    private val adapter by lazy { GroupAdapter<GroupieViewHolder>() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        equity_card_recycler.adapter = adapter
        equity_card_recycler.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        val equities = listOf(
            EquityIdentifiers("RDFN", "Redfin Corporation"),
            EquityIdentifiers("SPY", "SPDR S&P 500 ETF"),
            EquityIdentifiers("QQQ", "PowerShares QQQ Trust Ser 1"),
            EquityIdentifiers("DIA", "SPDR Dow Jones Industrial Average")
        )

        homeViewModel.apply {
            equityData.observe(viewLifecycleOwner, Observer {
                it ?: return@Observer

                adapter.clear()
                adapter.addAll(it.map { EquityCardItem(it) })
            })
        }


        homeViewModel.fetchEquityInfo(equities)
    }
}