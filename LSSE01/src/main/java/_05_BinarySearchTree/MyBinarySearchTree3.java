package _05_BinarySearchTree;

import treeprinter.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

/**
 * @author ngt on 2022-12-03 20:01
 * @version 1.0
 * <p>
 * 1. 完成节点删除
 * 2. 完成 clear、contains 方法
 */
public class MyBinarySearchTree3<E> implements BinaryTreeInfo {

	private int size;

	private Node<E> root;

	// 用于接收自定义比较策略
	private final Comparator<E> comparator;

	// 用于接收自定义 visit 策略
	private final Consumer<E> consumer;

	private static class Node<E> {
		E element;
		Node<E> left;

		Node<E> right;
		Node<E> parent;

		public Node(E element, Node<E> parent) {
			this.element = element;
			this.parent = parent;
		}

		public boolean isLeaf() {
			return left == null && right == null;
		}

		public boolean hasTwoChildren() {
			return left != null && right != null;
		}
	}


	public MyBinarySearchTree3() {
		this(null, null);
	}

	public MyBinarySearchTree3(Comparator<E> comparator) {
		this(comparator, null);
	}

	public MyBinarySearchTree3(Consumer<E> consumer) {
		this(null, consumer);
	}

	public MyBinarySearchTree3(Comparator<E> comparator, Consumer<E> consumer) {
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

	/**
	 * 范围操作抽象类
	 *
	 * @param <E>
	 */
	public static abstract class Visitor<E> {
		boolean stop;

		public abstract boolean visit(E element);
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


	// 遍历操作

	// 前序遍历（Preorder Traversal）
	public void preorder(Visitor<E> visitor) {
		if (visitor == null) return;
		preorder(root, visitor);
	}

	private void preorder(Node<E> node, Visitor<E> visitor) {
		// 当达到停止条件之后停止递归操作
		if (node == null || visitor.stop) return;

		visitor.stop = visitor.visit(node.element);
		preorder(node.left, visitor);
		preorder(node.right, visitor);
	}

	// 中序遍历（Inorder Traversal）
	public void inorder(Visitor<E> visitor) {
		if (visitor == null) return;
		inorder(root, visitor);
	}

	private void inorder(Node<E> node, Visitor<E> visitor) {
		if (node == null || visitor == null) return;

		inorder(node.left, visitor);
		// 当达到停止条件之后停止递归操作
		if (visitor.stop) return;
		visitor.stop = visitor.visit(node.element);
		inorder(node.right, visitor);
	}

	// 后序遍历（Postorder Traversal）
	public void postorder(Visitor<E> visitor) {
		if (visitor == null) return;
		postorder(root, visitor);
	}

	private void postorder(Node<E> node, Visitor<E> visitor) {
		if (node == null || visitor.stop) return;

		postorder(node.left, visitor);
		postorder(node.right, visitor);
		if (visitor.stop) return;
		visitor.stop = visitor.visit(node.element);
	}

	// 层序遍历（Level Order Traversal）
	public void levelOrder(Visitor<E> visitor) {
		if (visitor == null) return;
		levelOrder(root, visitor);
	}

	private void levelOrder(Node<E> root, Visitor<E> visitor) {
		if (root == null || visitor == null) return;

		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);

		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			if (visitor.visit(node.element)) return;

			if (node.left != null) {
				queue.offer(node.left);
			}

			if (node.right != null) {
				queue.offer(node.right);
			}
		}
	}


	/**
	 * 获取树的高度，对外暴露
	 *
	 * @return
	 */
	public int height1() {
		return height1(root);
	}


	/**
	 * 使用递归的方法获取树的高度
	 *
	 * @param root 根节点
	 * @return 树的高度
	 */
	private int height1(Node<E> root) {
		if (root == null) return 0;
		return 1 + Math.max(height1(root.left), height1(root.right));
	}

	/**
	 * 使用层序算法的思路获取二叉树的高度
	 *
	 * @return 二叉树的高度
	 */
	public int height2() {
		return height2(root);
	}

	/**
	 * 使用层序算法的思路获取二叉树的高度
	 *
	 * @param root 根节点
	 * @return 树的高度
	 */
	public int height2(Node<E> root) {
		if (root == null) return 0;
		// 树的高度
		int height = 0;
		// 存储着每一层的元素数量
		int levelSize = 1;
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			levelSize--;
			if (node.left != null) {
				queue.offer(node.left);
			}

			if (node.right != null) {
				queue.offer(node.right);
			}

			if (levelSize == 0) {
				// 访问到下一层
				levelSize = queue.size();
				height++;
			}
		}

		return height;
	}

	/**
	 * 判断一个树是不是完全二叉树
	 *
	 * @return 是否二叉树 true false
	 */
	public boolean isComplete() {
		return isComplete(root);
	}

	/**
	 * 判断一个树是不是完全二叉树
	 *
	 * @param root 根节点
	 * @return 是否二叉树 true false
	 */
	public boolean isComplete(Node<E> root) {
		if (root == null) return false;

		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);

		boolean leaf = false;
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			if (leaf && !node.isLeaf()) return false;

			if (node.left != null) {
				queue.offer(node.left);
			} else if (node.right != null) {
				return false;
			}

			if (node.right != null) {
				queue.offer(node.right);
			} else {
				leaf = true;
			}
		}
		return true;
	}

	/**
	 * 查找一个节点的前驱节点：中序遍历时的前一个节点
	 *
	 * @param node 节点
	 * @return 前驱节点
	 */
	private Node<E> predecessor(Node<E> node) {
		if (node == null) return null;

		// 1.从该节点的左子树中寻找（一定有）
		Node<E> p = node.left;
		if (p != null) {
			while (p.right != null) {
				p = p.right;
			}
			return p;
		}
		// 2.前驱节点在父节点或者祖父节点中（不一定有）
		while (node.parent != null && node == node.parent.left) {
			node = node.parent;
		}

		// 1.如果 node.parent == null 没有前驱，返回 null
		// 2.找到前驱节点，node == node.parent.right 返回 node.parent

		// 两种情况归一化 返回 node.parent
		return node.parent;
	}


	/**
	 * 后继节点：后继节点：中序遍历时的后一个节点，是前驱节点的镜像问题
	 *
	 * @param node 节点
	 * @return 后继节点
	 */
	private Node<E> successor(Node<E> node) {
		if (node == null) return null;

		Node<E> p = node.right;
		if (p != null) {
			while (p.left != null) {
				p = p.left;
			}
			return p;
		}

		while (node.parent != null && node == node.parent.right) {
			node = node.parent;
		}
		return node.parent;
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


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		toString(root, sb, "");
		return sb.toString();
	}

	private void toString(Node<E> node, StringBuilder sb, String prefix) {
		if (node == null) return;

		toString(node.left, sb, prefix + "L---");
		sb.append(prefix).append(node.element).append("\n");
		toString(node.right, sb, prefix + "R---");
	}

}
