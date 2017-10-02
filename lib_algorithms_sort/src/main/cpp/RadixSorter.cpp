#include "RadixSorter.h"
#include <math.h>

void sort(int *a, int size, int exp) {
    int *output = new int[size];
    int *buckets = new int[10];
    for (int i = 0; i < 10; i++) {
        buckets[i] = 0;
    }
    for (int i = 0; i < size; i++) {
        buckets[(a[i] / exp) % 10]++;
    }
    for (int i = 1; i < 10; i++) {
        buckets[i] += buckets[i - 1];
    }
    for (int i = size - 1; i >= 0; i--) {
        output[buckets[(a[i] / exp) % 10] - 1] = a[i];
        buckets[(a[i] / exp) % 10]--;
    }
    for (int i = 0; i < size; i++) {
        a[i] = output[i];
    }
    delete[]buckets;
    delete[]output;
}


int getMax(int *a, int size) {
    int max;
    max = INT_MIN;
    for (int i = 0; i < size; i++) {
        if (a[i] > max) { max = a[i]; }
    }
    return max;
}

void radixSort(int *a, int size) {
    int max = getMax(a, size);
    for (int exp = 1; max / exp > 0; exp *= 10) {
        sort(a, size, exp);
    }
}