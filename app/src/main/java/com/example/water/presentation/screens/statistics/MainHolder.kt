package com.example.water.presentation.screens.statistics

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.report_item.view.*

class MainHolder(view: View) : RecyclerView.ViewHolder(view) {
    val dateReport: TextView = view.item_date
    val countWaterReport: TextView = view.item_count_water
}
