package com.venkatapps.mppiechart

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate


class MainActivity : AppCompatActivity() {
    private lateinit var pieChart: PieChart
    private lateinit var chartLayout: RelativeLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pieChart = findViewById(R.id.pieChart)

        //initialize a List of colors one for each slice
        val pieColors: ArrayList<Int> = ArrayList()
        pieColors.add(Color.parseColor("#0f2c4b"))
        pieColors.add(Color.parseColor("#1857a0"))
        pieColors.add(Color.parseColor("#238837"))
        pieColors.add(Color.parseColor("#3f9cff"))

        //initialize a List of PieEntry with its value/label pair
        val pieEntries: ArrayList<PieEntry> = ArrayList()
        pieEntries.add(PieEntry(40f, "Industrials"))
        pieEntries.add(PieEntry(18f, "Financials"))
        pieEntries.add(PieEntry(20f, "Health Care"))
        pieEntries.add(PieEntry(22f, "Real Estate"))

        //prepare the PieDataSet with the above pieEntries and pieColors
        val pieDataSet = PieDataSet(pieEntries, "")
       // pieDataSet.valueTextSize = 14f
        pieDataSet.colors = pieColors

        //draw value/label outside the pie chart
        pieDataSet.xValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE
        pieDataSet.yValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE
        pieDataSet.valueLinePart1OffsetPercentage = 100f
        pieDataSet.valueLinePart1Length = 1.5f
        pieDataSet.valueLinePart2Length = 0f
        pieDataSet.valueTextColor = Color.BLACK
        pieDataSet.valueTypeface = Typeface.DEFAULT_BOLD
        pieDataSet.valueLineColor = ColorTemplate.COLOR_NONE

        //prepare the PieData
        val pieData = PieData(pieDataSet)
        pieData.setValueTextColor(Color.WHITE)
        pieData.setDrawValues(true)

        //pieData.setValueFormatter(PercentFormatter(pieChart))

        //set pieChart data and any other pieChart property needed
        pieChart.data = pieData
        pieChart.setExtraOffsets(35f, 35f, 35f, 35f)
        pieChart.holeRadius = 0f
        pieChart.transparentCircleRadius = 0f
        pieChart.setEntryLabelColor(Color.BLACK)
        pieChart.setEntryLabelTextSize(14f)
        pieChart.setEntryLabelTypeface(Typeface.DEFAULT_BOLD)
        pieChart.setUsePercentValues(false)
        pieChart.legend.isEnabled = false
        pieChart.description.isEnabled = false
        pieChart.isRotationEnabled = true
        pieChart.dragDecelerationFrictionCoef = 0.9f
        pieChart.rotationAngle = 220f
        pieChart.isHighlightPerTapEnabled = true
        pieChart.animateY(1400, Easing.EaseInOutQuad)
        //pieChart.setHoleColor(Color.WHITE)


        //set the custom renderer (CustomPieChartRenderer) used to draw each label on top of the value and call invalidate to redraw the chart
        pieChart.renderer =
            CustomPieChartRenderer(pieChart, pieChart.animator, pieChart.viewPortHandler)
        pieChart.invalidate()
    }

}

/*private fun createPieChart(): PieChart {
    val chart = PieChart(this)

    // Create and populate PieDataSet
    val pieDataSet = PieDataSet(ArrayList(), "Data")

    //draw value/label outside the pie chart
    pieDataSet.xValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE
    pieDataSet.yValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE
    pieDataSet.valueLinePart1OffsetPercentage = 100f
    pieDataSet.valueLinePart1Length = 0.8f
    pieDataSet.valueLinePart2Length = 0f
    pieDataSet.valueTextColor = Color.BLACK
    pieDataSet.valueTypeface = Typeface.DEFAULT_BOLD
    pieDataSet.valueLineColor = ColorTemplate.COLOR_NONE

    pieDataSet.addEntry(PieEntry(100f))
    pieDataSet.addEntry(PieEntry(50f))
    pieDataSet.addEntry(PieEntry(25f))


    // Set random colors for the 3 entries of the PieDataSet
    pieDataSet.setColors(Color.RED, Color.BLUE, Color.GREEN)

    // Set Data Set to PieData; Set PieData to Chart
    val pieData = PieData()
    pieData.dataSet = pieDataSet
    chart.data = pieData
    pieData.setDrawValues(true)
    pieData.setValueFormatter(PercentFormatter(chart))

    // Disable legend and description
    chart.legend.isEnabled = false
    chart.description.isEnabled = false
    chart.holeRadius = 0f
    chart.transparentCircleRadius = 0f

    return chart
}*/