#include "HeapSorter.h"

inline void swap(int *array, int j, int k) {
    int temp;
    temp = array[j];
    array[j] = array[k];
    array[k] = temp;
}

void heapRebuild(int *array, int root, int size) {
    int child = 2 * root + 1;
    if (child <= size - 1) {
        int rightChild = child + 1;
        if (rightChild <= size - 1)
            if (array[child] < array[rightChild])
                child = rightChild;
        if (array[root] < array[child]) {
            int temp = array[child];
            array[child] = array[root];
            array[root] = temp;
            heapRebuild(array, child, size);
        }
    }
}

void heapSort(int *array, int size) {
    for (int i = size - 1; i >= 0; i--) {
        heapRebuild(array, i, size);
    }
    int last = size - 1;
    for (int i = 1; i <= size; i++, last--) {
        swap(array, 0, last);
        heapRebuild(array, 0, last);

    }
}
