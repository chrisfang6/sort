package net.chris.lib.algorithms.sort.kotlin

/**
 * Quick sort.
 *
 * @param <T>
</T> */
abstract class KTQuickSorter : KTSorter() {

    override fun subSort(list: IntArray) {
        subQuickSort(list, 0, list.size - 1)
    }

    private fun subQuickSort(array: IntArray, start: Int, end: Int) {
        if (array == null || end - start + 1 < 2) {
            return
        }
        val part = partition(array, start, end)
        if (part == start) {
            subQuickSort(array, part + 1, end)
        } else if (part == end) {
            subQuickSort(array, start, part - 1)
        } else {
            subQuickSort(array, start, part - 1)
            subQuickSort(array, part + 1, end)
        }
    }

    private fun partition(array: IntArray, start: Int, end: Int): Int {
        val value = array[end]
        var index = start - 1
        for (i in start until end) {
            if (compareTo(array[i], value) < 0) {
                index++
                if (index != i) {
                    exchangeElements(array, index, i)
                }
            }
        }
        if (index + 1 != end) {
            exchangeElements(array, index + 1, end)
        }
        return index + 1
    }

    /**
     * Exchange the 2 items in the list
     *
     * @param array
     * @param position1
     * @param position2
     */
    private fun exchangeElements(array: IntArray, position1: Int, position2: Int) {
        val temp = array[position1]
        array[position1] = array[position2]
        array[position2] = temp
        doUpdate(array)
    }

}
