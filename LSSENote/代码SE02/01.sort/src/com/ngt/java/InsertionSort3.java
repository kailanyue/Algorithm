package com.ngt.java;

/**
 * @author ngt
 * @create 2020-08-15 23:49
 * 通过二分策略直接找到要插入的位置，然后依次挪动元素并将保存的值插入对应的元素
 */
public class InsertionSort3<T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        int len = array.length;
        for (int begin = 1; begin < len; begin++) {
            T v = array[begin];
            int insertIndex = search(begin);
            for (int i = begin; i > insertIndex; i--) {
                array[i] = array[i - 1];
            }
            array[insertIndex] = v;
        }
    }

    private int search(int index) {
        int begin = 0;
        int end = index;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (cmp(array[index], array[mid]) < 0) {
                end = mid;
            } else {
                begin = mid + 1;
            }
        }
        return begin;
    }
}
