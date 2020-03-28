package io.polygon.android.marketwatcher.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import io.polygon.android.marketwatcher.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        homeViewModel.apply {
            equityName.observe(this@HomeFragment.viewLifecycleOwner, Observer {
                it ?: return@Observer
                equity_card.setEquityName(it)
            })

            equityValue.observe(this@HomeFragment.viewLifecycleOwner, Observer {
                it ?: return@Observer
                equity_card.setEquityValue(it)
            })

            equityHistory.observe(this@HomeFragment.viewLifecycleOwner, Observer {
                it ?: return@Observer
                equity_card.setEquityHistory(it.map { it.price })
            })
        }

        homeViewModel.fetchEquityInfo()

        return root
    }
}