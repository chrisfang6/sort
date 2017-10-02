package net.chris.exercises.sort.ui;

import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.components.support.RxFragment;

import net.chris.exercises.sort.R;
import net.chris.exercises.sort.databinding.FragmentSortBinding;
import net.chris.exercises.sort.inject.FragmentComponent;
import net.chris.exercises.sort.ui.model.SortViewModel;
import net.chris.exercises.sort.ui.recycler.BaseRecyclerAdapter;
import net.chris.exercises.sort.ui.recycler.ChartAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static net.chris.exercises.sort.Constants.TAG_SORT_FRAGMENT;

public class SortFragment extends RxFragment {

    private static final String TAG = SortFragment.class.getSimpleName();

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

    public static void createAndResume(@NonNull final FragmentManager fragmentManager, @NonNull final int fragment_container, @NonNull final Bundle args) {
        Fragment fragment = new SortFragment();
        fragment.setArguments(new Bundle());
        if (args != null) {
            fragment.getArguments().putAll(args);
        }
        fragmentManager.beginTransaction().add(fragment_container, fragment, TAG_SORT_FRAGMENT).commit();
    }

    public static void createOrResume(@NonNull final FragmentManager fragmentManager, @NonNull final int fragment_container, @NonNull final Bundle args) {
        if (isOnTop(fragmentManager, TAG_SORT_FRAGMENT) || isFragmentVisible(fragmentManager, TAG_SORT_FRAGMENT)) {
            SortFragment fragment = (SortFragment) fragmentManager.findFragmentByTag(TAG_SORT_FRAGMENT);
            Bundle arguments = fragment.getArguments();
            arguments.putAll(args);
            fragment.sortViewModel.register(fragment);
            return;
        }
        Fragment fragment = new SortFragment();
        fragment.setArguments(new Bundle());
        if (args != null) {
            fragment.getArguments().putAll(args);
        }
        fragmentManager.beginTransaction().replace(fragment_container, fragment, TAG_SORT_FRAGMENT).commit();
    }

    protected static boolean isFragmentVisible(@NonNull FragmentManager fragmentManager, @NonNull final String tag) {
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        return fragment != null && fragment.isVisible();
    }

    protected static boolean isOnTop(@NonNull final FragmentManager fragmentManager, @NonNull final String tag) {
        try {
            final int backStackCount = fragmentManager.getBackStackEntryCount();
            if (backStackCount < 1) {
                return false;
            }
            final String topTag = fragmentManager.getBackStackEntryAt(backStackCount - 1).getName();
            if (tag.equals(topTag)) {
                Log.w(TAG, String.format("Current fragment (%s) is on the screen.", tag));
                return true;
            }
        } catch (Exception e) {
            Log.e(TAG, "err", e);
        }
        return false;
    }


}
