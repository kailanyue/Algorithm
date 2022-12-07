package _05_BinarySearchTree;

import treeprinter.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

/**
 * @author ngt on 2022-12-08 0:44
 * @version 1.0
 *
 * 将二叉树的方法放置在该类下面
 */
public class MyBinaryTree<E>  implements BinaryTreeInfo {

	protected int size;

	protected Node<E> root;

	// 用于接收自定义 visit 策略
	protected final Consumer<E> consumer;

	public MyBinaryTree(Consumer<E> consumer) {
		this.consumer = consumer;
	}


	protected static class Node<E> {
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

	public int size() {
		return size;
	}

	public void clear() {
		root = null;
		size = 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * 查找一个节点的前驱节点：中序遍历时的前一个节点
	 *
	 * @param node 节点
	 * @return 前驱节点
	 */
	protected Node<E> predecessor(Node<E> node) {
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
	protected Node<E> successor(Node<E> node) {
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


	// 遍历操作

	// 前序遍历（Preorder Traversal）
	public void preorder(MyBinarySearchTree.Visitor<E> visitor) {
		if (visitor == null) return;
		preorder(root, visitor);
	}

	private void preorder(Node<E> node, MyBinarySearchTree.Visitor<E> visitor) {
		// 当达到停止条件之后停止递归操作
		if (node == null || visitor.stop) return;

		visitor.stop = visitor.visit(node.element);
		preorder(node.left, visitor);
		preorder(node.right, visitor);
	}

	// 中序遍历（Inorder Traversal）
	public void inorder(MyBinarySearchTree.Visitor<E> visitor) {
		if (visitor == null) return;
		inorder(root, visitor);
	}

	private void inorder(Node<E> node, MyBinarySearchTree.Visitor<E> visitor) {
		if (node == null || visitor == null) return;

		inorder(node.left, visitor);
		// 当达到停止条件之后停止递归操作
		if (visitor.stop) return;
		visitor.stop = visitor.visit(node.element);
		inorder(node.right, visitor);
	}

	// 后序遍历（Postorder Traversal）
	public void postorder(MyBinarySearchTree.Visitor<E> visitor) {
		if (visitor == null) return;
		postorder(root, visitor);
	}

	private void postorder(Node<E> node, MyBinarySearchTree.Visitor<E> visitor) {
		if (node == null || visitor.stop) return;

		postorder(node.left, visitor);
		postorder(node.right, visitor);
		if (visitor.stop) return;
		visitor.stop = visitor.visit(node.element);
	}

	// 层序遍历（Level Order Traversal）
	public void levelOrder(MyBinarySearchTree.Visitor<E> visitor) {
		if (visitor == null) return;
		levelOrder(root, visitor);
	}

	private void levelOrder(Node<E> root, MyBinarySearchTree.Visitor<E> visitor) {
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
	 * @return 树的高度
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
