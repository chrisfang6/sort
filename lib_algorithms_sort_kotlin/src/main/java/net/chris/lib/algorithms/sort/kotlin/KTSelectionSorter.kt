package net.chris.lib.algorithms.sort.kotlin

/**
 * Selection sort.
 *
 * @param <T>
</T> */
abstract class KTSelectionSorter : KTSorter() {

    override fun subSort(array: IntArray) {
        if (array == null) {
            return
        }
        for (i in 0 until array.size - 1) {
            var min = i
            val temp: Int
            //find min
            for (j in i + 1 until array.size) {
                if (compareTo(array[j], array[min]) < 0) {
                    min = j
                }
            }
            //swap the min with the ith element
            temp = array[min]
            array[min] = array[i]
            array[i] = temp
            doUpdate(array)
        }
    }

}
