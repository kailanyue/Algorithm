package _01_ArrayList;

/**
 * @author ngt on 2022-09-14 15:05
 * @version 1.0
 */
public class Test {

	public static void main(String[] args) {
		// ArrayList<Person> persons = new ArrayList<>();
		// persons.add(new Person(10, "Jack"));
		// persons.add(new Person(12, "James"));
		// persons.add(new Person(15, "Rose"));
		// persons.clear();
		// persons.add(new Person(22, "abc"));
		//
		// System.out.println(persons);
		ArrayList<Integer> list = new ArrayList<>();
		list.add(11);
		list.add(22);
		list.add(33);
		list.add(44);

		list.add(0, 55); // [55, 11, 22, 33, 44]
		list.add(2, 66); // [55, 11, 66, 22, 33, 44]
		list.add(list.size(), 77); // [55, 11, 66, 22, 33, 44, 77]

		list.remove(0); // [11, 66, 22, 33, 44, 77]
		list.remove(2); // [11, 66, 33, 44, 77]
		list.remove(list.size() - 1); // [11, 66, 33, 44]
		System.out.println(list);
	}
}
