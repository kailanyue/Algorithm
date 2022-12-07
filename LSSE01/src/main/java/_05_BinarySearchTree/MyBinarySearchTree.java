package _05_BinarySearchTree;


import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @author ngt on 2022-12-03 20:01
 * @version 1.0
 * 该类继承 MyBinaryTree 并放置二叉搜索树的专有方法
 */
public class MyBinarySearchTree<E> extends MyBinaryTree<E> {



	// 用于接收自定义比较策略
	private final Comparator<E> comparator;

	// 用于接收自定义 visit 策略

	// private final Consumer<E> consumer;


	public MyBinarySearchTree() {
		this(null,null);
	}

	public MyBinarySearchTree(Comparator<E> comparator) {
		this(comparator, null);
	}

	public MyBinarySearchTree(Consumer<E> consumer) {
		this(null, consumer);
	}

	public MyBinarySearchTree(Comparator<E> comparator, Consumer<E> consumer) {
		super(consumer);
		this.comparator = comparator;
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


	public boolean contains(E element) {
		return getNode(element) != null;
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
		remove(getNode(element));
	}


	private void remove(Node<E> node) {

		if (node == null) return;
		// 如果删除的节点度为 2，那么首先使用它的后继节点的数值替换它的值
		// 然后删除它的后继节点，此时问题就退化成为了 删除度为 0 或者 1 的节点的情况
		size--;
		if (node.hasTwoChildren()) {
			Node<E> s = successor(node);

			// 用后继节点的值覆盖度为2的节点的值
			node.element = s.element;

			// 删除后继节点，退化成另外两种情况
			node = s;
		}

		// 删除node节点（node的度必然是1或者0）
		Node<E> replacement = node.left != null ? node.left : node.right;

		if (replacement != null) { // node 为度为1的节点
			// 更改parent
			replacement.parent = node.parent;

			if (node.parent == null) { // node是度为1的节点并且是根节点
				root = replacement;
			} else if (node == node.parent.left) {
				node.parent.left = replacement;
			} else {
				node.parent.right = replacement;
			}
		} else if (node.parent == null) { // node是叶子节点并且是根节点
			root = null;
		} else { // node是叶子节点，但不是根节点
			if (node == node.parent.left) {
				node.parent.left = null;
			} else {
				node.parent.right = null;
			}
		}
	}


	/**
	 * 根据数值查找对应的节点
	 *
	 * @param element 输入的数值
	 * @return 该数值对应的节点，如果没有对应的节点就返回 null
	 */
	private Node<E> getNode(E element) {
		Node<E> node = root;
		while (node != null) {
			int compare = compare(element, node.element);

			// 数值相等就返回当前的节点
			if (compare == 0) return node;

			if (compare > 0) {
				node = node.right;
			} else {
				node = node.left;
			}
		}

		// 遍历完成还没有  return，就是没有找到该数值对应的节点
		return null;
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









}
