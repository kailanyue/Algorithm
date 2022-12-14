package _02_LinkedList.circle;

import _02_LinkedList.MyAbstractList;

/**
 * @author ngt on 2022-11-26 20:00
 * @version 1.0
 */
public class MySingleCircleLinkedList<E> extends MyAbstractList<E> {
	private Node<E> first;

	private static class Node<E> {
		E element;
		Node<E> next;

		public Node(E element, Node<E> next) {
			this.element = element;
			this.next = next;
		}

		@Override
		public String toString() {
			return element + "_" + next.element;
		}
	}

	@Override
	public void clear() {
		size = 0;
		first = null;
	}

	@Override
	public E get(int index) {
		return node(index).element;
	}

	@Override
	public E set(int index, E element) {
		Node<E> node = node(index);
		E old = node.element;
		node.element = element;
		return old;
	}

	@Override
	public void add(int index, E element) {
		rangeCheckForAdd(index);
		if (index == 0) {
			Node<E> newFirst = new Node<>(element, first);
			Node<E> last = (size == 0) ? newFirst : node(size - 1);
			last.next = newFirst;
			first = newFirst;
		} else {
			Node<E> prev = node(index - 1);
			prev.next = new Node<>(element, prev.next);
		}
		size++;
	}

	@Override
	public E remove(int index) {
		rangeCheck(index);

		Node<E> node = first;
		if (index == 0) {
			if (size == 1) {
				first = null;
			} else {
				Node<E> last = node(size - 1);
				first = first.next;
				last.next = first;
			}
		} else {
			Node<E> prev = node(index - 1);
			node = prev.next;
			prev.next = node.next;
		}

		size--;
		return node.element;
	}

	@Override
	public int indexOf(E element) {
		Node<E> node = first;

		if (element == null) {
			for (int i = 0; i < size; i++) {
				if (node.element == null) return i;
				node = node.next;
			}
		} else {

			for (int i = 0; i < size; i++) {
				if (element.equals(node.element)) return i;
				node = node.next;
			}
		}
		return ELEMENT_NOT_FOUND;
	}

	private Node<E> node(int index) {
		rangeCheck(index);
		Node<E> node = first;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}

		return node;
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("size=").append(size).append(", [");
		Node<E> node = first;
		for (int i = 0; i < size; i++) {
			if (i != 0) {
				string.append(", ");
			}

			string.append(node);

			node = node.next;
		}
		string.append("]");
		return string.toString();
	}
}
