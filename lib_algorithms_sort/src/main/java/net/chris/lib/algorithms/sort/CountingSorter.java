package net.chris.lib.algorithms.sort;

import java.util.List;

/**
 * Counting sort.
 *
 */
public abstract class CountingSorter extends Sorter<Integer> {

    @Override
    public List<Integer> sort(List<Integer> list) {
        if (list == null) {
            return list;
        }
        return toList(sort(toArray(list), false));
    }

    @Override
    protected void subSort(Integer[] A) {
        if (A == null) {
            return;
        }
        int[] B = new int[A.length]; //to store result after sorting
        int k = max(A);
        int[] C = new int[k + 1]; // to store temp
        for (int i = 0; i < A.length; i++) {
            C[A[i]] = C[A[i]] + 1;
        }
        // store the count of the item whose value less than A[i]
        for (int i = 1; i < C.length; i++) {
            C[i] = C[i] + C[i - 1];
        }
        for (int i = A.length - 1; i >= 0; i--) {
            B[C[A[i]] - 1] = A[i];
            C[A[i]] = C[A[i]] - 1;
        }
        for (int i = 0; i < A.length; i++) {
            A[i] = B[i];
        }
    }

    private int max(Integer[] a) {
        int max  = 0;
        for (int i = 0; i < a.length; i++) {
            max = Math.max(max, a[i]);
        }
        return max;
    }

    @Override
    protected Integer[] toArray(List<Integer> list) {
        return list.toArray(new Integer[0]);
    }

}
