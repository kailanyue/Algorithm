package com.ngt.backtracking;

/**
 * @author ngt
 * @create 2020-08-22 23:26
 */
public class Demo {
    public static void main(String[] args) {
        int n = 12;
        Times.test("Queens1", ()->{
            Queens1.queens(n);
        });

        Times.test("Queens2", ()->{
            Queens2.queens(n);
        });

        Times.test("Queens3", ()->{
            Queens3.queens(n);
        });
    }
}
