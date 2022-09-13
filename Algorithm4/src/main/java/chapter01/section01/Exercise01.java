package chapter01.section01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Exercise01 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        ArrayList<Integer> list = new ArrayList<>();
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            list.add(in.nextInt());
        }
        System.out.println(list);
        f(list);
    }

    public static void f(ArrayList<Integer>  a) {
        HashMap<Integer, Integer> map = new HashMap<>();

        int maxCount = 0;

        for (int i : a) {
            int count = map.getOrDefault(i, 0) + 1;
            if (count > maxCount) maxCount = count;
            map.put(i, count);
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {

            if (entry.getValue() == maxCount) {
                list.add(entry.getKey());
            }
        }

        list.sort(Integer::compareTo);

        if (list.size() % 2 == 1) {
            System.out.println(list.get(list.size() / 2));
        } else {
            System.out.println((list.get(list.size() / 2 - 1) + list.get(list.size()/ 2 )) / 2);
        }
    }
}
