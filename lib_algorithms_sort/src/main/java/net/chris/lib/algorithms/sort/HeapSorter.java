package net.chris.lib.algorithms.sort;

/**
 * Heap sort.
 *
 * @param <T>
 */
public abstract class HeapSorter<T> extends Sorter<T> {

    @Override
    protected void subSort(T[] array) {
        if (array == null) {
            return;
        }
        int heapsize = array.length;
        buildheap(array);
        for (int i = 0; i < array.length - 1; i++) {
            // swap the first and the last
            T temp;
            temp = array[0];
            array[0] = array[heapsize - 1];
            array[heapsize - 1] = temp;
            // heapify the array
            heapsize = heapsize - 1;
            heapify(array, 0, heapsize);
        }
    }

    public void buildheap(T array[]) {
        int length = array.length;
        int heapsize = length;
        int nonleaf = length / 2 - 1;
        for (int i = nonleaf; i >= 0; i--) {
            heapify(array, i, heapsize);
        }
    }

    public void heapify(T array[], int i, int heapsize) {
        int smallest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < heapsize) {
            if (compareTo(array[i], array[left]) > 0) {
                smallest = left;
            } else smallest = i;
        }
        if (right < heapsize) {
            if (compareTo(array[smallest], array[right]) > 0) {
                smallest = right;
            }
        }
        if (smallest != i) {
            T temp;
            temp = array[i];
            array[i] = array[smallest];
            array[smallest] = temp;
            doUpdate(array);
            heapify(array, smallest, heapsize);
        }
    }

}
