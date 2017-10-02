#include "InsertionSorter.h"

void insertionSort(int *array, int size) {
    int temp;
    int pos;
    for (int i = 1; i < size; i++) {
        temp = array[i];
        pos = i - 1;
        while ((pos >= 0) && (temp < array[pos])) {
            array[pos + 1] = array[pos];
            pos--;
        }
        array[pos + 1] = temp;
    }
}