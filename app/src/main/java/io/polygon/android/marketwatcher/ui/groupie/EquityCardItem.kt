package io.polygon.android.marketwatcher.ui.groupie

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import io.polygon.android.marketwatcher.R
import io.polygon.android.marketwatcher.ui.EquityCardView
import io.polygon.android.marketwatcher.ui.home.EquityData

class EquityCardItem(val equityData: EquityData) : Item() {

    override fun getLayout(): Int = R.layout.item_equity_card

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        (viewHolder.root as EquityCardView).apply {
            setEquityIdentifier(equityData.identifiers)
            setEquityValue(equityData.values)
            setEquityHistory(equityData.aggs.map { it.price })
        }
    }
}