package net.chris.lib.algorithms.sort.kotlin

/**
 * Radix sort.
 */
abstract class KTRadixSorter : KTSorter() {

    override fun subSort(array: IntArray) {
        if (array == null) {
            return
        }
        val d = getDigit(array)
        var n = 1//代表位数对应的数：1,10,100...
        var k = 0//保存每一位排序后的结果用于下一位的排序输入
        val bucket = Array(10) { IntArray(array.size) }//排序桶用于保存每次排序后的结果，这一位上排序结果相同的数字放在同一个桶里
        val order = IntArray(array.size)//用于保存每个桶里有多少个数字
        while (n < d) {
            for (num in array)
            //将数组array里的每个数字放在相应的桶里
            {
                val digit = num / n % 10
                bucket[digit][order[digit]] = num
                order[digit]++
            }
            for (i in array.indices)
            //将前一个循环生成的桶里的数据覆盖到原数组中用于保存这一位的排序结果
            {
                if (order[i] != 0)
                //这个桶里有数据，从上到下遍历这个桶并将数据保存到原数组中
                {
                    for (j in 0..order[i] - 1) {
                        array[k] = bucket[i][j]
                        k++
                    }
                }
                order[i] = 0//将桶里计数器置0，用于下一次位排序
            }
            n *= 10
            k = 0//将k置0，用于下一轮保存位排序结果
        }
    }

    private fun getDigit(array: IntArray): Int {
        var d = 1
        for (i in array.indices) {
            while (array[i] % Math.pow(10.0, d.toDouble()) != array[i].toDouble()) {
                d++
            }
        }
        return Math.pow(10.0, d.toDouble()).toInt()
    }

}
