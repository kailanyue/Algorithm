package com.ngt.java;

import com.ngt.java.tools.Integers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ngt
 * @create 2020-08-16 21:14
 * 1. 希尔排序的效率取决于步长序列的选取，因此希尔排序没有确定的时间复杂度
 * 2. 随着希尔排序的进行，数组中逆序对是再不断减少的，因此希尔排序的底层一把使用插入排序来实现
 * 3. 由此希尔排序也可以算作插入排序的改进
 */
public class ShellSort<T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        List<Integer> stepSequence = sedgewickStepSequence();
        for (Integer step : stepSequence) {
            sort(step);
        }
    }

    private void sort(int step) {
        for (int col = 0; col < step; col++) {
            for (int begin = col + step; begin < array.length; begin += step) {
                int cur = begin;
                T v = array[cur];
                while (cur > col && cmp(v, array[cur - step]) < 0) {
                    array[cur] = array[cur - step];
                    cur -= step;
                }
                array[cur] = v;
            }
        }
    }

    private List<Integer> shellStepSequence() {
        List<Integer> stepSequence = new ArrayList<>();
        int step = array.length;
        while ((step >>= 1) > 0) {
            stepSequence.add(step);
        }
        return stepSequence;
    }

    private List<Integer> sedgewickStepSequence() {
        List<Integer> stepSequence = new LinkedList<>();
        int k = 0, step = 0;
        while (true) {
            if (k % 2 == 0) {
                int pow = (int) Math.pow(2, k >> 1);
                step = 1 + 9 * (pow * pow - pow);
            } else {
                int pow1 = (int) Math.pow(2, (k - 1) >> 1);
                int pow2 = (int) Math.pow(2, (k + 1) >> 1);
                step = 1 + 8 * pow1 * pow2 - 6 * pow2;
            }
            if (step >= array.length) break;
            stepSequence.add(0, step);
            k++;
        }
        return stepSequence;
    }
}
