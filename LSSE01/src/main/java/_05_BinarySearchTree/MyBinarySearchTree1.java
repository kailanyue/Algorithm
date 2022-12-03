package _05_BinarySearchTree;

import treeprinter.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

/**
 * @author ngt on 2022-12-03 20:01
 * @version 1.0
 *
 * 1. 完成元素添加方法
 * 2. 使用 Comparator 接口完成自定义对象比较策略
 * 3. 使用递归方法完成前序、中序和后序遍历以及层序遍历
 * 4. 使用 Consumer 接口完成遍历中自定义打印策略
 */
public class MyBinarySearchTree1<E> implements BinaryTreeInfo {

	private int size;

	private Node<E> root;

	// 用于接收自定义比较策略
	private final Comparator<E> comparator;

	// 用于接收自定义 visit 策略
	private final Consumer<E> consumer;

	public MyBinarySearchTree1() {
		this(null, null);
	}

	public MyBinarySearchTree1(Comparator<E> comparator) {
		this(comparator, null);
	}

	public MyBinarySearchTree1(Consumer<E> consumer) {
		this(null, consumer);
	}

	public MyBinarySearchTree1(Comparator<E> comparator, Consumer<E> consumer) {
		this.comparator = comparator;
		this.consumer = consumer;
	}


	/**
	 * 对象的比较策略，如果未输入就使用对象默认比较方法
	 *
	 * @param e1 e1
	 * @param e2 e2
	 * @return 比较结果
	 */
	private int compare(E e1, E e2) {
		if (comparator != null) {
			return comparator.compare(e1, e2);
		}
		return ((Comparable<E>) e1).compareTo(e2);

	}

	/**
	 * 对象的 visit 策略，如果没有就使用 println 方法
	 *
	 * @param e e
	 */
	private void visit(Node<E> e) {
		if (consumer == null) {
			System.out.println(e.element);
		} else {
			consumer.accept(e.element);
		}

	}


	private static class Node<E> {
		E element;
		Node<E> left;

		Node<E> right;
		Node<E> parent;

		public Node(E element, Node<E> parent) {
			this.element = element;
			this.parent = parent;
		}

	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void clear() {
		root = null;
		size = 0;
	}

	// 添加元素
	public void add(E element) {
		elementNotNullCheck(element);

		// 添加根节点
		if (root == null) {
			root = new Node<>(element, null);
			size++;
		}
		// 添加非根节点
		Node<E> parent = root; // 记录要插入节点的父节点
		Node<E> node = root; // 保存遍历的位置
		int cmp = 0; // 记录比较的结果，用来确定添加到左边还是右边

		while (node != null) {
			cmp = compare(element, node.element);
			parent = node;
			// 向右边遍历
			if (cmp > 0) {
				node = node.right;
			} else if (cmp < 0) {
				node = node.left;
			} else {
				node.element = element;
				return;
			}
		}

		Node<E> newNode = new Node<>(element, parent);
		if (cmp > 0) {
			parent.right = newNode;
		} else {
			parent.left = newNode;
		}
		size++;
	}

	// 删除元素
	public void remove(E element) {

	}

	public boolean contains(E element) {
		return true;
	}

	/**
	 * check add element is not null
	 *
	 * @param element element
	 */
	private void elementNotNullCheck(E element) {
		if (element == null) {
			throw new IllegalArgumentException("element must not be null");
		}
	}


	// 遍历操作

	// 前序遍历（Preorder Traversal）
	public void preorderTraversal() {
		preorderTraversal(root);
	}

	private void preorderTraversal(Node<E> node) {
		if (node == null) return;

		visit(node);
		preorderTraversal(node.left);
		preorderTraversal(node.right);
	}

	// 中序遍历（Inorder Traversal）
	public void inorderTraversal() {
		inorderTraversal(root);
	}

	private void inorderTraversal(Node<E> node) {
		if (node == null) return;

		inorderTraversal(node.left);
		visit(node);
		inorderTraversal(node.right);
	}

	// 后序遍历（Postorder Traversal）
	public void postorderTraversal() {
		postorderTraversal(root);
	}

	private void postorderTraversal(Node<E> node) {
		if (node == null) return;

		postorderTraversal(node.left);
		postorderTraversal(node.right);
		visit(node);
	}

	// 层序遍历（Level Order Traversal）
	public void levelOrderTraversal() {
		levelOrderTraversal(root);
	}

	private void levelOrderTraversal(Node<E> node) {
		if (node == null) return;

		Queue<Node<E>> queue = new LinkedList<>();

		// 添加元素
		queue.offer(node);

		while (!queue.isEmpty()) {
			Node<E> next = queue.poll();
			visit(next);

			if (next.left != null) {
				queue.offer(next.left);
			}

			if (next.right != null) {
				queue.offer(next.right);
			}
		}
	}


	// 用于树的打印操作
	@Override
	public Object root() {
		return root;
	}

	@Override
	public Object left(Object node) {
		return ((Node<E>) node).left;
	}

	@Override
	public Object right(Object node) {
		return ((Node<E>) node).right;
	}

	@Override
	public Object string(Object node) {
		return ((Node<E>) node).element;
	}

}
