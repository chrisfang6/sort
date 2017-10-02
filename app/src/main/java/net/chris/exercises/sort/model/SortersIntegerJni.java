package net.chris.exercises.sort.model;

import net.chris.lib.algorithms.sort.jni.integer.BubbleSorterIntegerByJni;
import net.chris.lib.algorithms.sort.jni.integer.BucketSorterIntegerByJni;
import net.chris.lib.algorithms.sort.jni.integer.CountingSorterIntegerByJni;
import net.chris.lib.algorithms.sort.jni.integer.HeapSorterIntegerByJni;
import net.chris.lib.algorithms.sort.jni.integer.InsertionSorterIntegerByJni;
import net.chris.lib.algorithms.sort.jni.integer.MergeSorterIntegerByJni;
import net.chris.lib.algorithms.sort.jni.integer.QuickSorterIntegerByJni;
import net.chris.lib.algorithms.sort.jni.integer.RadixSorterIntegerByJni;
import net.chris.lib.algorithms.sort.jni.integer.SelectionSorterIntegerByJni;

public class SortersIntegerJni {

    public InsertionSorterIntegerByJni provideInsertionSorterInteger() {
        return new InsertionSorterIntegerByJni();
    }

    public BubbleSorterIntegerByJni provideBubbleSorterInteger() {
        return new BubbleSorterIntegerByJni();
    }

    public SelectionSorterIntegerByJni provideSelectionSorterInteger() {
        return new SelectionSorterIntegerByJni();
    }

    public MergeSorterIntegerByJni provideMergeSorterInteger() {
        return new MergeSorterIntegerByJni();
    }

    public QuickSorterIntegerByJni provideQuickSorterInteger() {
        return new QuickSorterIntegerByJni();
    }

    public HeapSorterIntegerByJni provideHeapSorterInteger() {
        return new HeapSorterIntegerByJni();
    }

    public BucketSorterIntegerByJni provideBucketSorterInteger() {
        return new BucketSorterIntegerByJni();
    }

    public CountingSorterIntegerByJni provideCountingSorterInteger() {
        return new CountingSorterIntegerByJni();
    }

    public RadixSorterIntegerByJni provideRadixSorterInteger() {
        return new RadixSorterIntegerByJni();
    }


}
