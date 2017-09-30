package net.chris.lib.algorithms.sort.kotlin

import java.util.*

abstract class KTSorter : KTUpdateListener {

    protected var update: Boolean = false

    open fun sort(list: List<Int>): List<Int> {
        return if (list == null) {
            list
        } else toList(sort(toArray(list), false))
    }

    fun sort(collection: Collection<Int>): List<Int> {
        if (collection == null) {
            return listOf()
        }
        val list = ArrayList<Int>(collection.size)
        list.addAll(collection)
        return sort(list)
    }

    /**
     * @param array
     * @param copy  copy the array to a new one to sort
     * @return
     */
    open fun sort(array: IntArray, copy: Boolean): IntArray {
        if (array == null) {
            return array
        }
        val copiedArray: IntArray
        if (copy) {
            copiedArray = intArrayOf(array.size)
            System.arraycopy(array!!, 0, copiedArray, 0, array!!.size)
        } else {
            copiedArray = array
        }
        subSort(copiedArray)
        return copiedArray
    }

    protected abstract fun subSort(list: IntArray)

    /**
     * @param first
     * @param second
     * @return -1 (first < second); 0 (first = second); 1 (first > second)
     */
    protected abstract fun compareTo(first: Int, second: Int): Int

    protected fun toArray(list: List<Int>): IntArray {
        return list.toIntArray()
    }

    protected fun toList(sorted: IntArray): List<Int> {
        return sorted.toList()
    }

    protected fun doUpdate(array: IntArray) {
        if (update) {
            update(toList(array))
        }
    }

    fun setUpdate(update: Boolean): KTSorter {
        this.update = update
        return this
    }
}