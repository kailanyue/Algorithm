package _04_Queue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ngt on 2022-11-30 23:15
 * @version 1.0
 */
public class MyDeque<E> {
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

	public void enQueueRear(E element) {
		list.add(element);
	}

	public E deQueueFront() {
		return list.remove(0);
	}

	public void enQueueFront(E element) {
		list.add(0, element);
	}

	public E deQueueRear() {
		return list.remove(list.size() - 1);
	}

	public E front() {
		return list.get(0);
	}

	public E rear() {
		return list.get(list.size() - 1);
	}
}

