package com.ngt.java;

/**
 * @author ngt
 * @create 2020-08-15 0:26
 * 堆排序实质仍然是选择排序，首先将数组原地建立大顶堆，然后交换堆顶元素和堆尾元素，
 * 然后对堆顶的元素下滤建立一个规模减一的堆，每次将堆
 * 使用元素 heapSize 来标识堆的大小，也就是待排序的元素的数量。
 */
public class HeapSort<T extends Comparable<T>> extends Sort<T> {
    private int heapSize;

    @Override
    protected void sort() {
        heapSize = array.length;
        for(int i = (heapSize>>1) - 1; i >=0 ;i--){
            siftDown(i);
        }
        while (heapSize>1){
            swap(0, --heapSize);
            siftDown(0);
        }
    }

    private void siftDown(int index) {
        T element = array[index];
        int haft = heapSize >> 1;
        while (index < haft) {
            int childIndex = (index << 1) + 1;
            T child = array[childIndex];

            int rightIndex = childIndex + 1;
            if(rightIndex < heapSize && cmp(array[rightIndex], child) >0){
                child = array[childIndex = rightIndex];
            }

            if(cmp(element, child) >= 0) break;
            array[index] = child;
            index = childIndex;
        }
        array[index] = element;
    }
}
