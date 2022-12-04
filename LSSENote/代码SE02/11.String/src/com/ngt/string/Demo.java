package com.ngt.string;

/**
 * @author ngt
 * @create 2020-08-30 15:44
 */
public class Demo {
    public static void main(String[] args) {
      test3();
    }

    public static void test1(){
        Asserts.test(BruteForce01.indexOf("Hello World", "H") == 0);
        Asserts.test(BruteForce01.indexOf("Hello World", "d") == 10);
        Asserts.test(BruteForce01.indexOf("Hello World", "or") == 7);
        Asserts.test(BruteForce01.indexOf("Hello World", "abc") == -1);
    }

    public static void test2(){
        Asserts.test(BruteForce02.indexOf("Hello World", "H") == 0);
        Asserts.test(BruteForce02.indexOf("Hello World", "d") == 10);
        Asserts.test(BruteForce02.indexOf("Hello World", "or") == 7);
        Asserts.test(BruteForce02.indexOf("Hello World", "abc") == -1);
    }

    public static void test3(){
        Asserts.test(KMP.indexOf("Hello World", "H") == 0);
        Asserts.test(KMP.indexOf("Hello World", "d") == 10);
        Asserts.test(KMP.indexOf("Hello World", "or") == 7);
        Asserts.test(KMP.indexOf("Hello World", "abc") == -1);

    }
}
