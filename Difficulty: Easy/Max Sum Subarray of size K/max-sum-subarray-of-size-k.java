class Solution {
    public int maxSubarraySum(int[] arr, int k) {
        // Code here
        int sum = 0,
            max = 0;
        
        // first window sum
        for (int i = 0; i < k; i++) {
            sum = sum + arr[i];
        }
        
        max = Math.max (max,sum);
        
        // sliding window 
        for (int i = k; i < arr.length; i++ ){
            sum = sum + arr[i];
            sum = sum - arr[i - k];
            
            max = Math.max(max,sum);
            
        }
        return max;
    }
}
/// T : O(n)   , s: O(1)

