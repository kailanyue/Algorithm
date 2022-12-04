package com.ngt.backtracking;

/**
 * @author ngt
 * @create 2020-08-22 16:57
 */
public class Queens1 {
    static int count = 0;

    public static void main(String[] args) {
        int queenSize = 8;
        int[] array = new int[queenSize];
        check(array, queenSize, 0);
        System.out.println(count);
    }

    public static void queens(int n) {
        int[] array = new int[n];
        check(array, n, 0);
        System.out.println(n + "皇后一共有" + count + "种摆法");
    }

    /**
     * @param array
     * @param queenSize
     * @param n
     */
    private static void check(int[] array, int queenSize, int n) {
        if (n == queenSize) {
            //show(array);
            count++;
            return;
        }
        for (int i = 0; i < queenSize; i++) {
            array[n] = i;
            if (judge(array, n)) {
                check(array, queenSize, n + 1);
            }
        }
    }

    /**
     * 判断当前的皇后与之前的皇后是否满足不在同一行，同一列，同一个对角线上
     *
     * @param array
     * @param n     第n哥皇后
     * @return
     */
    private static boolean judge(int[] array, int n) {
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    private static void print(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    private static void show(int[] array) {
        for (int row = 0; row < array.length; row++) {
            for (int col = 0; col < array.length; col++) {
                if (array[row] == col) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
        System.out.println("------------------------------");
    }
}
