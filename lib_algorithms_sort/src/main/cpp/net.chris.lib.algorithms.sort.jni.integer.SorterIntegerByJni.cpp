#include <jni.h>
#include <stdlib.h>
#include <android/Log.h>
#include "InsertionSorter.h"
#include "BubbleSorter.h"
#include "SelectionSorter.h"
#include "MergeSorter.h"
#include "QuickSorter.h"
#include "HeapSorter.h"
#include "RadixSorter.h"
#include "CountingSorter.h"
#include "BucketSorter.h"

#define  TAG    "Sort JNI"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG,__VA_ARGS__)
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__)
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN, TAG,__VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,TAG,__VA_ARGS__)

extern "C"
JNIEXPORT jintArray JNICALL
Java_net_chris_lib_algorithms_sort_jni_integer_SorterIntegerByJni_insertionSort(JNIEnv *env,
                                                                                jclass type,
                                                                                jintArray src_) {

    LOGD("start insertion sorter");
    jint *src = env->GetIntArrayElements(src_, NULL);
    if (src == NULL) {
        return 0;
    }
    jint arr_len = env->GetArrayLength(src_);
    insertionSort(src, arr_len);
    env->ReleaseIntArrayElements(src_, src, 0);
    return src_;
}

extern "C"
JNIEXPORT jintArray JNICALL
Java_net_chris_lib_algorithms_sort_jni_integer_SorterIntegerByJni_bubbleSort(JNIEnv *env,
                                                                             jclass type,
                                                                             jintArray src_) {
    jint *src = env->GetIntArrayElements(src_, NULL);
    if (src == NULL) {
        return 0;
    }
    jint arr_len = env->GetArrayLength(src_);
    bubbleSort(src, arr_len);
    env->ReleaseIntArrayElements(src_, src, 0);
    return src_;
};

extern "C"
JNIEXPORT jintArray JNICALL
Java_net_chris_lib_algorithms_sort_jni_integer_SorterIntegerByJni_selectionSort(JNIEnv *env,
                                                                                jclass type,
                                                                                jintArray src_) {
    jint *src = env->GetIntArrayElements(src_, NULL);
    if (src == NULL) {
        return 0;
    }
    jint arr_len = env->GetArrayLength(src_);
    selectSort(src, arr_len);
    env->ReleaseIntArrayElements(src_, src, 0);
    return src_;
}

extern "C"
JNIEXPORT jintArray JNICALL
Java_net_chris_lib_algorithms_sort_jni_integer_SorterIntegerByJni_mergeSort(JNIEnv *env,
                                                                            jclass type,
                                                                            jintArray src_) {
    jint *src = env->GetIntArrayElements(src_, NULL);
    if (src == NULL) {
        return 0;
    }
    jint arr_len = env->GetArrayLength(src_);
    mergeStart(src, arr_len);
    env->ReleaseIntArrayElements(src_, src, 0);
    return src_;
}

extern "C"
JNIEXPORT jintArray JNICALL
Java_net_chris_lib_algorithms_sort_jni_integer_SorterIntegerByJni_quickSort(JNIEnv *env,
                                                                            jclass type,
                                                                            jintArray src_) {
    jint *src = env->GetIntArrayElements(src_, NULL);
    if (src == NULL) {
        return 0;
    }
    jint arr_len = env->GetArrayLength(src_);
    quickSort(src, arr_len);
    env->ReleaseIntArrayElements(src_, src, 0);
    return src_;
}

extern "C"
JNIEXPORT jintArray JNICALL
Java_net_chris_lib_algorithms_sort_jni_integer_SorterIntegerByJni_heapSort(JNIEnv *env,
                                                                           jclass type,
                                                                           jintArray src_) {
    jint *src = env->GetIntArrayElements(src_, NULL);
    if (src == NULL) {
        return 0;
    }
    jint arr_len = env->GetArrayLength(src_);
    heapSort(src, arr_len);
    env->ReleaseIntArrayElements(src_, src, 0);
    return src_;
}

extern "C"
JNIEXPORT jintArray JNICALL
Java_net_chris_lib_algorithms_sort_jni_integer_SorterIntegerByJni_bucketSort(JNIEnv *env,
                                                                             jclass type,
                                                                             jintArray src_) {
    jint *src = env->GetIntArrayElements(src_, NULL);
    if (src == NULL) {
        return 0;
    }
    jint arr_len = env->GetArrayLength(src_);
    bucketSort(src, arr_len);
    env->ReleaseIntArrayElements(src_, src, 0);
    return src_;
}

extern "C"
JNIEXPORT jintArray JNICALL
Java_net_chris_lib_algorithms_sort_jni_integer_SorterIntegerByJni_countingSort(JNIEnv *env,
                                                                               jclass type,
                                                                               jintArray src_) {
    jint *src = env->GetIntArrayElements(src_, NULL);
    if (src == NULL) {
        return 0;
    }
    jint arr_len = env->GetArrayLength(src_);
    countingSort(src, arr_len);
    env->ReleaseIntArrayElements(src_, src, 0);
    return src_;
}

extern "C"
JNIEXPORT jintArray JNICALL
Java_net_chris_lib_algorithms_sort_jni_integer_SorterIntegerByJni_radixSort(JNIEnv *env,
                                                                            jclass type,
                                                                            jintArray src_) {
    jint *src = env->GetIntArrayElements(src_, NULL);
    if (src == NULL) {
        return 0;
    }
    jint arr_len = env->GetArrayLength(src_);
    radixSort(src, arr_len);
    env->ReleaseIntArrayElements(src_, src, 0);
    return src_;
}
