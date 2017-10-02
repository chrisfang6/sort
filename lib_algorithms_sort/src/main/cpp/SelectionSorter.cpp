#include "SelectionSorter.h"

void selectSort(int *array, int size) {
    int temp;
    int pos;
    for (int i = 0; i < size - 1; i++) {
        temp = array[i];
        pos = i;
        for (int j = i + 1; j < size; j++) {
            if (array[j] < temp) {
                temp = array[j];
                pos = j;
            }
        }
        array[pos] = array[i];
        array[i] = temp;
    }
}