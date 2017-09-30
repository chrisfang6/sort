package net.chris.exercises.sort.kotlin.recycler

import android.support.v7.widget.RecyclerView

/**
 * Base [RecyclerView.Adapter]
 */

abstract class KTBaseRecyclerAdapter<T : RecyclerView.ViewHolder> : RecyclerView.Adapter<T>() {

    override fun getItemCount(): Int {
        return size()
    }

    abstract fun size(): Int

    abstract fun getItem(position: Int): Int

    abstract fun clear()

}
