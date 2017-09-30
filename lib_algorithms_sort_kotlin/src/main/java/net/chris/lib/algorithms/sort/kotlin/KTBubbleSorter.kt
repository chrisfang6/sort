package net.chris.lib.algorithms.sort.kotlin

/**
 * Bubble sort.
 *
 * @param <T>
</T> */
abstract class KTBubbleSorter : KTSorter() {

    override fun subSort(array: IntArray) {
        if (array == null) {
            return
        }
        for (i in 0 until array.size - 1) {
            for (j in array.size - 1 downTo i + 1) {
                val temp: Int
                if (compareTo(array[j], array[j - 1]) < 0) {
                    temp = array[j]
                    array[j] = array[j - 1]
                    array[j - 1] = temp
                    doUpdate(array)
                }
            }
        }
    }

}
