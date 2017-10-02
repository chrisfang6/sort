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
import net.chris.lib.algorithms.sort.jni.integer.BubbleSorterIntegerByJni;
import net.chris.lib.algorithms.sort.jni.integer.BucketSorterIntegerByJni;
import net.chris.lib.algorithms.sort.jni.integer.CountingSorterIntegerByJni;
import net.chris.lib.algorithms.sort.jni.integer.HeapSorterIntegerByJni;
import net.chris.lib.algorithms.sort.jni.integer.InsertionSorterIntegerByJni;
import net.chris.lib.algorithms.sort.jni.integer.MergeSorterIntegerByJni;
import net.chris.lib.algorithms.sort.jni.integer.QuickSorterIntegerByJni;
import net.chris.lib.algorithms.sort.jni.integer.RadixSorterIntegerByJni;
import net.chris.lib.algorithms.sort.jni.integer.SelectionSorterIntegerByJni;

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

import static net.chris.exercises.sort.Constants.INTENT_KEY_SORTED_BY_JNI;

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
    @Inject
    InsertionSorterIntegerByJni insertionSorterIntegerByJni;
    @Inject
    BubbleSorterIntegerByJni bubbleSorterIntegerByJni;
    @Inject
    SelectionSorterIntegerByJni selectionSorterIntegerByJni;
    @Inject
    MergeSorterIntegerByJni mergeSorterIntegerByJni;
    @Inject
    QuickSorterIntegerByJni quickSorterIntegerByJni;
    @Inject
    HeapSorterIntegerByJni heapSorterIntegerByJni;
    @Inject
    BucketSorterIntegerByJni bucketSorterIntegerByJni;
    @Inject
    CountingSorterIntegerByJni countingSorterIntegerByJni;
    @Inject
    RadixSorterIntegerByJni radixSorterIntegerByJni;

    private Constants.Type type = Type.INSERTION_SORT;

    private final ObservableBoolean byJni = new ObservableBoolean(false);

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
        if (typeDisposable != null) {
            typeDisposable.dispose();
        }
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
                sorter = byJni.get() ? quickSorterIntegerByJni : quickSorter;
                break;
            case INSERTION_SORT:
                sorter = byJni.get() ? insertionSorterIntegerByJni : insertionSorter;
                break;
            case MERGE_SORT:
                sorter = byJni.get() ? mergeSorterIntegerByJni : mergeSorter;
                break;
            case BUBBLE_SORT:
                sorter = byJni.get() ? bubbleSorterIntegerByJni : bubbleSorterIntegerByJni;
                break;
            case COUNTING_SORT:
                sorter = byJni.get() ? countingSorterIntegerByJni : countingSorter;
                break;
            case SELECTION_SORT:
                sorter = byJni.get() ? selectionSorterIntegerByJni : selectionSorter;
                break;
            case HEAP_SORT:
                sorter = byJni.get() ? heapSorterIntegerByJni : heapSorter;
                break;
            case BUCKET_SORT:
                sorter = byJni.get() ? bucketSorterIntegerByJni : bucketSorter;
                break;
            case RADIX_SORT:
                sorter = byJni.get() ? radixSorterIntegerByJni : radixSorter;
                break;
        }
        if (sorter == null) {
            return items;
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
        boolean same = context != null ? context.get() == fragment : false;
        context = (fragment == null) ? null : new WeakReference<>(fragment);
        if (fragment != null) {
            byJni.set(byJni(fragment));
            if (!same) {
                startListen();
            }
        }
    }

    public void unregister() {
        stopListen();
        context = null;
    }

    private boolean byJni(RxFragment fragment) {
        if (fragment != null && fragment.getArguments() != null) {
            return fragment.getArguments().getBoolean(INTENT_KEY_SORTED_BY_JNI);
        }
        return false;
    }

    public CopyOnWriteArrayList<Integer> getItems() {
        return items;
    }

    public SortDescription getSortDescription() {
        return sortDescription;
    }

    public ObservableBoolean getByJni() {
        return byJni;
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
