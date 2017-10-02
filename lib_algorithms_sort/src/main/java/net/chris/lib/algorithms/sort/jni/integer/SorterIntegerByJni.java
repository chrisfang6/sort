package net.chris.lib.algorithms.sort.jni.integer;

public class SorterIntegerByJni {

    static {
        System.loadLibrary("native-lib-sorter");
    }

    static native int[] insertionSort(int[] src);

    static native int[] bubbleSort(int[] src);

    static native int[] selectionSort(int[] src);

    static native int[] mergeSort(int[] src);

    static native int[] quickSort(int[] src);

    static native int[] heapSort(int[] src);

    static native int[] bucketSort(int[] src);

    static native int[] countingSort(int[] src);

    static native int[] radixSort(int[] src);
}
