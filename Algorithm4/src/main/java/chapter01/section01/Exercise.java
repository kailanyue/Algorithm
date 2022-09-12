package chapter01.section01;


import org.junit.jupiter.api.Test;

public class Exercise {


    @Test
    public void test1() {
        boolean[][] array = {{true, false, true},
                {false, true, false}};
        printArray(array);
    }

    /**
     * Exercise11 打印二维boolean数组
     *
     * @param array
     */
    private static void printArray(boolean[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j]) {
                    System.out.println("*");
                } else {
                    System.out.println(" ");
                }
            }
            System.out.println();
        }
    }


    /**
     * 二维数组的转置
     *
     * @param mat
     */
    private static void transpose(int[][] mat) {
        int[][] newMat = new int[mat[0].length][mat.length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                newMat[j][i] = mat[i][j];
            }
        }
        print(newMat);
    }

    /**
     * @param mat
     */
    private static void print(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }
}
