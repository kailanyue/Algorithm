package _04_Queue;

import java.util.Deque;
import java.util.Queue;

/**
 * @author ngt on 2022-11-30 23:11
 * @version 1.0
 */
public class Demo {
	public static void main(String[] args) {
		MyQueue<Integer> queue = new MyQueue<>();
		queue.enQueue(11);
		queue.enQueue(22);
		queue.enQueue(33);
		queue.enQueue(44);

		// while (!queue.isEmpty()) {
		// 	System.out.println(queue.deQueue());
		// }

		System.out.println("-------------------");
		MyDeque<Integer> deque = new MyDeque<>();
		deque.enQueueFront(11);
		deque.enQueueFront(22);
		deque.enQueueRear(33);
		deque.enQueueRear(44);

		/* 尾  44  33   11  22 头 */

		// while (!deque.isEmpty()) {
		// 	System.out.println(deque.deQueueRear());
		// }

		test3();

		System.out.println(Math.floor(3.3));
	}

	static void test2() {
		MyCircleQueue<Integer> queue = new MyCircleQueue<>();
		for (int i = 0; i < 10; i++) {
			queue.enQueue(i);
		}
		// capcacity=10 size=10 front=0, [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
		System.out.println(queue);

		for (int i = 0; i < 5; i++) {
			queue.deQueue();
		}

		// capcacity=10 size=5 front=5, [null, null, null, null, null, 5, 6, 7, 8, 9]
		System.out.println(queue);

		for (int i = 15; i < 20; i++) {
			queue.enQueue(i);
		}

		// capcacity=10 size=10 front=5, [15, 16, 17, 18, 19, 5, 6, 7, 8, 9]
		System.out.println(queue);
	}

	static void test3() {
		MyCircleDeque<Integer> queue = new MyCircleDeque<>();
		// 头5 4 3 2 1  100 101 102 103 104 105 106 8 7 6 尾

		// 头 8 7 6  5 4 3 2 1  100 101 102 103 104 105 106 107 108 109 null null 10 9 尾
		for (int i = 0; i < 10; i++) {
			queue.enQueueFront(i + 1);
			queue.enQueueRear(i + 100);
		}

		// 头 null 7 6  5 4 3 2 1  100 101 102 103 104 105 106 null null null null null null null 尾
		for (int i = 0; i < 3; i++) {
			queue.deQueueFront();
			queue.deQueueRear();
		}

		// 头 11 7 6  5 4 3 2 1  100 101 102 103 104 105 106 null null null null null null 12 尾
		queue.enQueueFront(11);
		queue.enQueueFront(12);
		System.out.println(queue);

	}

}
