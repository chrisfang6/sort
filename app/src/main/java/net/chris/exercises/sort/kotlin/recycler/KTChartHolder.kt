package net.chris.exercises.sort.kotlin.recycler

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

import net.chris.exercises.sort.R

class KTChartHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var pic: View
    var value: TextView

    init {
        pic = itemView.findViewById(R.id.item_chart_pic)
        value = itemView.findViewById(R.id.item_chart_value) as TextView
    }
}
