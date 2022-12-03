package _05_BinarySearchTree;

import bean.Person;
import treeprinter.BinaryTrees;
import treeprinter.MyFiles;

import java.util.Comparator;

/**
 * @author ngt on 2022-12-03 20:23
 * @version 1.0
 */
public class Demo {
	public static void main(String[] args) {
		traversalTest();
	}

	/**
	 * 测试元素为 Integer 的二叉搜索树
	 */
	private static void intTreeTest() {
		Integer[] data = new Integer[]{
				7, 4, 9, 2, 5, 8, 11, 3, 12, 1
		};

		// 按照数值的顺序就行操作
		MyBinarySearchTree1<Integer> bst = new MyBinarySearchTree1<>();
		for (Integer datum : data) {
			bst.add(datum);
		}

		BinaryTrees.println(bst);
	}

	/**
	 * 测试元素为 Person 的二叉搜索树
	 */

	private static void personTreeTest() {
		Integer[] data = new Integer[]{
				7, 4, 9, 2, 5, 8, 11, 3, 12, 1
		};

		// 按照年龄进行升序操作
		MyBinarySearchTree1<Person> bst1 = new MyBinarySearchTree1<>(Comparator.comparingInt(Person::getAge));
		for (Integer datum : data) {
			bst1.add(new Person(datum));
		}
		BinaryTrees.println(bst1);


		System.out.println("-----------------------------------------------------------------------------");


		// 按照年龄进行降序操作
		MyBinarySearchTree1<Person> bst2 = new MyBinarySearchTree1<>((p1, p2) -> p2.getAge() - p1.getAge());
		for (Integer datum : data) {
			bst2.add(new Person(datum));
		}
		BinaryTrees.println(bst2);

		// 将结果保存到文件中
		MyFiles.writeToFile("data/1.txt", BinaryTrees.printString(bst1));
	}

	/**
	 * 四种遍历策略，以及自定义输出策略
	 */
	private static void traversalTest() {
		Integer[] data = new Integer[]{
				7, 4, 9, 2, 5, 8, 11, 3, 12, 1
		};
		MyBinarySearchTree1<Integer> bst1 = new MyBinarySearchTree1<>(a -> System.out.print(a * 10 + " "));
		for (Integer datum : data) {
			bst1.add(datum);
		}
		BinaryTrees.println(bst1);

		// 前序遍历
		System.out.println("\n------------- PreorderTraversal -------------");
		bst1.preorderTraversal();

		// 中序遍历
		System.out.println("\n------------- InorderTraversal -------------");
		bst1.inorderTraversal();

		// 后序遍历
		System.out.println("\n------------- PostorderTraversal -------------");
		bst1.postorderTraversal();

		// 层序遍历
		System.out.println("\n------------- LevelOrderTraversal -------------");
		bst1.levelOrderTraversal();
	}
}
