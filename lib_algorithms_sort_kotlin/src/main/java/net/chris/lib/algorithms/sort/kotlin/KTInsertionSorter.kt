package net.chris.lib.algorithms.sort.kotlin

/**
 * Insertion sort.
 *
 * @param <T>
</T> */
abstract class KTInsertionSorter : KTSorter() {

    override fun subSort(array: IntArray) {
        if (array == null) {
            return
        }
        for (j in 1 until array.size) {
            val key = array[j]
            var find = false
            for (i in j - 1 downTo -1 + 1) {
                if (compareTo(array[i], key) == 1) {
                    array[i + 1] = array[i]
                    doUpdate(array)
                } else {
                    array[i + 1] = key
                    find = true
                    doUpdate(array)
                    break
                }
            }
            if (!find) {
                array[0] = key
            }
        }
    }

}
