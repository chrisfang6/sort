package net.chris.exercises.sort.model;

import android.support.annotation.NonNull;

import net.chris.lib.algorithms.sort.BubbleSorter;
import net.chris.lib.algorithms.sort.BucketSorter;
import net.chris.lib.algorithms.sort.CountingSorter;
import net.chris.lib.algorithms.sort.HeapSorter;
import net.chris.lib.algorithms.sort.InsertionSorter;
import net.chris.lib.algorithms.sort.MergeSorter;
import net.chris.lib.algorithms.sort.QuickSorter;
import net.chris.lib.algorithms.sort.RadixSorter;
import net.chris.lib.algorithms.sort.SelectionSorter;
import net.chris.lib.algorithms.sort.Sorter;

public class SortersInteger {

    public InsertionSorter<Integer> provideInsertionSorter() {
        return new InsertionSorter<Integer>() {
            @Override
            protected int compareTo(@NonNull Integer first, @NonNull Integer second) {
                return first.compareTo(second);
            }
        };
    }

    public BubbleSorter<Integer> provideBubbleSorter() {
        return new BubbleSorter<Integer>() {
            @Override
            protected int compareTo(@NonNull Integer first, @NonNull Integer second) {
                return first.compareTo(second);
            }
        };
    }

    public SelectionSorter<Integer> provideSelectionSorter() {
        return new SelectionSorter<Integer>() {
            @Override
            protected int compareTo(@NonNull Integer first, @NonNull Integer second) {
                return first.compareTo(second);
            }
        };
    }

    public MergeSorter<Integer> provideMergeSorter() {
        return new MergeSorter<Integer>() {
            @Override
            protected Integer getInfinity() {
                return Integer.MAX_VALUE;
            }

            @Override
            protected int compareTo(@NonNull Integer first, @NonNull Integer second) {
                return first.compareTo(second);
            }
        };
    }

    public QuickSorter<Integer> provideQuickSorter() {
        return new QuickSorter<Integer>() {
            @Override
            protected int compareTo(@NonNull Integer first, @NonNull Integer second) {
                return first.compareTo(second);
            }
        };
    }

    public HeapSorter<Integer> provideHeapSorter() {
        return new HeapSorter<Integer>() {
            @Override
            protected int compareTo(@NonNull Integer first, @NonNull Integer second) {
                return first.compareTo(second);
            }
        };
    }

    public BucketSorter<Integer, Float> provideBucketSorter(final InsertionSorter<Integer> sorter) {
        return new BucketSorter<Integer, Float>() {
            /**
             * @param first
             * @param second
             * @return -1 (first < second); 0 (first = second); 1 (first > second)
             */
            @Override
            protected int compareTo(@NonNull Integer first, @NonNull Integer second) {
                return first.compareTo(second);
            }

            /**
             * @param first
             * @param second
             * @return -1 (first < second); 0 (first = second); 1 (first > second)
             */
            @Override
            protected int compare(@NonNull Integer first, @NonNull Float second) {
                return first < second ? -1 : (first.floatValue() > second ? 1 : 0);
            }

            @Override
            protected Sorter<Integer> getSorter() {
                return sorter;
            }

            @Override
            protected Float[] getDemarcations() {
                return new Float[]{-1f, 25f, 26f, 50f, 51f, 75f, 76f, 100f};
            }
        };
    }

    public CountingSorter provideCountingSorter() {
        return new CountingSorter() {
            @Override
            protected int compareTo(@NonNull Integer first, @NonNull Integer second) {
                return first.compareTo(second);
            }
        };
    }

    public RadixSorter provideRadixSorter() {
        return new RadixSorter() {
            @Override
            protected int compareTo(@NonNull Integer first, @NonNull Integer second) {
                return first.compareTo(second);
            }
        };
    }

}
