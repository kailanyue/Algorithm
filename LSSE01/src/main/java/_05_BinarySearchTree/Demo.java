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
		personTreeTest();
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
		MyBinarySearchTree<Person> bst2 = new MyBinarySearchTree<>((p1, p2) -> p2.getAge() - p1.getAge());
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

	private static void traversalTest1() {
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

	/**
	 * 四种遍历策略，以及自定义输出策略
	 */

	private static void traversalTest2() {
		Integer[] data = new Integer[]{
				7, 4, 9, 2, 5, 8, 11, 3, 12, 1
		};
		MyBinarySearchTree2<Integer> bst1 = new MyBinarySearchTree2<>(a -> System.out.print(a * 10 + " "));
		for (Integer datum : data) {
			bst1.add(datum);
		}
		BinaryTrees.println(bst1);

		// 前序遍历
		System.out.println("\n------------- PreorderTraversal -------------");
		bst1.preorder(new MyBinarySearchTree2.Visitor<Integer>() {
			@Override
			public boolean visit(Integer element) {
				System.out.print(element + " ");
				return element == 2;
			}
		});

		// 中序遍历
		System.out.println("\n------------- InorderTraversal -------------");
		bst1.inorder(new MyBinarySearchTree2.Visitor<Integer>() {
			@Override
			public boolean visit(Integer element) {
				System.out.print(element + " ");
				return element == 2;
			}
		});

		// 后序遍历
		System.out.println("\n------------- PostorderTraversal -------------");
		bst1.postorder(new MyBinarySearchTree2.Visitor<Integer>() {
			@Override
			public boolean visit(Integer element) {
				System.out.print(element + " ");
				return element == 4;
			}
		});

		// 层序遍历
		System.out.println("\n------------- LevelOrderTraversal -------------");
		bst1.levelOrder(new MyBinarySearchTree2.Visitor<Integer>() {
			@Override
			public boolean visit(Integer element) {
				System.out.print(element + " ");
				return element == 2;
			}
		});
	}


	/**
	 * height 方法测试
	 */
	private static void testTreeHeight() {
		MyBinarySearchTree2<Integer> bst = new MyBinarySearchTree2<>();
		for (int i = 0; i < 30; i++) {
			bst.add((int) (Math.random() * 100));
		}

		BinaryTrees.println(bst);

		System.out.println("递归算法获取的树的高度：" + bst.height1());
		System.out.println("迭代算法获取的树的高度：" + bst.height2());

	}


	/**
	 * isComplete 方法测试
	 */
	private static void testTreeComplete() {
		Integer[] data = new Integer[]{
				7, 4, 9, 2, 5
		};

		MyBinarySearchTree2<Integer> bst = new MyBinarySearchTree2<>();
		for (Integer datum : data) {
			bst.add(datum);
		}

		BinaryTrees.println(bst);
		System.out.println(bst.isComplete());
	}

	/**
	 * remove 方法的测试
	 */
	private static void testRemove() {
		Integer[] data = new Integer[] {
				7, 4, 9, 2, 5, 8, 11, 3, 12, 1
		};

		MyBinarySearchTree3<Integer> bst = new MyBinarySearchTree3<>();
		for (Integer datum : data) {
			bst.add(datum);
		}

		BinaryTrees.println(bst);

		String s = "";

		bst.remove(7);
		BinaryTrees.println(bst);
	}

	private static void testRemove1() {
		Integer[] data = new Integer[] {
				7, 4, 9, 2, 5, 8, 11, 3, 12, 1
		};

		MyBinarySearchTree<Integer> bst = new MyBinarySearchTree<>();
		for (Integer datum : data) {
			bst.add(datum);
		}

		BinaryTrees.println(bst);

		String s = "";

		bst.remove(7);
		BinaryTrees.println(bst);
	}
}
