#include "BubbleSorter.h"

inline void swap(int *array, int j, int k) {
    int temp;
    temp = array[j];
    array[j] = array[k];
    array[k] = temp;
}


void bubbleSort(int *array, int size) {
    for (int i = 1; i < size; i++) {
        for (int j = size - 1; j >= i; j--) {
            if (array[j] < array[j - 1]) {
                swap(array, j - 1, j);
            }
        }
    }
}