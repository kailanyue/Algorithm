package com.ngt.dp;

/**
 * 最长公共子序列
 * @author ngt
 * @create 2020-08-28 9:40
 */
public class LSC {
    public static void main(String[] args) {
        int len = lcs3(new int[]{1, 3, 5, 9, 10}, new int[]{1, 4, 9, 10});
        System.out.println(len);
    }

    public static int lcs5(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null
                || nums1.length == 0 || nums2.length == 0) {
            return 0;
        }
        int[] rowsNums = nums1, colsNums = nums2;
        if (nums1.length < nums2.length) {
            colsNums = nums1;
            rowsNums = nums2;
        }

        int[] dp = new int[colsNums.length + 1];
        for (int i = 1; i <= rowsNums.length; i++) {
            int cur = 0;
            for (int j = 1; j <= colsNums.length; j++) {
                int leftTop = cur;
                cur = dp[j];
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[j] = leftTop + 1;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
            }
        }
        return dp[colsNums.length];
    }

    public static int lcs4(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null
                || nums1.length == 0 || nums2.length == 0) {
            return 0;
        }
        int[] dp = new int[nums2.length + 1];
        for (int i = 1; i <= nums1.length; i++) {
            int cur = 0;
            for (int j = 1; j <= nums2.length; j++) {
                int leftTop = cur;
                cur = dp[j];
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[j] = leftTop + 1;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
            }
        }
        return dp[nums2.length];
    }

    public static int lcs3(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null
                || nums1.length == 0 || nums2.length == 0) {
            return 0;
        }
        int[][] dp = new int[2][nums2.length + 1];
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i & 1][j] = dp[(i - 1) & 1][j - 1] + 1;
                } else {
                    dp[i & 1][j] = Math.max(dp[(i - 1) & 1][j], dp[i & 1][j - 1]);
                }
            }
        }
        return dp[nums1.length & 1][nums2.length];
    }

    public static int lcs2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null
                || nums1.length == 0 || nums2.length == 0) {
            return 0;
        }
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[nums1.length][nums2.length];
    }

    public static int lcs1(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) return 0;
        if (nums2 == null || nums2.length == 0) return 0;
        return lcs1(nums1, nums1.length, nums2, nums2.length);
    }

    public static int lcs1(int[] nums1, int i, int[] nums2, int j) {
        if (i == 0 || j == 0) return 0;
        if (nums1[i - 1] == nums2[j - 1]) {
            return lcs1(nums1, i - 1, nums2, j - 1) + 1;
        }
        return Math.max(lcs1(nums1, i - 1, nums2, j),
                lcs1(nums1, i, nums2, j - 1));
    }
}
