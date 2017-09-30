package net.chris.lib.algorithms.sort;

/**
 * Selection sort.
 *
 * @param <T>
 */
public abstract class SelectionSorter<T> extends Sorter<T> {

    @Override
    protected void subSort(T[] array) {
        if (array == null) {
            return;
        }
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            T temp;
            //find min
            for (int j = i + 1; j < array.length; j++) {
                if (compareTo(array[j], array[min]) < 0) {
                    min = j;
                }
            }
            //swap the min with the ith element
            temp = array[min];
            array[min] = array[i];
            array[i] = temp;
            doUpdate(array);
        }
    }

}
