package com.ngt.recursion;

/**
 * @author ngt
 * @create 2020-08-22 13:38
 */
public class Fib {
    public static void main(String[] args) {
        int n =40;
//        Times.test("fib1", ()->{
//            System.out.println(fib1(n));
//        });

        Times.test("fib2", ()->{
            System.out.println(fib1(n));
        });

        Times.test("fib3", ()->{
            System.out.println(fib1(n));
        });

        Times.test("fib4", ()->{
            System.out.println(fib1(n));
        });

        Times.test("fib5", ()->{
            System.out.println(fib1(n));
        });

        Times.test("fib6", ()->{
            System.out.println(fib1(n));
        });
    }

    public static int fib1(int n) {
        if (n <= 2) return 1;
        return fib1(n - 1) + fib1(n - 2);
    }

    public static int fib2(int n) {
        if (n <= 2) return 1;
        int[] arr = new int[n + 1];
        arr[1] = arr[2] = 1;
        return fib2(n, arr);
    }

    private static int fib2(int n, int[] arr) {
        if (arr[n] == 0) {
            arr[n] = fib2(n - 1, arr) + fib2(n - 2, arr);
        }
        return arr[n];
    }

    public static int fib3(int n) {
        if (n <= 2) return 1;
        int[] arr = new int[n + 1];
        arr[1] = arr[2] = 1;
        for (int i = 3; i <= n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[n];
    }

    public static int fib4(int n) {
        if (n <= 2) return 1;
        int[] arr = new int[2];
        arr[1] = arr[2] = 1;
        for (int i = 3; i <= n; i++) {
            arr[i % 2] = arr[(i - 1) % 2] + arr[(i - 2) % 2];
        }
        return arr[n % 2];
    }

    public static int fib5(int n) {
        if (n <= 2) return 1;
        int[] array = new int[2];
        array[0] = array[1] = 1;
        for (int i = 3; i <= n; i++) {
            array[i & 1] = array[(i - 1) & 1] + array[(i - 2) & 1];
        }
        return array[n & 1];
    }

    public static int fib6(int n) {
        if (n <= 2) return 1;
        int first = 1;
        int second = 1;
        for (int i = 3; i <= n; i++) {
            second = first + second;
            first = second - first;
        }
        return second;
    }
}
