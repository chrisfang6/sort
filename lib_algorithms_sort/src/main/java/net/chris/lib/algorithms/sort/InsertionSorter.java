package net.chris.lib.algorithms.sort;

/**
 * Insertion sort.
 *
 * @param <T>
 */
public abstract class InsertionSorter<T> extends Sorter<T> {

    @Override
    protected void subSort(T[] array) {
        if (array == null) {
            return;
        }
        for (int j = 1; j < array.length; j++) {
            T key = array[j];
            boolean find = false;
            for (int i = j - 1; i > -1; i--) {
                if (compareTo(array[i], key) == 1) {
                    array[i + 1] = array[i];
                    doUpdate(array);
                } else {
                    array[i + 1] = key;
                    find = true;
                    doUpdate(array);
                    break;
                }
            }
            if (!find) {
                array[0] = key;
            }
        }
    }

}
