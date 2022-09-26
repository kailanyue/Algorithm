package _02_LinkedList;

/**
 * @author ngt
 * @create 2021-04-29 22:47
 */
public class Asserts {
    public static void test(boolean value) {
        try {
            if (!value) throw new Exception("测试未通过");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
