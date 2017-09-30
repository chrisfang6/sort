package net.chris.lib.algorithms.sort.kotlin

/**
 * Merge sort.
 *
 * @param <T>
</T> */
abstract class KTMergeSorter : KTSorter() {

    override fun subSort(array: IntArray) {
        if (array == null) {
            return
        }
        mergeSort(array, 0, array.size - 1)
    }

    private fun mergeSort(A: IntArray, p: Int, r: Int) {
        if (p < r) {
            val q = Math.floor((p + r).toDouble() / 2).toInt()
            mergeSort(A, p, q)
            mergeSort(A, q + 1, r)
            merge(A, p, q, r)
        }
    }

    private fun merge(A: IntArray, p: Int, q: Int, r: Int) {
        // p<=q<r
        if (p > q || q >= r) {
            return
        }
        val n1 = q - p + 1 // [p, q]
        val L = A.copyOf(n1 + 1)
        for (i in 0 until n1) {
            L[i] = A[p + i]
        }
        L[n1] = infinity
        val n2 = r - q // (q, r]
        val R = A.copyOf(n2 + 1)
        for (j in 0..n2 - 1) {
            R[j] = A[q + j + 1]
        }
        R[n2] = infinity
        var i = 0
        var j = 0
        var k = p
        while (k < r + 1) { // [p, r]
            if (compareTo(L[i], R[j]) < 1) { // L[i] <= R[j]
                A[k] = L[i]
                i++
                doUpdate(A)
            } else {
                A[k] = R[j]
                j++
                doUpdate(A)
            }
            k++
        }
    }

    protected abstract val infinity: Int

}
