package net.chris.lib.algorithms.sort;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Bucket sort.
 *
 * @param <T>
 */
public abstract class BucketSorter<T, K> extends Sorter<T> {

    private List<Bucket> buckets;

    @Override
    protected void subSort(T[] array) {
        if (array == null) {
            return;
        }
        createBuckets();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < buckets.size(); j++) {
                if (buckets.get(j).inBucket(array[i])) {
                    buckets.get(j).add(array[i]);
                    break;
                }
            }
        }
        T[] result = newArray(array.length);
        int index = 0;
        for (int j = 0; j < buckets.size(); j++) {
            T[] bucketArray = buckets.get(j).sort();
            for (int i = 0; i < bucketArray.length; i++) {
                result[index] = bucketArray[i];
                index++;
            }
        }
        for (int i = 0; i < result.length; i++) {
            array[i] = result[i];
        }
    }

    private void createBuckets() {
        final int size = getDemarcations().length + 1;
        buckets = new ArrayList<>(size);
        for (int i = 0; i < getDemarcations().length; i += 2) {
            K left = getDemarcations()[i];
            K right = getDemarcations()[i + 1];
            buckets.add(new Bucket(left, right));
        }
    }

    /**
     * @param first
     * @param second
     * @return -1 (first < second); 0 (first = second); 1 (first > second)
     */
    protected abstract int compare(@NonNull final T first, @NonNull final K second);

    protected abstract Sorter<T> getSorter();

    protected abstract K[] getDemarcations();

    /**
     *
     */
    public class Bucket {

        private final K left;
        private final K right;

        private List<T> list = new ArrayList<>();

        private Bucket(K left, K right) {
            this.left = left;
            this.right = right;
        }

        public boolean inBucket(T element) {
            return (compare(element, left) >= 0 && compare(element, right) <= 0) ? true : false;
        }

        private void add(T element) {
            list.add(element);
        }

        private T[] sort() {
            return getSorter().sort(toArray(list));
        }
    }

}
