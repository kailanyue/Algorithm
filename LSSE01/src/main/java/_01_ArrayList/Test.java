package _01_ArrayList;

/**
 * @author ngt on 2022-09-14 15:05
 * @version 1.0
 */
public class Test {

	public static void main(String[] args) {
		ArrayList<Person> persons = new ArrayList<>();
		persons.add(new Person(10, "Jack"));
		persons.add(new Person(12, "James"));
		persons.add(new Person(15, "Rose"));
		persons.clear();
		persons.add(new Person(22, "abc"));

		System.out.println(persons);
		ArrayList<Integer> ints = new ArrayList<>();
		ints.add(10);
		ints.add(10);
		ints.add(22);
		ints.add(33);
		System.out.println(ints);
	}
}
