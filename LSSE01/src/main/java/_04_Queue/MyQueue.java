package _04_Queue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ngt on 2022-11-30 22:08
 * @version 1.0
 */
public class MyQueue<E> {
	private final List<E> list = new ArrayList<>();


	public int size() {
		return list.size();
	}


	public boolean isEmpty() {
		return list.isEmpty();
	}


	public void clear() {
		list.clear();
	}


	public void enQueue(E element) {
		list.add(element);
	}

	public E deQueue() {
		return list.remove(0);
	}

	public E front() {
		return list.get(0);
	}

}
