package com.ngt.java;

/**
 * @author ngt
 * @create 2020-08-16 18:48
 *  1. 当轴点的位置过于靠近数组的左右两端时候，快速排序将出现最坏的情况，
 *     因此每次堆第一个元素和任意一个随机元素进行交换将大大减小，最坏情况出现的可能
 *
 *  2. 由于与端点进行比较的时候，需要左右来回比较，可以通过两层while循环来进行是实现
 */
public class QuickSort<T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        sort(0, array.length);
    }

    /**
     * 对 [begin, end) 范围的元素进行快速排序
     *
     * @param begin
     * @param end
     */
    private void sort(int begin, int end) {
        if (end - begin < 2) return;
        int mid = pivotIndex(begin, end);
        sort(begin, mid);
        sort(mid + 1, end);
    }

    /**
     * 构造出 [begin, end) 范围的轴点元素
     *
     * @return 轴点元素的最终位置
     */
    private int pivotIndex(int begin, int end) {
//        swap(begin, (int) (Math.random() * (end - begin)) + begin);
        T pivot = array[begin];
        end--;
        while (begin < end) {
            while (begin < end) {
                if (cmp(pivot, array[end]) < 0) {
                    end--;
                } else {
                    array[begin++] = array[end];
                    break;
                }
            }

            while (begin < end) {
                if (cmp(pivot, array[begin]) > 0) {
                    begin++;
                } else {
                    array[end--] = array[begin];
                    break;
                }
            }
        }
        array[begin] = pivot;
        return begin;
    }
}
