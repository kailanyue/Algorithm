package chapter01.section01;


import org.junit.jupiter.api.Test;

public class Exercise {


	@Test
	public void test11() {
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


	@Test
	public void test13() {
		int[][] mat = {
				{1, 2, 3},
				{4, 5, 6}
		};

		transpose(mat);
	}

	/**
	 * Exercise13 二维数组的转置
	 *
	 * @param mat
	 */
	private static void transpose(int[][] mat) {
		int[][] newMat = new int[mat[0].length][mat.length];
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				// 此处必须是 mat[i][j] 因为需要和mat的形状相对应
				newMat[j][i] = mat[i][j];
			}
		}
		print(newMat);
	}

	/**
	 * 格式化打印二维矩阵
	 *
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

	/**
	 * Exercise14 静态方法 logn
	 *
	 * @param n
	 * @return
	 */
	private static int lg(int n) {
		int logInt = 0;
		while (n > 0) {
			logInt++;
			n /= 2;
		}
		return logInt - 1;
	}

	@Test
	public void test19() {
		for (int n = 0; n < 90; n++) {
			long[] arr;

			if (n == 0 || n == 1) {
				arr = new long[2];
			} else {
				arr = new long[n + 1];
			}

			arr[0] = 0;
			arr[1] = 1;

			System.out.println(n + " - " + enhancedF(n, arr));
		}
	}

	/**
	 * Exercise19 斐波那契数列
	 *
	 * @param n
	 * @return
	 */
	private static int F(int n) {
		if (n == 0) return 0;
		if (n == 1) return 1;
		return F(n - 1) + F(n - 2);
	}

	private static long enhancedF(int n, long[] arr) {
		if (n == 0) return arr[0];
		if (n == 1) return arr[1];
		for (int i = 2; i <= n; i++) {
			arr[i] = arr[i - 1] + arr[i - 2];
		}
		return arr[n];
	}


	/**
	 * 递归实现 ln(N!) 计算方法
	 *
	 * @param n
	 * @return
	 */
	private static double lnFactorial(int n) {
		if (n == 1) return 0.0;
		return Math.log(n) + lnFactorial(n - 1);
	}
}
