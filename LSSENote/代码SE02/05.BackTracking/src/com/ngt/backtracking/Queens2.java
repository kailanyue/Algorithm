package com.ngt.backtracking;

/**
 * @author ngt
 * @create 2020-08-22 23:10
 */
public class Queens2 {
    public static void main(String[] args) {
        int n = 8;
        int[] array = new int[n];
        placeQueens(n);
    }

    public static void queens(int n) {
        placeQueens(n);
    }

    private static void placeQueens(int n) {
        if (n < 1) return;
        int[] array = new int[n];
        place(0, array);
        System.out.println(n + "皇后一共有" + ways + "种摆法");
    }

    static int ways;

    private static void place(int row, int[] array) {
        if (row == array.length) {
            ways++;
            //show(array);
            return;
        }
        for (int col = 0; col < array.length; col++) {
            if (isValid(row, col, array)) {
                array[row] = col;
                place(row + 1, array);
            }
        }
    }

    private static boolean isValid(int row, int col, int[] array) {
        for (int i = 0; i < row; i++) {
            if (array[i] == col || row - i == Math.abs(col - array[i])) {
                return false;
            }
        }
        return true;
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
