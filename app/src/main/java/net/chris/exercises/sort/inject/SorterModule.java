package net.chris.exercises.sort.inject;

import android.support.annotation.NonNull;

import net.chris.exercises.sort.model.SortersInteger;
import net.chris.lib.algorithms.sort.BubbleSorter;
import net.chris.lib.algorithms.sort.BucketSorter;
import net.chris.lib.algorithms.sort.CountingSorter;
import net.chris.lib.algorithms.sort.HeapSorter;
import net.chris.lib.algorithms.sort.InsertionSorter;
import net.chris.lib.algorithms.sort.MergeSorter;
import net.chris.lib.algorithms.sort.QuickSorter;
import net.chris.lib.algorithms.sort.RadixSorter;
import net.chris.lib.algorithms.sort.SelectionSorter;

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

}
