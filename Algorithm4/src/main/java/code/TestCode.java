package code;


public class TestCode {

    /**
     * 字符串中 0 的个数？整数的二进制数中0的个数？
     *
     * @param args
     */
    public static void main(String[] args) {
        stringZeroCountTest();
        binaryStringZeroCountTest();
//        System.out.println(Integer.toBinaryString(-2));
    }


    /**
     * 统计字符串中 0 的个数
     *
     * @param str 输入的字符串
     * @return 0 的个数
     */
    private static int stringZeroCount(String str) {
        int nums = 0;
        for (char c : str.toCharArray()) {
            if (String.valueOf(c).equals("0")) {
                nums++;
            }
        }

        return nums;
    }

    /**
     * 对 count1 进行功能测试，以及边缘场景的测试
     */
    private static void stringZeroCountTest() {
        System.out.println(stringZeroCount("0") == 1);
        System.out.println(stringZeroCount("") == 0);
        System.out.println(stringZeroCount("0110") == 2);
        System.out.println(stringZeroCount("1000dddf") == 3);
        System.out.println(stringZeroCount("dddf") == 0);
        System.out.println(stringZeroCount("中文") == 0);
    }

    /**
     * 整数的二进制表示中， 0 的个数
     *
     * @param num 输入的整数
     * @return 二进制表示中 0 的个数
     */
    private static int binaryStringZeroCount(int num) {

        // 将整数转换成二进制字符串
        String numBinaryString = Integer.toBinaryString(num);
        // 调用 stringZeroCount 方法
        return stringZeroCount(numBinaryString);
    }


    /**
     * binaryStringZeroCount 方法测试功能模块
     */
    private static void binaryStringZeroCountTest() {
        System.out.println(binaryStringZeroCount(2) == 1);
        System.out.println(binaryStringZeroCount(0) == 1);
        System.out.println(binaryStringZeroCount(3) == 0);
        System.out.println(binaryStringZeroCount(-2) == 1);
    }
}


//字符串中 0 的个数？整数的二进制数中 0 的个数？