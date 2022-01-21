package com.example.water.presentation.screens.statistics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.water.R
import com.example.water.domain.models.Report
import com.example.water.domain.models.UserData

class StatisticAdapter : RecyclerView.Adapter<MainHolder>() {

    private var mListReport = emptyList<Report>()
    var userData = UserData()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.report_item, parent, false)
        return MainHolder(view)
    }

    override fun getItemCount(): Int = mListReport.size

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.dateReport.text = mListReport[position].date
        holder.countWaterReport.text = "Выпито ${mListReport[position].water} мл из ${userData.normWater} мл"
    }

    fun setList(list: List<Report>) {
        mListReport = list
        notifyDataSetChanged()
    }

    fun setUser(user: UserData) {
        userData = user
    }
}