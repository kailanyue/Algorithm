package code;

public class TestCode2 {

    /*
    题目1
    编程题：提供一个方法，能进行字符串格式转换。
    支持输入2个参数类似：
    （1）原字符串：3c8-8c-ea-5-dddd-99-sssssss
    （2）整数：4
    输出格式化后的字符串：3-c88c-ea5d-ddd9-9sss-ssss
                      3-c88c-ea5d-ddd9-9sss-ssss
    请自测通过，确认无bug后，再跟面试官说提交代码
     */
    public static void main(String[] args) {
        test1();
    }

    /**
     * @param str 输入函数 3c8-8c-ea-5-dddd-99-sssssss
     * @param n   分割大小 4
     * @return 按照要求返回结果 3-c88c-ea5d-ddd9-9sss-ssss
     */
    private static String string1(String str, int n) {
        if (n <= 0 || n >= str.length()) return str;

        String replaceString = str.replace("-", "");

        char[] chars = replaceString.toCharArray();

        StringBuilder stringBuffer = new StringBuilder();

        for (int i = chars.length - 1; i >= 0; i--) {
            if (i != chars.length - 1 && i % n == 0) {
                stringBuffer.append("-");
            }
            stringBuffer.append(chars[i]);
        }

        return stringBuffer.reverse().toString();
    }

    /**
     * 测试案例
     */
    private static void test1() {
        String str = "3c8-8c-ea-5-dddd-99-sssssss";
        int n = 4;
        System.out.println(string1(str, n).equals("3-c88c-ea5d-ddd9-9sss-ssss"));

        String str1 = "3c8-8c-ea-5-dddd-99-sssssss";
        int n1 = 100;
        System.out.println(string1(str1, n1).equals("3c8-8c-ea-5-dddd-99-sssssss"));

        String str2 = "123456789";
        int n2 = 9;
        System.out.println(string1(str2, n2).equals("123456789"));

    }
}
