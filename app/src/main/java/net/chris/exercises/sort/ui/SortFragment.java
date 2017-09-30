package net.chris.exercises.sort.ui;

import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.components.support.RxFragment;

import net.chris.exercises.sort.R;
import net.chris.exercises.sort.databinding.FragmentSortBinding;
import net.chris.exercises.sort.inject.FragmentComponent;
import net.chris.exercises.sort.ui.model.SortViewModel;
import net.chris.exercises.sort.ui.recycler.ChartAdapter;
import net.chris.exercises.sort.ui.recycler.BaseRecyclerAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SortFragment extends RxFragment {

    @Inject
    SortViewModel sortViewModel;

    private Unbinder unbinder;

    @BindView(R.id.chart_recycler)
    RecyclerView recycler;
    @BindView(R.id.start_sort)
    View start;
    @BindView(R.id.reset)
    View reset;

    private BaseRecyclerAdapter adapter;

    private final Handler handler = new Handler();

    private final Observable.OnPropertyChangedCallback callback = new Observable.OnPropertyChangedCallback() {
        @Override
        public void onPropertyChanged(Observable sender, int propertyId) {
            if (sortViewModel.updateAdapter.get()) {
                handler.post(() -> adapter.notifyDataSetChanged());
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentComponent.getFragmentComponent().inject(this);
        sortViewModel.register(this);
        sortViewModel.updateAdapter.addOnPropertyChangedCallback(callback);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentSortBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sort, container, false);
        unbinder = ButterKnife.bind(this, binding.getRoot());
        binding.setViewModel(sortViewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new ChartAdapter(sortViewModel.getItems());
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sortViewModel.updateAdapter.removeOnPropertyChangedCallback(callback);
        sortViewModel.unregister();
    }

}
