package net.chris.exercises.sort.kotlin

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import net.chris.exercises.sort.Constants
import net.chris.exercises.sort.Constants.Type
import net.chris.exercises.sort.R
import net.chris.exercises.sort.comm.RxSubject
import net.chris.exercises.sort.inject.FragmentComponent
import net.chris.exercises.sort.kotlin.recycler.KTChartAdapterList
import net.chris.lib.algorithms.sort.kotlin.*
import java.util.*
import javax.inject.Inject

class KTSortFragment : Fragment() {

    @Inject
    lateinit var typeRxSubject: RxSubject<Type>;

    private var start: View? = null
    private var reset: View? = null

    private var hint: TextView? = null

    private var type = Type.BUCKET_SORT

    private var handler: Handler? = null

    private var recycler: RecyclerView? = null
    private lateinit var adapter: KTChartAdapterList

    private lateinit var typeDisposable: Disposable

    companion object {
        fun newInstance(): KTSortFragment {
            return KTSortFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FragmentComponent.getFragmentComponent().inject(this)
        handler = Handler()
        typeRxSubject.listen()
                .doOnSubscribe({ disposable -> typeDisposable = disposable })
                .observeOn(Schedulers.io())
//                .compose(bindToLifecycle())
                .doOnNext({ newType -> type = newType })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ newType -> setHint(newType) })

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_sort_kotlin, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view!!.findViewById(R.id.chart_recycler) as RecyclerView
        adapter = getAdapter()
        recycler!!.adapter = adapter
        recycler!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        start = view.findViewById(R.id.start_sort)
        reset = view.findViewById(R.id.reset)
        start!!.setOnClickListener {
            reset!!.isEnabled = false
            start!!.isEnabled = false
            startSort(type)
        }
        reset!!.setOnClickListener {
            adapter.items = createData(Random())
            adapter!!.notifyDataSetChanged()
        }
        hint = view.findViewById(R.id.sort_hint) as TextView
    }

    override fun onResume() {
        super.onResume()
        setHint(type)
    }

    override fun onDestroy() {
        super.onDestroy()
        typeDisposable.dispose()
    }

    private fun getAdapter(): KTChartAdapterList {
        return KTChartAdapterList()
    }

    private fun createData(r: Random): List<Int> {
        val items = Array(Constants.MAX_ITEM_COUNT, { 0 })
        var i = 0
        while (i < Constants.MAX_ITEM_COUNT) {
            val newValue = r.nextInt((Constants.MAX_VALUE + 1) - 0)
            var same = false
            for (j in 0 until i) {
                if (newValue == items[j]) {
                    same = true
                }
            }
            if (same) {
                i--
                i++
                continue
            } else {
                items[i] = newValue
            }
            i++
        }
        return items.toList()
    }

    private fun startSort(byType: Constants.Type) {
        Thread(Runnable {
            var sorter: KTSorter
            when (byType) {
                Constants.Type.BUBBLE_SORT -> sorter = bubbleSorter
                Constants.Type.INSERTION_SORT -> sorter = insertionSorter
                Constants.Type.MERGE_SORT -> sorter = mergeSorter
                Constants.Type.SELECTION_SORT -> sorter = selectionSorter
                Constants.Type.COUNTING_SORT -> sorter = countingSorter
                Constants.Type.HEAP_SORT -> sorter = heapSorter
                Constants.Type.QUICK_SORT -> sorter = quickSorter
                Constants.Type.RADIX_SORT -> sorter = radixSorter
                Constants.Type.BUCKET_SORT -> sorter = bucketSorter
            }
            val newItems = sorter!!.sort(adapter!!.items)
            handler!!.post {
                reset!!.isEnabled = true
                start!!.isEnabled = true
                adapter.items = newItems
                adapter!!.notifyDataSetChanged()
            }
        }).start()
    }

    private val radixSorter: KTSorter
        get() = object : KTRadixSorter() {
            override fun compareTo(first: Int, second: Int): Int {
                return first.compareTo(second)
            }

            override fun update(list: List<Int>) {
                this@KTSortFragment.update(list)
            }

            override fun update(array: IntArray) {}
        }.setUpdate(true)

    private val bucketSorter: KTSorter
        get() = object : KTBucketSorter() {
            override fun compareTo(first: Int, second: Int): Int {
                return first.compareTo(second)
            }

            override fun update(list: List<Int>) {
                this@KTSortFragment.update(list)
            }

            override fun update(array: IntArray) {}

            override val sorter: KTSorter
                get() = insertionSorter

            override val demarcations: IntArray
                get() = intArrayOf(-1, 25, 26, 50, 51, 75, 76, 100)

        }.setUpdate(true)

    private val heapSorter: KTSorter
        get() = object : KTHeapSorter() {
            override fun compareTo(first: Int, second: Int): Int {
                return first.compareTo(second)
            }

            override fun update(list: List<Int>) {
                this@KTSortFragment.update(list)
            }

            override fun update(array: IntArray) {}
        }.setUpdate(true)

    private val selectionSorter: KTSorter
        get() = object : KTSelectionSorter() {
            override fun compareTo(first: Int, second: Int): Int {
                return first.compareTo(second)
            }

            override fun update(list: List<Int>) {
                this@KTSortFragment.update(list)
            }

            override fun update(array: IntArray) {}
        }.setUpdate(true)

    private val countingSorter: KTSorter
        get() = object : KTCountingSorter() {
            override fun compareTo(first: Int, second: Int): Int {
                return first.compareTo(second)
            }

            override fun update(list: List<Int>) {
                this@KTSortFragment.update(list)
            }

            override fun update(array: IntArray) {}
        }.setUpdate(true)

    private val bubbleSorter: KTSorter
        get() = object : KTBubbleSorter() {
            override fun compareTo(first: Int, second: Int): Int {
                return first.compareTo(second)
            }

            override fun update(list: List<Int>) {
                this@KTSortFragment.update(list)
            }

            override fun update(array: IntArray) {}
        }.setUpdate(true)

    private val mergeSorter: KTSorter
        get() = object : KTMergeSorter() {
            override fun compareTo(first: Int, second: Int): Int {
                return first.compareTo(second)
            }

            override fun update(list: List<Int>) {
                this@KTSortFragment.update(list)
            }

            override fun update(array: IntArray) {}

            override val infinity: Int
                get() = Int.MAX_VALUE
        }.setUpdate(true)

    private val insertionSorter: KTSorter
        get() = object : KTInsertionSorter() {
            override fun compareTo(first: Int, second: Int): Int {
                return first.compareTo(second)
            }

            override fun update(list: List<Int>) {
                this@KTSortFragment.update(list)
            }

            override fun update(array: IntArray) {}
        }.setUpdate(true)

    private val quickSorter: KTSorter
        get() = object : KTQuickSorter() {
            override fun compareTo(first: Int, second: Int): Int {
                return first.compareTo(second)
            }

            override fun update(list: List<Int>) {
                this@KTSortFragment.update(list)
            }

            override fun update(array: IntArray) {}
        }.setUpdate(true)

    private fun update(list: List<Int>) {
        handler!!.post {
            Log.e("post", "post")
            val temp = ArrayList<Int>()
            temp.addAll(list)
            adapter.items = temp
            adapter!!.notifyDataSetChanged()
        }
        try {
            Thread.sleep(Constants.INTERVAL.toLong())
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

    }

    private fun setHint(type: Type) {
        hint!!.setText(String.format(getString(R.string.sort_hint_txt), getSortName(type), "KOTLIN"))
    }

    private fun getSortName(type: Type): String {
        var name = ""
        when (type) {
            Constants.Type.QUICK_SORT -> name = "QUICK"
            Constants.Type.INSERTION_SORT -> name = "INSERTION"
            Constants.Type.MERGE_SORT -> name = "MERGE"
            Constants.Type.BUBBLE_SORT -> name = "BUBBLE"
            Constants.Type.COUNTING_SORT -> name = "COUNTING"
            Constants.Type.SELECTION_SORT -> name = "SELECTION"
            Constants.Type.HEAP_SORT -> name = "HEAP"
            Constants.Type.BUCKET_SORT -> name = "BUCKET"
            Constants.Type.RADIX_SORT -> name = "RADIX"
        }
        return name
    }
}
