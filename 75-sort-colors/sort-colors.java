class Solution {
    public void sortColors(int[] nums) {
        int i = 0;
        int j = nums.length - 1;
        int k = 0;

        // 0 0 1 1 2 2
        // i     j
        //   k

        while (k <= j) {
            // T: O(n), S: O(1)
            if (nums[k] == 1) {
                k = k + 1;
            } 
            else if (nums[k] == 2) {
                int temp = nums[j];
                nums[j] = nums[k];
                nums[k] = temp;

                j = j - 1;
            } 
            else {
                int temp = nums[i];
                nums[i] = nums[k];
                nums[k] = temp;

                i = i + 1;
                k = k + 1;
            }
        }
    }
}
