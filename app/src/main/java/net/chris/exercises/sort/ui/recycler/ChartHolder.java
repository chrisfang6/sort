package net.chris.exercises.sort.ui.recycler;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import net.chris.exercises.sort.BR;

public class ChartHolder extends RecyclerView.ViewHolder {

    private ViewDataBinding binding;

    public ChartHolder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

    public void bind(Integer item) {
        if (item == null || binding == null) {
            return;
        }
        binding.setVariable(BR.value, item);
        binding.executePendingBindings();
    }

}
