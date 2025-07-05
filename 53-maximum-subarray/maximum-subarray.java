class Solution {
    public int maxSubArray(int[] nums) {
        //to find the maximum sum possible
        int max_so_far =nums[0];
        //t store the maximum found at a position
        int curr_max= nums[0];
        for (int i=1; i<nums.length; i++){
            //evaluate step 3
            curr_max=Math.max(nums[i],nums[i]+curr_max);

            // evaluate to step 4
            max_so_far =Math.max (curr_max, max_so_far);
        }

       return max_so_far;  
    }
}