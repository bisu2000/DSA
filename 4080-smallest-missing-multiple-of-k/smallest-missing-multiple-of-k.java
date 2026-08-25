class Solution {
    public int missingMultiple(int[] nums, int k) {
        
    Set<Integer> set = new HashSet<>();
        int len = nums.length;

        // Step 1: Add all elements to a HashSet for O(1) lookups
        for (int i = 0; i < len; i++) {
            set.add(nums[i]);
        }

        // Step 2: Start checking multiples of k (k, 2k, 3k, ...)
        int val = 1;
        while (set.contains(val * k)) {
            val++;
        }

        // Step 3: Return the first missing multiple
        return val * k;
    }
}