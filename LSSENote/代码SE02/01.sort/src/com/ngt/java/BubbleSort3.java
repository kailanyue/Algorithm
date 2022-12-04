package com.ngt.java;

/**
 * @author ngt
 * @create 2020-08-14 21:54
 * 当数组的后半部分是有序的时候，下一次则不需要进行遍历，因此使用sortedIndex记录有序部分
 * 的起始位置，下次循环只需要执行到此处
 */
public class BubbleSort3<T extends Comparable<T>> extends Sort<T>{
    @Override
    protected void sort() {
        int len = array.length;
        for (int end = len - 1; end > 0; end--) {
            int sortedIndex = 1;
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(array[begin], array[begin - 1]) < 0) {
                    swap(begin, begin - 1);
                    sortedIndex = begin;
                }
            }
            end = sortedIndex;
        }
    }
}
