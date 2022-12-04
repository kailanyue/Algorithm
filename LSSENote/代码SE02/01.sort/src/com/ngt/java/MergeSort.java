package com.ngt.java;

/**
 * @author ngt
 * @create 2020-08-16 14:57
 */
public class MergeSort<T extends Comparable<T>> extends Sort<T> {

    private T[] leftArray;

    @Override
    protected void sort() {
        leftArray = (T[]) new Comparable[array.length >> 1];
        sort(0, array.length);
    }

    /**
     * 对 [begin, end) 范围的数据进行归并排序
     */
    private void sort(int begin, int end) {
        if (end - begin < 2) return;
        int mid = (begin + end) >> 1;
        sort(begin, mid);
        sort(mid, end);
        merge(begin, mid, end);
    }

    /**
     * 将 [begin, mid) 和 [mid, end) 范围的序列合并成一个有序序列
     */
    private void merge(int begin, int mid, int end) {
        int li = 0, le = mid - begin;// 因为li是指向备份的数组所以索引从0开始
        int ri = mid, re = end;
        int ai = begin; // 待替换的位置， 因为是原始数组所以从end开始

        for (int i = 0; i < le; i++) {
            leftArray[i] = array[begin + i];
        }
        while (li < le) { // 备份的数组没有扫描完
            if (ri < re && cmp(array[ri], leftArray[li]) < 0) {// <= 将失去稳定性
                array[ai++] = array[ri++];
            } else {
                array[ai++] = leftArray[li++];
            }
        }
    }
}
