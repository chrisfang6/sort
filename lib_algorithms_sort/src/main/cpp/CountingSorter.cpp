#include "CountingSorter.h"
#include <math.h>

void countingSort(int *array, int size) {
    int max = INT_MIN;
    for (int i = 0; i < size; i++) {
        if (array[i] > max) {
            max = array[i];
        }
    }
    int *tmp = new int[max + 1];
    for (int i = 0; i <= max; i++) {
        tmp[i] = 0;
    }
    for (int i = 0; i < size; i++) {
        tmp[array[i]]++;
    }
    do while (tmp[max]--) array[--size] = max;
    while (max--);
    delete[]tmp;
}