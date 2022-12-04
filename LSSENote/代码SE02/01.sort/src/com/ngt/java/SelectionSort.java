package com.ngt.java;

/**
 * @author ngt
 * @create 2020-08-15 0:15
 * 因为选择排序是一趟比较完之后再进行一次交换，而冒泡排序最坏的情况下相邻位置都需要进行进行比较，
 * 所以选择排序的效率肯定是高于冒泡排序的
 */
public class SelectionSort<T extends Comparable<T>> extends Sort<T>{
    @Override
    protected void sort() {
        int len = array.length;
        for (int end = len - 1; end > 0; end--) {
            int maxIndex = 0;
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(array[maxIndex], array[begin]) < 0) {
                    maxIndex = begin;
                }
            }
            swap(maxIndex, end);
        }
    }
}
