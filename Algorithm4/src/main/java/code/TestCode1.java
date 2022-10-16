package code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestCode1 {

    /*
    2、给定一个List集合，集合里的值按照出现的频率降序排列，一共有多少种不同的结果。
    示例1：
    输入:
    [1, 2, 3, 3, 2, 2, 1, 0]
    输出:

    2
    解释：排序结果有[2, 2, 2, 3, 3, 1, 1, 0]或[2, 2, 2, 1, 1, 3, 3, 0]

     public int frequencySort(List<Integer> list) {

     }
     */
    public static void main(String[] args) {
        testFrequencySort();
    }


    /**
     * 输出符合条件的组合个数
     *
     * @param list
     * @return
     */
    private static int frequencySort(List<Integer> list) {

        // 保存各个字符出现的频次
        HashMap<Integer, Integer> countMap = new HashMap<>();

        for (Integer i : list) {
            Integer count = countMap.getOrDefault(i, 0);
            countMap.put(i, count + 1);
        }

        // 保存各个频次出现的数量
        HashMap<Integer, Integer> strNumsMap = new HashMap<>();

        for (Integer value : countMap.values()) {
            Integer count = strNumsMap.getOrDefault(value, 0);
            strNumsMap.put(value, count + 1);
        }

        // 最终的组合数
        int nums = 1;

        for (Integer value : strNumsMap.values()) {
            for (int i = 1; i <= value; i++) {
                nums *= i;
            }
        }
        return list.isEmpty() ? 0 : nums;
    }

    /**
     * 对 frequencySort 函数进行功能测试
     */
    private static void testFrequencySort() {
        ArrayList<Integer> list0 = new ArrayList<>();
        System.out.println(frequencySort(list0) == 0);

        ArrayList<Integer> list1 = new ArrayList<>();
        //  [1, 2, 3, 3, 2, 2, 1, 0]
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(3);
        list1.add(2);

        list1.add(2);
        list1.add(1);
        list1.add(0);
        list1.add(5);

        list1.add(5);
        list1.add(5);
        list1.add(4);
        list1.add(4);
        System.out.println(frequencySort(list1));
    }
}
