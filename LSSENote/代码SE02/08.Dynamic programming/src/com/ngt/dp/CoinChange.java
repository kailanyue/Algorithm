package com.ngt.dp;

/**
 * @author ngt
 * @create 2020-08-27 15:08
 */
public class CoinChange {
    public static void main(String[] args) {
        System.out.println(coins2(0));
    }

    public static int coins4(int n) {
        if (n < 1) return -1;
        int[] dp = new int[n + 1];
        int[] faces = new int[dp.length];
        for (int i = 1; i < n + 1; i++) {
            int min = dp[i - 1];
            faces[i] = 1;

            if (i >= 5) {
                min = dp[i - 5];
                faces[i] = 5;
            }
            if (i >= 20) {
                min = dp[i - 20];
                faces[i] = 20;
            }
            if (i >= 25) {
                min = dp[i - 25];
                faces[i] = 25;
            }
            dp[i] = min + 1;
        }
        return dp[n];
    }

    public static void print(int[] faces, int n) {
        System.out.print("[" + n + "] = ");
        while (n > 0) {
            System.out.println(faces[n] + " ");
            n -= faces[n];
        }
        System.out.println();
    }

    public static int coins3(int n) {
        if (n < 1) return -1;
        int[] dp = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            int min = Integer.MAX_VALUE;
            if (i >= 1) min = Math.min(dp[i - 1], min);
            if (i >= 5) min = Math.min(dp[i - 5], min);
            if (i >= 20) min = Math.min(dp[i - 20], min);
            if (i >= 25) min = Math.min(dp[i - 25], min);
            dp[i] = min + 1;
        }
        return dp[n];
    }

    public static int coins2(int n) {
        if (n < 1) return -1;
        int[] dp = new int[n + 1];
        int[] faces = {1, 5, 20, 25};
        for (int face : faces) {
            if (n < face) break;//如果最小面值大于n就直接返回
            dp[face] = 1;
        }
        return coins2(n, dp);

    }

    /**
     * 记忆化搜索
     */
    public static int coins2(int n, int[] dp) {
        if (n < 1) return Integer.MAX_VALUE;
        if (dp[n] == 0) {
            int min1 = Math.min(coins1(n - 25), coins1(n - 20));
            int min2 = Math.min(coins1(n - 5), coins1(n - 1));
            dp[n] = Math.min(min1, min2) + 1;
        }
        return dp[n];
    }

    /**
     * 暴力递归（自顶向下的调用，出现了重叠子问题）
     */
    public static int coins1(int n) {
        if (n < 1) return Integer.MAX_VALUE;
        if (n == 25 || n == 20 || n == 5 || n == 1) return 1;
        int min1 = Math.min(coins1(n - 25), coins1(n - 20));
        int min2 = Math.min(coins1(n - 5), coins1(n - 1));
        return Math.min(min1, min2) + 1;
    }
}
