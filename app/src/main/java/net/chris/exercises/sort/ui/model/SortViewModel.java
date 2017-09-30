package net.chris.exercises.sort.ui.model;

import android.databinding.BindingAdapter;
import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;
import android.support.percent.PercentRelativeLayout;
import android.util.Log;
import android.view.View;

import com.trello.rxlifecycle2.components.support.RxFragment;

import net.chris.exercises.sort.Constants;
import net.chris.exercises.sort.Constants.Type;
import net.chris.exercises.sort.comm.RxSubject;
import net.chris.exercises.sort.inject.SorterComponent;
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
import net.chris.lib.algorithms.sort.Updatable;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SortViewModel {

    private static final String TAG = SortViewModel.class.getSimpleName();

    @Inject
    RxSubject<Constants.Type> typeRxSubject;
    @Inject
    InsertionSorter<Integer> insertionSorter;
    @Inject
    BubbleSorter<Integer> bubbleSorter;
    @Inject
    SelectionSorter<Integer> selectionSorter;
    @Inject
    MergeSorter<Integer> mergeSorter;
    @Inject
    QuickSorter<Integer> quickSorter;
    @Inject
    HeapSorter<Integer> heapSorter;
    @Inject
    BucketSorter<Integer, Float> bucketSorter;
    @Inject
    CountingSorter countingSorter;
    @Inject
    RadixSorter radixSorter;

    private Constants.Type type = Type.INSERTION_SORT;

    private final CopyOnWriteArrayList<Integer> items = new CopyOnWriteArrayList<>();

    private final SortDescription sortDescription = new SortDescription(type.getShowName());

    private WeakReference<RxFragment> context;

    private Disposable typeDisposable;

    private final Updatable<Integer> updatable = list -> {
        Observable.just(list)
                .observeOn(AndroidSchedulers.mainThread())
                .compose(context.get().bindToLifecycle())
                .subscribe(items -> updateAdapter(items), throwable -> Log.e(TAG, "update intermediates failed", throwable));
        try {
            Thread.sleep(Constants.INTERVAL);
        } catch (Exception e) {
            Log.e(TAG, "sleep interrupted", e);
        }
    };

    /**
     * enable status of 'reset' button.
     */
    public final ObservableBoolean enableResetButtons = new ObservableBoolean(true);

    /**
     * enable status of 'start' button.
     */
    public final ObservableBoolean enableStartButtons = new ObservableBoolean(false);

    /**
     * update adapter when it's true.
     */
    public final ObservableBoolean updateAdapter = new ObservableBoolean(false);

    public final Command startSort = () -> startSort();

    public final Command reset = () -> reset();


    public SortViewModel() {
        SorterComponent.getSorterComponent().inject(this);
    }

    private void startListen() {
        typeRxSubject.listen()
                .doOnSubscribe(disposable -> typeDisposable = disposable)
                .observeOn(Schedulers.io())
                .compose(context.get().bindToLifecycle())
                .doOnNext(type -> this.type = type)
                .subscribe(type -> sortDescription.sortTypeName.set(type.getShowName()), throwable -> Log.e(TAG, "Can's set type.", throwable));
    }

    private void stopListen() {
        typeDisposable.dispose();
    }

    private void startSort() {
        Observable.just(type)
                .doOnSubscribe(disposable -> {
                    enableResetButtons.set(false);
                    enableStartButtons.set(false);
                })
                .compose(context.get().bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(sortType -> sort(sortType, updatable))
                .doAfterTerminate(() -> enableResetButtons.set(true))
                .subscribe(newItems -> updateAdapter(newItems), throwable -> Log.e(TAG, "Can's start sorting.", throwable));
    }

    private void updateAdapter(final List<Integer> newItems) {
        items.clear();
        items.addAll(newItems);
        updateAdapter.set(true);
        updateAdapter.set(false);
    }

    private void reset() {
        updateAdapter(createData(new Random()));
        enableStartButtons.set(true);
    }

    private List<Integer> sort(@NonNull final Constants.Type type, final Updatable<Integer> updatable) {
        Sorter<Integer> sorter = null;
        switch (type) {
            case QUICK_SORT:
                sorter = quickSorter;
                break;
            case INSERTION_SORT:
                sorter = insertionSorter;
                break;
            case MERGE_SORT:
                sorter = mergeSorter;
                break;
            case BUBBLE_SORT:
                sorter = bubbleSorter;
                break;
            case COUNTING_SORT:
                sorter = countingSorter;
                break;
            case SELECTION_SORT:
                sorter = selectionSorter;
                break;
            case HEAP_SORT:
                sorter = heapSorter;
                break;
            case BUCKET_SORT:
                sorter = bucketSorter;
                break;
            case RADIX_SORT:
                sorter = radixSorter;
                break;
        }
        return sorter.setUpdate(true).setUpdatable(updatable).sort(items);
    }

    @NonNull
    private List<Integer> createData(Random r) {
        Set<Integer> items = new HashSet<>();
        do {
            items.add((Math.abs(r.nextInt()) % Constants.MAX_VALUE));
        } while (items.size() < Constants.MAX_ITEM_COUNT);
        List<Integer> list = new ArrayList<>();
        list.addAll(items);
        items.clear();
        return list;
    }

    public void register(final RxFragment fragment) {
        context = (fragment == null) ? null : new WeakReference<>(fragment);
        if (fragment != null) {
            startListen();
        }
    }

    public void unregister() {
        stopListen();
        context = null;
    }

    public CopyOnWriteArrayList<Integer> getItems() {
        return items;
    }

    public SortDescription getSortDescription() {
        return sortDescription;
    }

    @BindingAdapter("app:layout_heightPercent")
    public static void setHeightPercent(View view, Integer height) {
        if (height == null) {
            return;
        }
        PercentRelativeLayout.LayoutParams params = (PercentRelativeLayout.LayoutParams) view.getLayoutParams();
        if (params == null) {
            return;
        }
        params.getPercentLayoutInfo().heightPercent = ((float) height) / Constants.MAX_VALUE;
    }

    @BindingAdapter("bind:onClick")
    public static void onClick(final View view, final Command command) {
        view.setOnClickListener(v -> {
            if (command != null) {
                command.execute();
            }
        });
    }


}
