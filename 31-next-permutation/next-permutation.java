class Solution {
    public void nextPermutation(int[] nums) {
        int n= nums.length;

        // step 1:find break point
        int ind = -1;
        for ( int i = n-2; i>=0; i--){
            if (nums[i] < nums[i+1]){
                ind = i;
                break;
            }
        }
        // if break point does not exixt
        if (ind == -1) {
        // reverse the whole array
        reverse(nums,0,n-1);
        return;
       
        }
        // step 2: find the next greater element and swap it with array index
        for (int i = n-1; i>ind; i--) {
            if (nums[i] > nums[ind]) {
                int temp = nums[i];
                nums[i]= nums[ind];
                nums[ind]=temp;
                break;
            }
        }
        //step 3: reverse the right half
        
        reverse(nums,ind+1,n-1);
        
    }
    private void reverse (int[]nums , int start, int end){
        while (start < end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}