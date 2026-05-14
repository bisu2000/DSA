class Solution {
    public boolean isGood(int[] nums) {
        
     int n = nums.length;
        int[] count = new int[n];

        for (int a : nums) {
            if (a >= n) return false;
            count[a]++;
        }

        // Check 1 to n-2 appear exactly once
        for (int i = 1; i <= n - 2; i++) {
            if (count[i] != 1) return false;
        }

        // Check n-1 appears exactly twice
        if (count[n - 1] != 2) return false;

        return true;
    }
}