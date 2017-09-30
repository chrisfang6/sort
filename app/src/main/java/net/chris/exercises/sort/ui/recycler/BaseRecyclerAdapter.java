package net.chris.exercises.sort.ui.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

/**
 * Base {@link RecyclerView.Adapter}
 */

public abstract class BaseRecyclerAdapter<K, V, T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {

    @Override
    public int getItemCount() {
        return size();
    }

    public abstract int size();

    public abstract void setItems(final K items);

    public abstract K getItems();

    public abstract V getItem(@NonNull final int position);

    public abstract void clear();

}
