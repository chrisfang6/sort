package net.chris.exercises.sort;

public interface Constants {
    int MAX_VALUE = 100;
    int MAX_ITEM_COUNT = 20;
    int INTERVAL = 500;

    String TAG_SORT_FRAGMENT = "sort fragment";
    String TAG_KOTLIN_SORT_FRAGMENT = "kotlin sort fragment";

    enum Type {
        INSERTION_SORT,
        BUBBLE_SORT,
        MERGE_SORT,
        SELECTION_SORT,
        HEAP_SORT,
        BUCKET_SORT,
        QUICK_SORT,
        COUNTING_SORT,
        RADIX_SORT;

        public String getShowName() {
            switch (this) {
                case INSERTION_SORT:
                    return "Insertion Sort";
                case BUBBLE_SORT:
                    return "Bubble Sort";
                case MERGE_SORT:
                    return "Merge Sort";
                case SELECTION_SORT:
                    return "Selection Sort";
                case HEAP_SORT:
                    return "Heap Sort";
                case BUCKET_SORT:
                    return "Bucket Sort";
                case QUICK_SORT:
                    return "Quick Sort";
                case COUNTING_SORT:
                    return "Counting Sort";
                case RADIX_SORT:
                    return "Radix Sort";
            }
            return null;
        }
    }

    String INTENT_KEY_SORTED_BY_JNI = "INTENT_KEY_SORTED_BY_JNI";
}
