package _03_Stack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ngt on 2022-11-29 22:36
 * @version 1.0
 */
public class MyStack<E> {
	private List<E> list = new ArrayList<>();


	public void clear() {
		list.clear();
	}

	public int size() {
		return list.size();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public void push(E element) {
		list.add(element);
	}


	public E pop() {
		return list.remove(list.size() - 1);
	}


	public E top() {
		return list.get(list.size() - 1);
	}
}
