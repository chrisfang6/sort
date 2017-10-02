package net.chris.exercises.sort.inject;

import android.support.annotation.NonNull;

import net.chris.exercises.sort.model.SortersInteger;
import net.chris.exercises.sort.model.SortersIntegerJni;
import net.chris.lib.algorithms.sort.BubbleSorter;
import net.chris.lib.algorithms.sort.BucketSorter;
import net.chris.lib.algorithms.sort.CountingSorter;
import net.chris.lib.algorithms.sort.HeapSorter;
import net.chris.lib.algorithms.sort.InsertionSorter;
import net.chris.lib.algorithms.sort.MergeSorter;
import net.chris.lib.algorithms.sort.QuickSorter;
import net.chris.lib.algorithms.sort.RadixSorter;
import net.chris.lib.algorithms.sort.SelectionSorter;
import net.chris.lib.algorithms.sort.jni.integer.BubbleSorterIntegerByJni;
import net.chris.lib.algorithms.sort.jni.integer.BucketSorterIntegerByJni;
import net.chris.lib.algorithms.sort.jni.integer.CountingSorterIntegerByJni;
import net.chris.lib.algorithms.sort.jni.integer.HeapSorterIntegerByJni;
import net.chris.lib.algorithms.sort.jni.integer.InsertionSorterIntegerByJni;
import net.chris.lib.algorithms.sort.jni.integer.MergeSorterIntegerByJni;
import net.chris.lib.algorithms.sort.jni.integer.QuickSorterIntegerByJni;
import net.chris.lib.algorithms.sort.jni.integer.RadixSorterIntegerByJni;
import net.chris.lib.algorithms.sort.jni.integer.SelectionSorterIntegerByJni;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SorterModule {

    @Singleton
    @Provides
    public SortersInteger providesSortersInteger() {
        return new SortersInteger();
    }

    @Provides
    public InsertionSorter<Integer> provideInsertionSorter(@NonNull SortersInteger sorters) {
        return sorters.provideInsertionSorter();
    }

    @Provides
    public BubbleSorter<Integer> provideBubbleSorter(@NonNull SortersInteger sorters) {
        return sorters.provideBubbleSorter();
    }

    @Provides
    public SelectionSorter<Integer> provideSelectionSorter(@NonNull SortersInteger sorters) {
        return sorters.provideSelectionSorter();
    }

    @Provides
    public MergeSorter<Integer> provideMergeSorter(@NonNull SortersInteger sorters) {
        return sorters.provideMergeSorter();
    }

    @NonNull
    @Provides
    public QuickSorter<Integer> provideQuickSorter(@NonNull SortersInteger sorters) {
        return sorters.provideQuickSorter();
    }

    @Provides
    public HeapSorter<Integer> provideHeapSorter(@NonNull SortersInteger sorters) {
        return sorters.provideHeapSorter();
    }

    @Provides
    public BucketSorter<Integer, Float> provideBucketSorter(@NonNull SortersInteger sorters, final InsertionSorter<Integer> sorter) {
        return sorters.provideBucketSorter(sorter);
    }

    @Provides
    public CountingSorter provideCountingSorter(@NonNull SortersInteger sorters) {
        return sorters.provideCountingSorter();
    }

    @Provides
    public RadixSorter provideRadixSorter(@NonNull SortersInteger sorters) {
        return sorters.provideRadixSorter();
    }

    @Singleton
    @Provides
    public SortersIntegerJni providesSortersIntegerJni() {
        return new SortersIntegerJni();
    }

    @Provides
    public InsertionSorterIntegerByJni provideInsertionSorterIntegerByJni(@NonNull SortersIntegerJni sorters) {
        return sorters.provideInsertionSorterInteger();
    }

    @Provides
    public BubbleSorterIntegerByJni provideBubbleSorterIntegerByJni(@NonNull SortersIntegerJni sorters) {
        return sorters.provideBubbleSorterInteger();
    }

    @Provides
    public SelectionSorterIntegerByJni provideSelectionSorterIntegerByJni(@NonNull SortersIntegerJni sorters) {
        return sorters.provideSelectionSorterInteger();
    }

    @Provides
    public MergeSorterIntegerByJni provideMergeSorterIntegerByJni(@NonNull SortersIntegerJni sorters) {
        return sorters.provideMergeSorterInteger();
    }

    @Provides
    public QuickSorterIntegerByJni provideQuickSorterIntegerByJni(@NonNull SortersIntegerJni sorters) {
        return sorters.provideQuickSorterInteger();
    }

    @Provides
    public HeapSorterIntegerByJni provideHeapSorterIntegerByJni(@NonNull SortersIntegerJni sorters) {
        return sorters.provideHeapSorterInteger();
    }


    @Provides
    public BucketSorterIntegerByJni provideBucketSorterIntegerByJni(@NonNull SortersIntegerJni sorters) {
        return sorters.provideBucketSorterInteger();
    }


    @Provides
    public CountingSorterIntegerByJni provideCountingSorterIntegerByJni(@NonNull SortersIntegerJni sorters) {
        return sorters.provideCountingSorterInteger();
    }


    @Provides
    public RadixSorterIntegerByJni provideRadixSorterIntegerByJni(@NonNull SortersIntegerJni sorters) {
        return sorters.provideRadixSorterInteger();
    }


}
