package net.chris.lib.algorithms.sort.kotlin

/**
 * Counting sort.
 *
 */
abstract class KTCountingSorter : KTSorter() {

    /*override fun sort(list: List<Int>?): List<Int>? {
        return if (list == null) {
            list
        } else toList(sort(toArray(list), false))
    }*/

    override fun subSort(A: IntArray) {
        if (A == null) {
            return
        }
        val B = IntArray(A.size) //to store result after sorting
        val k = max(A)
        val C = IntArray(k + 1) // to store temp
        for (i in A.indices) {
            C[A[i]] = C[A[i]] + 1
        }
        // store the count of the item whose value less than A[i]
        for (i in 1..C.size - 1) {
            C[i] = C[i] + C[i - 1]
        }
        for (i in A.indices.reversed()) {
            B[C[A[i]] - 1] = A[i]
            C[A[i]] = C[A[i]] - 1
        }
        for (i in A.indices) {
            A[i] = B[i]
        }
    }

    private fun max(a: IntArray): Int {
        var max = 0
        for (i in a.indices) {
            max = Math.max(max, a[i])
        }
        return max
    }

}
