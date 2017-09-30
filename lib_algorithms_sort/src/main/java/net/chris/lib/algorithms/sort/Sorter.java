package net.chris.lib.algorithms.sort;

import android.support.annotation.NonNull;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Sorter<T> {

    protected boolean update;
    protected Updatable<T> updatable;

    public List<T> sort(final List<T> list) {
        if (list == null) {
            return list;
        }
        return toList(sort(toArray(list), false));
    }

    public List<T> sort(final Collection<T> collection) {
        if (collection == null) {
            return null;
        }
        List<T> list = new ArrayList<>(collection.size());
        list.addAll(collection);
        return sort(list);
    }

    /**
     * {@param copy} is true.
     *
     * @param array
     * @return
     * @see {@link Sorter#sort(Object[], boolean)}
     */
    public T[] sort(final T[] array) {
        return sort(array, true);
    }

    /**
     * @param array
     * @param copy  copy the array to a new one to sort
     * @return
     */
    public T[] sort(final T[] array, final boolean copy) {
        if (array == null) {
            return array;
        }
        T[] list;
        if (copy) {
            list = newArray(array.length);
            System.arraycopy(array, 0, list, 0, array.length);
        } else {
            list = array;
        }
        subSort(list);
        return list;
    }

    protected abstract void subSort(final T[] list);

    /**
     * @param first
     * @param second
     * @return -1 (first < second); 0 (first = second); 1 (first > second)
     */
    protected abstract int compareTo(@NonNull final T first, @NonNull final T second);

    protected T[] toArray(final List<T> list) {
        if (list == null) {
            return null;
        }
        return list.toArray(newArray(0));
    }

    protected T[] newArray(final int length) {
        return (T[]) Array.newInstance(getTClass(), length);
    }

    @NonNull
    protected List<T> toList(final T[] sorted) {
        final List<T> sortedList = new ArrayList<>();
        if (sorted != null) {
            for (int i = 0; i < sorted.length; i++) {
                sortedList.add(sorted[i]);
            }
        }
        return sortedList;
    }

    private Class<T> getTClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected final void doUpdate(T[] array) {
        if (update && updatable != null) {
            updatable.update(toList(array));
        }
    }

    public Sorter<T> setUpdate(boolean update) {
        this.update = update;
        return this;
    }

    public Sorter<T> setUpdatable(final Updatable<T> updatable) {
        this.updatable = updatable;
        return this;
    }
}
