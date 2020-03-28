package io.polygon.android.marketwatcher.ui

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import io.polygon.android.marketwatcher.R
import kotlinx.android.synthetic.main.equity_card_view.view.*

class EquityCardView : ConstraintLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        val padding = resources.getDimensionPixelSize(R.dimen.gap_large)
        setPadding(padding, padding, padding, padding)


        View.inflate(context, R.layout.equity_card_view, this)
    }

    // TODO: Unify with [setEquityValue] with some data model
    fun setEquityName(equityName: String) {
        equity_name.text = equityName
    }

    fun setEquityValue(equityValue: String) {
        equity_value.text = equityValue
    }

    fun setEquityHistory(values: List<Double>) {
        val entries = values.mapIndexed { index: Int, value: Double -> Entry(index.toFloat(), value.toFloat()) }

        val dataSet = LineDataSet(entries, "test")
        dataSet.setDrawCircles(false)
        dataSet.setDrawCircleHole(false)
        dataSet.setDrawValues(false)

        val colorGradient = if (entries.isNetPositive()) {
            intArrayOf(
                ContextCompat.getColor(context, R.color.gain_green),
                ContextCompat.getColor(context, R.color.gain_green_transparent)
            )
        } else {
            intArrayOf(
                ContextCompat.getColor(context, R.color.loss_red),
                ContextCompat.getColor(context, R.color.loss_red_transparent)
            )
        }

        val drawable = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM,
            colorGradient
        )

        dataSet.color = colorGradient[0]
        dataSet.fillDrawable = drawable
        dataSet.setDrawFilled(true)

        equity_chart.isClickable = false
        equity_chart.isFocusable = false
        equity_chart.axisLeft.isEnabled = false
        equity_chart.axisRight.isEnabled = false
        equity_chart.description.isEnabled = false
        equity_chart.xAxis.isEnabled = false
        equity_chart.legend.isEnabled = false

        equity_chart.data = LineData(dataSet)
        equity_chart.invalidate()
    }

    private fun List<Entry>.isNetPositive() = last().y >= first().y
}