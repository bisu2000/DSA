class Solution {
    public long maxTotalValue(int[] nums, int k) {
        
     int maxEl = Integer.MIN_VALUE;
        int minEl = Integer.MAX_VALUE;

        for(int num : nums) {
            maxEl = Math.max(maxEl, num);
            minEl = Math.min(minEl, num);
        }

        return (long)(maxEl - minEl) * k;
    }
}