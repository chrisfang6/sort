package net.chris.lib.algorithms.sort;

/**
 * Bubble sort.
 *
 * @param <T>
 */
public abstract class BubbleSorter<T> extends Sorter<T> {

    @Override
    protected void subSort(T[] array) {
        if (array == null) {
            return;
        }
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = array.length - 1; j >= i + 1; j--) {
                T temp;
                if (compareTo(array[j], array[j - 1]) < 0) {
                    temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                    doUpdate(array);
                }
            }
        }
    }

}
