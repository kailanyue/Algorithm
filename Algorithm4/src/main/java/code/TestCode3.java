package code;

public class TestCode3 {


//    给定一个 n × n 的二维矩阵matrix 表示一个图像。请你将图像顺时针旋转 90 度。
//    你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。

    /*
    1 2 3
    4 5 6
    7 8 9

    7 4 1
    8 5 2
    9 6 3
    */

    public static void main(String[] args) {
        test();
    }


    private static int[][] matrix(int[][] ints) {
        int n = ints.length;

        // 对角线变换
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                int tmp = ints[i][j];
                ints[i][j] = ints[j][i];
                ints[j][i] = tmp;


            }
        }

        // 对称轴变换
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                if (!(n % 2 == 1 && j == n / 2)) {
                    int tmp = ints[i][j];
                    ints[i][j] = ints[i][n - j - 1];
                    ints[i][n - j - 1] = tmp;

                }
            }
        }
        return ints;
    }

    private static void prints(int[][] ints) {
        int n = ints.length;


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(ints[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void test() {

        // 3 * 3
//        int[][] ints = new int[3][3];
//        ints[0][0] = 1;
//        ints[0][1] = 2;
//        ints[0][2] = 3;
//
//        ints[1][0] = 4;
//        ints[1][1] = 5;
//        ints[1][2] = 6;
//
//
//        ints[2][0] = 7;
//        ints[2][1] = 8;
//        ints[2][2] = 9;
//        prints(matrix(ints));


        // 2 * 2
//        int[][] ints1 = new int[2][2];
//        ints1[0][0] = 1;
//        ints1[0][1] = 2;
//
//
//        ints1[1][0] = 3;
//        ints1[1][1] = 4;
//
//        prints(matrix(ints1));


    }
}
