class Solution {
    public int maximumJumps(int[] nums, int target) {
        
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        dp[n - 1] = 0;

        for (int ind = n - 2; ind >= 0; ind--) {
            for (int i = ind + 1; i < n; i++) {
                if (Math.abs(nums[i] - nums[ind]) <= target && dp[i] != -1)
                    dp[ind] = Math.max(dp[ind], 1 + dp[i]);
            }
        }

        return dp[0];
    }
}
    