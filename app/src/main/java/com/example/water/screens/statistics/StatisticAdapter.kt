package com.example.water.screens.statistics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.water.R
import com.example.water.models.Report
import com.example.water.utilits.USER_DATA
import kotlinx.android.synthetic.main.report_item.view.*

class StatisticAdapter : RecyclerView.Adapter<StatisticAdapter.MainHolder>() {

    private var mListReport = emptyList<Report>()

    class MainHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dateReport: TextView = view.item_date
        val countWaterReport: TextView = view.item_count_water
    }

//    override fun onViewAttachedToWindow(holder: MainHolder) {
//        holder.itemView.setOnClickListener {
//            StatisticsFragment.click(mListNotes[holder.adapterPosition])
//        }
//    }
//
//    override fun onViewDetachedFromWindow(holder: MainHolder) {
//        holder.itemView.setOnClickListener(null)
//        super.onViewDetachedFromWindow(holder)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.report_item, parent, false)
        return MainHolder(view)
    }

    override fun getItemCount(): Int = mListReport.size

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.dateReport.text = mListReport[position].date
        holder.countWaterReport.text = "Выпито ${mListReport[position].water} мл из ${USER_DATA.normWater} мл"
    }

    fun setList(list: List<Report>) {
        mListReport = list
        notifyDataSetChanged()
    }
}