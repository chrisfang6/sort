package net.chris.lib.algorithms.sort.kotlin

/**
 * Heap sort.
 *
 * @param <T>
</T> */
abstract class KTHeapSorter : KTSorter() {

    override fun subSort(array: IntArray) {
        if (array == null) {
            return
        }
        var heapsize = array.size
        buildheap(array)
        for (i in 0 until array.size - 1) {
            // swap the first and the last
            val temp: Int
            temp = array[0]
            array[0] = array[heapsize - 1]
            array[heapsize - 1] = temp
            // heapify the array
            heapsize -= 1
            heapify(array, 0, heapsize)
        }
    }

    fun buildheap(array: IntArray) {
        val length = array.size
        val nonleaf = length / 2 - 1
        for (i in nonleaf downTo 0) {
            heapify(array, i, length)
        }
    }

    fun heapify(array: IntArray, i: Int, heapsize: Int) {
        var smallest = i
        val left = 2 * i + 1
        val right = 2 * i + 2
        if (left < heapsize) {
            if (compareTo(array[i], array[left]) > 0) {
                smallest = left
            } else
                smallest = i
        }
        if (right < heapsize) {
            if (compareTo(array[smallest], array[right]) > 0) {
                smallest = right
            }
        }
        if (smallest != i) {
            val temp: Int
            temp = array[i]
            array[i] = array[smallest]
            array[smallest] = temp
            doUpdate(array)
            heapify(array, smallest, heapsize)
        }
    }

}
