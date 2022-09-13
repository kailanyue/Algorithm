package chapter01.section03;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author ngt on 2022-09-13 14:54
 * @version 1.0
 * 定容字符串栈
 * 1. 只能处理 String
 */
public class _01_FixedCapacityStackOfStrings {
	private String[] a;
	private int N;

	public _01_FixedCapacityStackOfStrings(int capacity) {
		a = new String[capacity];
		N = 0;
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public boolean isFull() {
		return N == a.length;
	}

	public int size() {
		return N;
	}

	public void push(String item) {
		a[N++] = item;
	}

	public String pop() {
		return a[--N];
	}

	public String peek() {
		return a[N - 1];
	}

	public Iterator<String> iterator() {
		return new ReverseArrayIterator();
	}

	public class ReverseArrayIterator implements Iterator<String> {
		private int i = N - 1;

		@Override
		public boolean hasNext() {
			return i >= 0;
		}

		@Override
		public String next() {
			if (!hasNext()) throw new NoSuchElementException();
			return a[i--];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

}
