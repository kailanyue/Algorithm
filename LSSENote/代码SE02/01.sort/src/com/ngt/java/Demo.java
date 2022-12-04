package com.ngt.java;

import com.ngt.java.tools.Asserts;
import com.ngt.java.tools.Integers;

import java.util.Arrays;

/**
 * @author ngt
 * @create 2020-08-14 21:53
 */
public class Demo {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        Integer[] array = Integers.random(50000, 1, 50000);
        testSorts(array,
//                new BubbleSort1(),
//                new BubbleSort2(),
//                new BubbleSort3(),
                new HeapSort(),
//                new SelectionSort(),
//                new InsertionSort1(),
//                new InsertionSort2(),
//                new InsertionSort3(),
                new MergeSort(),
                new QuickSort(),
                new ShellSort(),
                new CountingSort(),
                new RadixSort());
    }

    public static void test2(){

    }

    public static void testSorts(Integer[] array, Sort... sorts) {
        for (Sort sort : sorts) {
            Integer[] newArray = Integers.copy(array);
            sort.sort(newArray);
            Asserts.test(Integers.isAscOrder(newArray));
        }
        Arrays.sort(sorts);  // 按照运行时间升序排列

        for (Sort sort : sorts) {
            System.out.println(sort);
        }
    }

}
