package com.ngt.backtracking;

/**
 * @author ngt
 * @create 2020-08-22 23:10
 */
public class Queens3 {
    public static void main(String[] args) {
        int n = 8;
        int[] array = new int[n];
        placeQueens(n);
    }

    public static void queens(int n) {

        placeQueens(n);
    }

    static int ways;

    private static void placeQueens(int n) {
        if (n < 1) return;
        int[] array = new int[n];
        boolean[] cols = new boolean[n];
        boolean[] leftTop = new boolean[(n << 1) - 1];
        boolean[] rightTop = new boolean[leftTop.length];
        place(0, array, cols, leftTop, rightTop);
        System.out.println(n + "皇后一共有" + ways + "种摆法");
    }


    private static void place(int row, int[] array, boolean[] cols,
                              boolean[] leftTop, boolean[] rightTop) {
        if (row == array.length) {
            ways++;
            //show(array);
            return;
        }
        for (int col = 0; col < array.length; col++) {
            if (cols[col]) continue;
            int ltIndex = row - col + cols.length - 1;
            if (leftTop[ltIndex]) continue;
            int rtIndex = row + col;
            if (rightTop[rtIndex]) continue;

            array[row] = col;
            cols[col] = true;
            leftTop[ltIndex] = true;
            rightTop[rtIndex] = true;
            place(row + 1, array, cols, leftTop, rightTop);
            cols[col] = false;
            leftTop[ltIndex] = false;
            rightTop[rtIndex] = false;

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
