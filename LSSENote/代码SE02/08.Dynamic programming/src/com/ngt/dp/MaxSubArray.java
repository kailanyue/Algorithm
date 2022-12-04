package com.ngt.dp;

/**
 * 最大连续子序列和
 * @author ngt
 * @create 2020-08-27 18:58
 */
public class MaxSubArray {

    public static int MaxSubArray1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        //int[] dp = new int[nums.length];
        int dp = nums[0];
        int max = dp;
        for (int i = 1; i < nums.length; i++) {
            if (dp <= 0) {
                dp = nums[i];
            } else {
                dp = dp + nums[i - 1];
            }
            max = Math.max(max, dp);
        }
        return max;
    }

    public static int MaxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < dp.length; i++) {
            if (dp[i - 1] <= 0) {
                dp[i] = nums[i];
            } else {
                dp[i] = dp[i - 1] + nums[i - 1];
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
