package _06_AVLTree;

import java.util.Comparator;

/**
 * @author ngt on 2022-12-09 0:04
 * @version 1.0
 */
public class MyAVLTree<E> extends MyBinarySearchTree<E> {


	public MyAVLTree() {
		this(null);
	}

	public MyAVLTree(Comparator<E> comparator) {
		super(comparator);
	}


	private static class AVLNode<E> extends Node<E> {

		private int height = 1;

		public AVLNode(E element, Node<E> parent) {
			super(element, parent);
		}


		/**
		 * 获取一个节点的平衡因子
		 *
		 * @return 平衡因子 = 左子树的高度 - 右子树的高度
		 */
		public int balanceFactor() {
			int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
			int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;

			// 左子树的高度减去右子树的高度就是其平衡因子
			return leftHeight - rightHeight;
		}

		/**
		 * 更新一个节点的高度
		 */
		public void updateHeight() {
			int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
			int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;

			height = Math.max(leftHeight, rightHeight) + 1;
		}

		/**
		 * 返回比较高的子节点，如果高度一致就返回和当前节点同方向的节点
		 *
		 * @return 比较高的子节点
		 */
		public Node<E> tallerChild() {
			int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
			int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
			if (leftHeight > rightHeight) return left;
			if (leftHeight < rightHeight) return right;

			return isLeftChild() ? left : right;
		}


		@Override
		public String toString() {
			String parentString = "null";
			if (parent != null) {
				parentString = parent.element.toString();
			}
			return element + "_p(" + parentString + ")_h(" + height + ")";
		}
	}


	@Override
	protected Node<E> createNode(E element, Node<E> parent) {
		return new AVLNode<>(element, parent);
	}

	/**
	 * 第一个不平衡的节点必定是其祖先节点
	 *
	 * @param node 新添加的节点
	 */
	@Override
	protected void afterAdd(Node<E> node) {
		while ((node = node.parent) != null) {
			if (isBalanced(node)) {
				updateHeight(node);
			} else {
				rebalanced(node);
				//
				// 平衡之后整个树必定恢复平，无需再向上寻找
				break;
			}
		}
	}


	/**
	 * 判断一个节点是否平衡
	 *
	 * @param node 需要判断的节点
	 * @return 该节点是否平衡
	 */
	private boolean isBalanced(Node<E> node) {
		return Math.abs(((AVLNode<E>) node).balanceFactor()) <= 1;
	}


	/**
	 * 更新一个节点个高度
	 *
	 * @param node 需要更新高度的节点
	 */
	private void updateHeight(Node<E> node) {
		((AVLNode<E>) node).updateHeight();
	}


	/**
	 * 恢复平衡
	 *
	 * @param grand 高度最低的那个不平衡节点
	 */
	private void rebalanced(Node<E> grand) {
		Node<E> parent = ((AVLNode<E>) grand).tallerChild();
		Node<E> node = ((AVLNode<E>) parent).tallerChild();

		if (parent.isLeftChild()) { // L
			if (node.isLeftChild()) {// LL
				// 右旋转 祖父节点
				rotateRight(grand);
			} else { // LR
				// 左旋转 父节点
				rotateLeft(parent);
				rotateRight(grand);
				// 右旋转 祖父节点
			}
		} else { // R
			if (node.isLeftChild()) {// RL
				// 右旋转 父节点
				rotateRight(parent);
				// 左旋转 祖父节点
				rotateLeft(grand);
			} else {// RR
				// 左旋转 祖父节点
				rotateLeft(grand);
			}
		}
	}

	/**
	 * 父节点和非祖先节点都不可能失衡，
	 * 当将第一个失衡的祖先节点恢复平衡之后，上面的主线啊节点都会恢复平衡
	 * <p>
	 * 针对 RR 的场景
	 *
	 * @param grand 第一个失衡节点(必定是祖先节点)
	 */
	private void rotateLeft(Node<E> grand) {
		// RR 的情况
		Node<E> parent = grand.right;
		Node<E> child = parent.left;

		grand.right = child;
		parent.left = grand;

		afterRotate(grand, parent, child);

	}

	/**
	 * 针对 LL 的场景
	 *
	 * @param grand 第一个失衡节点(必定是祖先节点)
	 */
	private void rotateRight(Node<E> grand) {
		Node<E> parent = grand.left;
		Node<E> child = parent.right;

		grand.left = child;
		parent.right = grand;
		afterRotate(grand, parent, child);
	}


	/**
	 * 左旋和右旋操作的公共步骤
	 *
	 * @param grand  首个失衡的祖先节点
	 * @param parent 父节点
	 * @param child  父节点的子节点
	 */
	private void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {

		// 各个节点的 parent

		parent.parent = grand.parent;
		if (grand.isLeftChild()) {
			grand.parent.left = parent;
		} else if (grand.isRightChild()) {
			grand.parent.right = parent;
		} else {
			// grand 是根节点
			root = parent;
		}

		if (child != null) {
			child.parent = grand;
		}

		// 更新grand的parent
		grand.parent = parent;
		// 更新高度
		updateHeight(grand);
		updateHeight(parent);

		// // 让parent称为子树的根节点
		// parent.parent = grand.parent;
		// if (grand.isLeftChild()) {
		// 	grand.parent.left = parent;
		// } else if (grand.isRightChild()) {
		// 	grand.parent.right = parent;
		// } else { // grand是root节点
		// 	root = parent;
		// }
		//
		// // 更新child的parent
		// if (child != null) {
		// 	child.parent = grand;
		// }
		//
		// // 更新grand的parent
		// grand.parent = parent;
		//
		// // 更新高度
		// updateHeight(grand);
		// updateHeight(parent);
	}
}
