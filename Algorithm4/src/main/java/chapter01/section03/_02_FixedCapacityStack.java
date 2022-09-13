package chapter01.section03;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author ngt on 2022-09-13 15:13
 * @version 1.0
 * 1.增加泛型
 * 2.动态调整大小
 */
public class _02_FixedCapacityStack<T> {
	private T[] a;
	private int N;

	public _02_FixedCapacityStack(int cap) {
		a = (T[]) new Object[cap];
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		return N;
	}

	public void push(T item) {
		a[N++] = item;
	}

	public T pop() {
		return a[--N];
	}

	public Iterator<T> iterator() {
		return new ReverseArrayIterator();
	}

	public class ReverseArrayIterator implements Iterator<T> {

		private int i = N - 1;

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
}
