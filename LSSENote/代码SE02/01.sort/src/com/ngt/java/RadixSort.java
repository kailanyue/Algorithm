package com.ngt.java;

/**
 * @author ngt
 * @create 2020-08-16 23:17
 * 1. 基数排序非常适合对整数排序（尤其是非负整数）
 * 2. 依次对元素的个位，十位，百位...进行排序（从低位到高位排序）
 * 3. 又因为每位数据都是由0~9构成，因此其底层通过计数排序来实现，计数数组的大小是10
 */
public class RadixSort extends Sort<Integer> {
    @Override
    protected void sort() {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        for (int divider = 1; divider <= max; divider *= 10) {
            countingSort(divider);
        }

    }

    protected void countingSort(int divider) {
        int[] counts = new int[10];
        for (int i = 0; i < array.length; i++) {
            counts[array[i] / divider % 10]++;
        }

        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }

        int[] newArray = new int[array.length];

        for (int i = array.length - 1; i >= 0; i--) {
            newArray[--counts[array[i] / divider % 10]] = array[i];
        }

        for (int i = 0; i < newArray.length; i++) {
            array[i] = newArray[i];
        }
    }
}
