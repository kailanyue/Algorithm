package _06_AVLTree;

import org.junit.jupiter.api.Test;
import treeprinter.BinaryTrees;

/**
 * @author ngt on 2022-12-12 23:04
 * @version 1.0
 */
public class TestDemo {
	public static void main(String[] args) {


	}


	@Test
	public void rebalancedTest() {
		Integer[] data = new Integer[]{
				70, 83, 1, 52, 80, 40, 6, 78, 97, 38, 64, 45, 50, 15, 58, 87, 18
		};

		MyAVLTree<Integer> avl = new MyAVLTree<>();
		for (Integer datum : data) {
			// System.out.println(datum);
			avl.add(datum);
		}

		BinaryTrees.println(avl);
	}
}
