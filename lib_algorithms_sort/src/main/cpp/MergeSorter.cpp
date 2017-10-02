#include <math.h>
#include "MergeSorter.h"

void merge(int *array, int p, int q, int r) {
    // p<=q<r
    if (p > q || q >= r) {
        return;
    }
    int n1 = q - p + 1; // [p, q]
    int lenL = n1 + 1;
    int *L = new int[lenL];
    for (int i = 0; i < n1; i++) {
        L[i] = array[p + i];
    }
    L[n1] = INT_MAX;
    int n2 = r - q; // (q, r]
    int lenR = n2 + 1;
    int *R = new int[lenR];
    for (int j = 0; j < n2; j++) {
        R[j] = array[q + j + 1];
    }
    R[n2] = INT_MAX;
    for (int i = 0, j = 0, k = p; k < r + 1; k++) { // [p, r]
        if (L[i] <= R[j]) { // L[i] <= R[j]
            array[k] = L[i];
            i++;
        } else {
            array[k] = R[j];
            j++;
        }
    }
    delete[]L;
    delete[]R;
}

void mergeSort(int *array, int p, int r) {
    if (p < r) {
        int q = (p + r) / 2;
        mergeSort(array, p, q);
        mergeSort(array, q + 1, r);
        merge(array, p, q, r);
    }
}

void mergeStart(int *array, int size) {
    mergeSort(array, 0, size - 1);
}