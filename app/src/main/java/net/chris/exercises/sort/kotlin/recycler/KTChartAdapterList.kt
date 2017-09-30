package net.chris.exercises.sort.kotlin.recycler

import android.support.percent.PercentRelativeLayout
import android.view.LayoutInflater
import android.view.ViewGroup
import net.chris.exercises.sort.Constants
import net.chris.exercises.sort.R

class KTChartAdapterList : KTBaseRecyclerAdapter<KTChartHolder>() {

    var items: List<Int> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KTChartHolder {
        return KTChartHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_chart_kotlin, parent, false))
    }

    override fun onBindViewHolder(holder: KTChartHolder, position: Int) {
        val item = getItem(position)
        holder.value.text = item!!.toInt().toString()
        val params = holder.pic.layoutParams as PercentRelativeLayout.LayoutParams
        params.percentLayoutInfo.heightPercent = item.toFloat() / Constants.MAX_VALUE
        holder.pic.layoutParams = params
    }

    override fun size(): Int {
        return if (items == null) {
            0
        } else items!!.size
    }

    override fun getItem(position: Int): Int {
//        return if (position > -1 && position < itemCount) {
           return  items!![position]
//        } else null
    }

    override fun clear() {
        /*if (items != null) {
            items!!.clear()
        }*/
    }
}
