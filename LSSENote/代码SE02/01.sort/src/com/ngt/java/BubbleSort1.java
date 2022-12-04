package com.ngt.java;

/**
 * @author ngt
 * @create 2020-08-14 21:54
 */
public class BubbleSort1<T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        int len = array.length;
        for (int end = len - 1; end > 0; end--) {
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(array[begin], array[begin - 1]) < 0) {
                    swap(begin, begin - 1);
                }
            }
        }
    }
}
