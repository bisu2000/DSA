class Solution {
    public int singleNonDuplicate(int[] nums) {
        int n = nums.length; //size of the array.
        int ans = 0;
        // XOR all the elements:
        for (int i = 0; i < n; i++) {
            ans = ans ^ nums[i];
        }
        return ans;
    }
}