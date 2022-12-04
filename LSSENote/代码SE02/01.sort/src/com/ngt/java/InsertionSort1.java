package com.ngt.java;

/**
 * @author ngt
 * @create 2020-08-15 23:49
 */
public class InsertionSort1<T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        int len = array.length;
        for (int begin = 1; begin < len; begin++) {
            int cur = begin;
            while (cur > 0 && cmp(array[cur], array[cur - 1]) < 0) {
                swap(cur, cur - 1);
                cur--;
            }
        }
    }
}
