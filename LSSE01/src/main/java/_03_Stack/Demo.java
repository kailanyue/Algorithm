package _03_Stack;

import java.util.Stack;

/**
 * @author ngt on 2022-11-29 23:28
 * @version 1.0
 */
public class Demo {
	public static void main(String[] args) {
		MyStack<Integer> stack = new MyStack<>();
		stack.push(11);
		stack.push(22);
		stack.push(33);
		stack.push(44);

		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}
}
