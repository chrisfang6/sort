package net.chris.lib.algorithms.sort.kotlin

import java.util.*

/**
 * Bucket sort.
 *
 * @param <T>
</T> */
abstract class KTBucketSorter : KTSorter() {

    private var buckets: MutableList<Bucket>? = null

    override fun subSort(array: IntArray) {
        if (array == null) {
            return
        }
        createBuckets()
        for (i in array.indices) {
            for (j in buckets!!.indices) {
                if (buckets!![j].inBucket(array[i])) {
                    buckets!![j].add(array[i])
                    break
                }
            }
        }
        val result = array.copyOf()
        var index = 0
        for (j in buckets!!.indices) {
            val bucketArray = buckets!![j].sort()
            for (i in bucketArray.indices) {
                result[index] = bucketArray[i]
                index++
            }
        }
        for (i in result.indices) {
            array[i] = result[i]
        }
    }

    private fun createBuckets() {
        val size = demarcations.size + 1
        buckets = ArrayList(size)
        var i = 0
        while (i < demarcations.size) {
            val left = demarcations[i]
            val right = demarcations[i + 1]
            buckets!!.add(Bucket(left, right))
            i += 2
        }
    }

    protected abstract val sorter: KTSorter

    protected abstract val demarcations: IntArray

    /**
     *
     */
    inner class Bucket constructor(private val left: Int, private val right: Int) {

        private val list = mutableListOf<Int>()

        fun inBucket(element: Int): Boolean {
            return compareTo(element, left) >= 0 && compareTo(element, right) <= 0
        }

        fun add(element: Int) {
            list.add(element)
        }

        fun sort(): IntArray {
            return sorter.sort(list.toList()).toIntArray()
        }
    }

}
