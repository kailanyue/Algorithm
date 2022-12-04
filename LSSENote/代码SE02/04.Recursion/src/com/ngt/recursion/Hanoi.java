package com.ngt.recursion;

import java.awt.geom.Path2D;

/**
 * @author ngt
 * @create 2020-08-22 14:35
 */
public class Hanoi {
    public static void main(String[] args) {
        hanoi(3, "A", "B", "C");
    }

    /**
     * 将 n 个碟子从 p1 挪动到 p3
     *
     * @param p2 中间柱子
     */
    public static void hanoi(int n, String p1, String p2, String p3) {
        if (n == 1) {
            move(1, p1, p3);
            return;
        }
        hanoi(n - 1, p1, p3, p2);
        move(n, p1, p3);
        hanoi(n - 1, p2, p1, p3);
    }

    /**
     * 将 no 号盘子从 from 移动到 to
     *
     * @param no
     * @param from
     * @param to
     */
    private static void move(int no, String from, String to) {
        System.out.println("将" + no + "号盘子从" + from + "移动到" + to);
    }
}
