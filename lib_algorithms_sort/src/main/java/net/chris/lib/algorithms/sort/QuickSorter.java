package net.chris.lib.algorithms.sort;

/**
 * Quick sort.
 *
 * @param <T>
 */
public abstract class QuickSorter<T> extends Sorter<T> {

    @Override
    protected void subSort(T[] list) {
        subQuickSort(list, 0, list.length - 1);
    }

    private void subQuickSort(T[] array, int start, int end) {
        if (array == null || (end - start + 1) < 2) {
            return;
        }
        int part = partition(array, start, end);
        if (part == start) {
            subQuickSort(array, part + 1, end);
        } else if (part == end) {
            subQuickSort(array, start, part - 1);
        } else {
            subQuickSort(array, start, part - 1);
            subQuickSort(array, part + 1, end);
        }
    }

    private int partition(T[] array, int start, int end) {
        T value = array[end];
        int index = start - 1;
        for (int i = start; i < end; i++) {
            if (compareTo(array[i], value) < 0) {
                index++;
                if (index != i) {
                    exchangeElements(array, index, i);
                }
            }
        }
        if ((index + 1) != end) {
            exchangeElements(array, index + 1, end);
        }
        return index + 1;
    }

    /**
     * Exchange the 2 items in the list
     *
     * @param array
     * @param position1
     * @param position2
     */
    private void exchangeElements(T[] array, int position1, int position2) {
        T temp = array[position1];
        array[position1] = array[position2];
        array[position2] = temp;
        doUpdate(array);
    }

}
