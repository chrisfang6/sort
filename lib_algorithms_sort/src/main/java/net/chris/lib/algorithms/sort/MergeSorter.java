package net.chris.lib.algorithms.sort;

/**
 * Merge sort.
 *
 * @param <T>
 */
public abstract class MergeSorter<T> extends Sorter<T> {

    @Override
    protected void subSort(T[] array) {
        if (array == null) {
            return;
        }
        mergeSort(array, 0, array.length - 1);
    }

    private void mergeSort(final T[] A, final int p, final int r) {
        if (p < r) {
            int q = (int) Math.floor(((double) (p + r)) / 2);
            mergeSort(A, p, q);
            mergeSort(A, q + 1, r);
            merge(A, p, q, r);
        }
    }

    private void merge(final T[] A, final int p, final int q, final int r) {
        // p<=q<r
        if (p > q || q >= r) {
            return;
        }
        int n1 = q - p + 1; // [p, q]
        T[] L = newArray(n1 + 1);
        for (int i = 0; i < n1; i++) {
            L[i] = A[p + i];
        }
        L[n1] = getInfinity();
        int n2 = r - q; // (q, r]
        T[] R = newArray(n2 + 1);
        for (int j = 0; j < n2; j++) {
            R[j] = A[q + j + 1];
        }
        R[n2] = getInfinity();
        for (int i = 0, j = 0, k = p; k < r + 1; k++) { // [p, r]
            if (compareTo(L[i], R[j]) < 1) { // L[i] <= R[j]
                A[k] = L[i];
                i++;
                doUpdate(A);
            } else {
                A[k] = R[j];
                j++;
                doUpdate(A);
            }
        }
    }

    protected abstract T getInfinity();

}
