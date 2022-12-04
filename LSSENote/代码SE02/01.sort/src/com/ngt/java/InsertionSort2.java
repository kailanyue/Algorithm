package com.ngt.java;

/**
 * @author ngt
 * @create 2020-08-15 23:49
 * 将交换更改为挪动，需要提前保存begin的值，最后将值放入找到的位置中
 */
public class InsertionSort2<T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        int len = array.length;
        for (int begin = 1; begin < len; begin++) {
            int cur = begin;
            T v = array[cur];
            while (cur > 0 && cmp(v, array[cur - 1]) < 0) {
                array[cur] = array[cur - 1];
                cur--;
            }
            array[cur] = v;
        }
    }
}
