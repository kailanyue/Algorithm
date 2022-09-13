package chapter01.section03;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author ngt on 2022-09-13 16:55
 * @version 1.0
 */
public class _03_ResizingArrayStack<T> {

	private static final int INIT_CAPACITY = 8;
	private T[] a;
	private int n;

	public _03_ResizingArrayStack() {
		a = (T[]) new Object[INIT_CAPACITY];
		n = 0;
	}


	public boolean isEmpty() {
		return n == 0;
	}

	public int size() {
		return n;
	}


	public void resize(int capacity) {
		assert capacity >= n;

		T[] copy = (T[]) new Object[capacity];

		for (int i = 0; i < n; i++) {
			copy[i] = a[i];
		}
		a = copy;
	}

	public void push(T item) {
		if (n == a.length) resize(2 * a.length);
		a[n++] = item;
	}

	public T pop() {
		if (isEmpty()) throw new NoSuchElementException("Stack underflow");
		T item = a[n - 1];
		a[n - 1] = null; //解决对象游离
		n--;

		if (n > 0 && n == a.length / 4) resize(a.length / 2);
		return item;
	}

	public T peek() {
		if (isEmpty()) throw new NoSuchElementException("Stack underflow");
		return a[n - 1];
	}

	public Iterator<T> iterator() {
		return new ReverseArrayIterator();
	}

	private class ReverseArrayIterator implements Iterator<T> {
		private int i;

		public ReverseArrayIterator() {
			i = n - 1;
		}

		@Override
		public boolean hasNext() {
			return i >= 0;
		}

		@Override
		public T next() {
			if (!hasNext()) throw new NoSuchElementException();
			return a[i--];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	public static void main(String[] args) {
		// _03_ResizingArrayStack<String> stack = new _03_ResizingArrayStack<String>();
		// while (!StdIn.isEmpty()) {
		// 	String item = StdIn.readString();
		// 	if (!item.equals("-")) stack.push(item);
		// 	else if (!stack.isEmpty()) System.out.println(stack.pop() + " ");
		// }
		// System.out.println("(" + stack.size() + " left on stack)");
	}
}
