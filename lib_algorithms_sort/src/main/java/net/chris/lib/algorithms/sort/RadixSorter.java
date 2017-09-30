package net.chris.lib.algorithms.sort;

import java.util.List;

/**
 * Radix sort.
 */
public abstract class RadixSorter extends Sorter<Integer> {

    @Override
    public List<Integer> sort(List<Integer> list) {
        if (list == null) {
            return list;
        }
        return toList(sort(toArray(list), false));
    }

    @Override
    protected void subSort(Integer[] array) {
        if (array == null) {
            return;
        }
        int d = getDigit(array);
        int n = 1;//代表位数对应的数：1,10,100...
        int k = 0;//保存每一位排序后的结果用于下一位的排序输入
        int[][] bucket = new int[10][array.length];//排序桶用于保存每次排序后的结果，这一位上排序结果相同的数字放在同一个桶里
        int[] order = new int[array.length];//用于保存每个桶里有多少个数字
        while (n < d) {
            for (int num : array) //将数组array里的每个数字放在相应的桶里
            {
                int digit = (num / n) % 10;
                bucket[digit][order[digit]] = num;
                order[digit]++;
            }
            for (int i = 0; i < array.length; i++)//将前一个循环生成的桶里的数据覆盖到原数组中用于保存这一位的排序结果
            {
                if (order[i] != 0)//这个桶里有数据，从上到下遍历这个桶并将数据保存到原数组中
                {
                    for (int j = 0; j < order[i]; j++) {
                        array[k] = bucket[i][j];
                        k++;
                    }
                }
                order[i] = 0;//将桶里计数器置0，用于下一次位排序
            }
            n *= 10;
            k = 0;//将k置0，用于下一轮保存位排序结果
        }
    }

    @Override
    protected Integer[] toArray(List<Integer> list) {
        return list.toArray(new Integer[0]);
    }

    private int getDigit(Integer[] array) {
        int d = 1;
        for (int i = 0; i < array.length; i++) {
            while (array[i] % Math.pow(10, d) != array[i]) {
                d++;
            }
        }
        return (int) Math.pow(10, d);
    }

}
