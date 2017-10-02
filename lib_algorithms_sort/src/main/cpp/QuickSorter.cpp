#include "QuickSorter.h"

inline void swap(int *array, int j, int k) {
    int temp;
    temp = array[j];
    array[j] = array[k];
    array[k] = temp;
}

void quickSort(int *array, int left, int right) {
    int last;
    if (left >= right) {
        return;
    }
    swap(array, left, (left + right) / 2);
    last = left;
    for (int i = left + 1; i <= right; i++) {
        if (array[i] < array[left]) {
            swap(array, ++last, i);
        }
    }
    swap(array, left, last);
    quickSort(array, left, last - 1);
    quickSort(array, last + 1, right);
}

void quickSort(int *v, int size) {
    quickSort(v, 0, size - 1);
}