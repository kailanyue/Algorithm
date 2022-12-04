package com.ngt.java;

/**
 * @author ngt
 * @create 2020-08-14 21:54
 * 使用变量 标识 sorted 当前的数组是否有序，因为有序不会触发if条件，sorted==true；
 * 此时可以直接中断循环
 */
public class BubbleSort2<T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        int len = array.length;
        for (int end = len - 1; end > 0; end--) {
            boolean sorted = true;
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(array[begin], array[begin - 1]) < 0) {
                    swap(begin, begin - 1);
                    sorted = false;
                }
            }
            if (sorted) break;
        }
    }
}
