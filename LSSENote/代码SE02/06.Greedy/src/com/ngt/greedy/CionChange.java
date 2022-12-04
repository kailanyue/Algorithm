package com.ngt.greedy;

import java.util.Arrays;

/**
 * @author ngt
 * @create 2020-08-25 0:48
 */
public class CionChange {
    public static void main(String[] args) {

    }

    public static void cionChange(Integer[] faces, int money) {
        Arrays.sort(faces);
        int coins = 0, idx = faces.length - 1;
        while (idx >= 0) {
            while (money >= faces[idx]) {
                money -= faces[idx];
                coins++;
            }
            idx--;
        }
        System.out.println(coins);
    }

    public static void cionChange2(Integer[] faces, int money) {
        Arrays.sort(faces, (Integer f1, Integer f2) -> f2 - f1);
        int coins = 0, i = 0;
        while (i < faces.length) {
            if (money < faces[i]) {
                i++;
                continue;
            }
            System.out.println(faces[i]);
            money -= faces[i];
            coins++;
        }
    }

}
