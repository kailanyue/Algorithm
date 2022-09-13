package chapter01.section01;

/**
 * @author ngt on 2022-09-13 10:48
 * @version 1.0
 */
public class Exercise33_Matrix_Operations {
	public static void main(String[] args) {

	}

	//TODO Vector dot product

	/**
	 * 计算两个向量的乘积
	 *
	 * @param x
	 * @param y
	 * @return
	 */
	private static double dot(double[] x, double[] y) {
		if (x == null || y == null || x.length != y.length) {
			throw new IllegalArgumentException();
		}

		double result = 0;
		for (int i = 0; i < x.length; i++) {
			result += x[i] * y[i];
		}
		return result;
	}

	//TODO Matrix-matrix product

	/**
	 * 计算两个矩阵乘积
	 *
	 * @param a
	 * @param b
	 * @return
	 */
	private static double[][] mult(double[][] a, double[][] b) {
		if (a == null || b == null || a.length == 0 || b.length == 0 || a[0].length != b.length) {
			throw new IllegalArgumentException();
		}

		double[][] c = new double[a.length][b[0].length];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b[0].length; j++) {
				for (int k = 0; k < a[0].length; k++) {
					c[i][j] += a[i][k] + b[k][j];
				}
			}
		}
		return c;
	}

	//TODO Transpose

	/**
	 * 矩阵转置
	 *
	 * @param a
	 * @return
	 */
	private static double[][] transpose(double[][] a) {
		if (a == null) {
			throw new IllegalArgumentException();
		}
		if (a.length == 0) {
			return a;
		}
		double[][] b = new double[a[0].length][a.length];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				b[j][i] = a[i][j];
			}
		}
		return b;
	}

	//TODO Vector-matrix product
	private static double[] mult(double[] y, double[][] a) {
		if(a==null || y == null || a.length != y.length){
			throw new IllegalArgumentException();
		}
		double[] b = new double[a[0].length];

		for (int i = 0; i < a[0].length; i++) {
			for (int j = 0; j < y.length; j++) {
				b[i] += a[j][i] * y[j];
			}
		}

		return b;
	}
}
