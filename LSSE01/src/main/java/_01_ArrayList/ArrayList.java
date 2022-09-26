package _01_ArrayList;

/**
 * @author ngt on 2022-09-14 11:52
 * @version 1.0
 */
public class ArrayList<E> {

	private int size;
	private E[] elements;

	// 默认容量
	private static final int DEFAULT_CAPACITY = 10;
	private static final int ELEMENT_NOT_FOUND = -1;

	public ArrayList(int capaticy) {
		// 防止传入的初始容量不合法或者过小
		capaticy = Math.max(capaticy, DEFAULT_CAPACITY);
		elements = (E[]) new Object[capaticy];
	}

	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean contains(E element) {
		return indexOf(element) != ELEMENT_NOT_FOUND;
	}

	public void add(E element) {
		add(size, element);
	}

	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
		}
		return elements[index];
	}

	public E set(int index, E element) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
		}
		E oldValue = elements[index];
		elements[index] = element;
		return oldValue;
	}

	public void add(int index, E element) {
		rangeCheckForAdd(index);
		ensureCapacity(size + 1);

		for (int i = size; i > index; i--) {
			elements[i] = elements[i - 1];
		}
		elements[index] = element;
		size++;
	}

	public E remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
		}
		E value = elements[index];
		for (int i = index + 1; i < size; i++) {
			elements[i - 1] = elements[i];
		}
		elements[--size] = null;

		trim();
		return value;
	}

	public int indexOf(E element) {
		if (null == element) {
			for (int i = 0; i < size; i++) {
				if (elements[i] == null) return i;
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (element.equals(elements[i])) return i;
			}
		}
		return ELEMENT_NOT_FOUND;
	}

	public void clear() {
		for (int i = 0; i < size; i++) {
			elements[i] = null;
		}
		size = 0;
	}

	private void ensureCapacity(int capacity) {
		int oldCapacity = elements.length;
		if (oldCapacity >= capacity) return;

		int newCapacity = oldCapacity + (oldCapacity >> 1);

		E[] newElements = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[i];
		}
		elements = newElements;

		System.out.println(oldCapacity + "扩容为" + newCapacity);
	}


	private void trim() {
		int oldCapacity = elements.length;
		int newCapacity = oldCapacity >> 1;
		if (size > newCapacity || oldCapacity <= DEFAULT_CAPACITY) return;

		// 剩余空间还很多
		E[] newElements = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[i];
		}
		elements = newElements;

		System.out.println(oldCapacity + "缩容为" + newCapacity);
	}


	private void outOfBounds(int index) {
		throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
	}

	private void rangeCheck(int index) {
		if (index < 0 || index >= size) {
			outOfBounds(index);
		}
	}

	private void rangeCheckForAdd(int index) {
		if (index < 0 || index > size) {
			outOfBounds(index);
		}
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("size = ").append(size).append(", [");
		for (int i = 0; i < size; i++) {
			if (i != 0) {
				string.append(", ");
			}
			string.append(elements[i]);
		}
		string.append("]");
		return string.toString();
	}
}
