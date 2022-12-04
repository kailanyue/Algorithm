package com.ngt.java;

/**
 * @author ngt
 * @create 2020-08-16 23:02
 * 1. 计数排序适用于对一定范围内的整数进行排序
 * 2. 计数排序的核心思想是统计每个整数再序列中出现的次数，进而推导出每个整数在有序序列中的索引
 */
public class CountingSort extends Sort<Integer> {
    @Override
    protected void sort() {
        int max = array[0];
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) max = array[i];
            if (array[i] < min) min = array[i];
        }

        int[] counts = new int[max - min + 1];

        for (int i = 0; i < array.length; i++) {
            counts[array[i] - min]++;
        }

        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }

        int[] newArray = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            newArray[--counts[array[i] - min]] = array[i];
        }

        for (int i = 0; i < newArray.length; i++) {
            array[i] = newArray[i];
        }
    }
}
