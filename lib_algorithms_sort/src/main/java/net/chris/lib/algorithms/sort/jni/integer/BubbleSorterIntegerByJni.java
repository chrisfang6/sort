package net.chris.lib.algorithms.sort.jni.integer;

import android.support.annotation.NonNull;

import net.chris.lib.algorithms.sort.Sorter;

import org.apache.commons.lang3.ArrayUtils;

public class BubbleSorterIntegerByJni extends Sorter<Integer> {

    @Override
    protected void subSort(Integer[] array) {
        int[] src = ArrayUtils.toPrimitive(array);
        SorterIntegerByJni.bubbleSort(src);
        if (src == null) {
            return;
        }
        for (int i = 0; i < src.length; i++) {
            array[i] = src[i];
        }
    }

    /**
     * @param first
     * @param second
     * @return -1 (first < second); 0 (first = second); 1 (first > second)
     */
    @Override
    protected int compareTo(@NonNull Integer first, @NonNull Integer second) {
        return first.compareTo(second);
    }
}
