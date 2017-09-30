package net.chris.exercises.sort.ui.recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import net.chris.exercises.sort.R;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChartAdapter extends BaseRecyclerAdapter<List<Integer>, Integer, ChartHolder> {

    private LayoutInflater layoutInflater;

    private final CopyOnWriteArrayList<Integer> items;

    public ChartAdapter(@NonNull final CopyOnWriteArrayList<Integer> items) {
        this.items = items;
    }

    @Override
    public ChartHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChartHolder(getInflater(parent.getContext()).inflate(R.layout.item_chart, parent, false));
    }

    @Override
    public void onBindViewHolder(ChartHolder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    public int size() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    @Override
    public void setItems(List<Integer> items) {
        clear();
        this.items.addAll(items);
    }

    @Override
    public List<Integer> getItems() {
        return this.items;
    }

    @Override
    public Integer getItem(@NonNull int position) {
        if (position > -1 && position < getItemCount()) {
            return items.get(position);
        }
        return null;
    }

    @Override
    public void clear() {
        if (items != null) {
            items.clear();
        }
    }

    private LayoutInflater getInflater(@NonNull final Context context) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(context);
        }
        return layoutInflater;
    }
}
