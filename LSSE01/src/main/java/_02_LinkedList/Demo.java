package _02_LinkedList;

import _02_LinkedList.circle.MyDoubleCircleLinkedList;
import _02_LinkedList.circle.MySingleCircleLinkedList;
import _02_LinkedList.doublelinked.MyDoubleLinkedList;
import _02_LinkedList.single.MySingleLinkedList;

/**
 * @author ngt
 * @create 2021-04-29 22:28
 */
public class Demo {
    public static void main(String[] args) {
        MyList<Integer> list = new MyDoubleCircleLinkedList<>();
        testList(list);
        josephus();
    }

    static void josephus() {
        MyDoubleCircleLinkedList<Integer> list = new MyDoubleCircleLinkedList<>();
        for (int i = 1; i <= 8; i++) {
            list.add(i);
        }

        // 指向头结点（指向1）
        list.reset();

        while (!list.isEmpty()) {
            list.next();
            list.next();
            System.out.print(list.remove() + " ");
        }
    }


    static void testList(MyList<Integer> list) {
        list.add(11);
        list.add(22);
        list.add(33);
        list.add(44);

        list.add(0, 55); // [55, 11, 22, 33, 44]
        System.out.println(list);

        list.add(2, 66); // [55, 11, 66, 22, 33, 44]
        list.add(list.size(), 77); // [55, 11, 66, 22, 33, 44, 77]
        System.out.println(list);


        list.remove(0); // [11, 66, 22, 33, 44, 77]
        System.out.println(list);

        list.remove(2); // [11, 66, 33, 44, 77]


        list.remove(list.size() - 1); // [11, 66, 33, 44]
        System.out.println(list);
        list.set(0, 99);

        Asserts.test(list.indexOf(44) == 3);
        Asserts.test(list.indexOf(22) == MyList.ELEMENT_NOT_FOUND);
        Asserts.test(list.contains(33));
        Asserts.test(list.get(0) == 99);
        Asserts.test(list.get(1) == 66);
        Asserts.test(list.get(list.size() - 1) == 44);
        System.out.println(list);

    }
}
