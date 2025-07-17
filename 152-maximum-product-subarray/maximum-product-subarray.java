class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int leftProduct = 1, rightProduct = 1;
        int ans = nums[0];
        
        for(int i = 0; i<n; i++){
            //if any leftproduct or rightproduct become 0 then update it into 1
            leftProduct = leftProduct == 0 ? 1 : leftProduct;
            rightProduct = rightProduct == 0? 1 : rightProduct;

            //prefix product
            leftProduct *= nums[i];

            //sufix product
            rightProduct *= nums[n-1-i];

            ans = Math.max (ans, Math.max(leftProduct, rightProduct));
        }
        return ans;
    }
}