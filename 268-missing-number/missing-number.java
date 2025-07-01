class Solution {
    public int missingNumber(int[] nums) {
            
            int allXOR = 0;

    // XOR all numbers in range [0, n]
    for (int i = 0; i <= nums.length; i++) {
      allXOR = allXOR ^ i;
    }

    // XOR all numbers in the given array
    for (int num : nums) {
      allXOR = allXOR ^ num;
    }

    // The missing number
    return allXOR;
        
    }
}


      ///////----- brute force method ------//////
 /*class Solution {
    public int missingNumber(int[] nums) {
        int sum = 0 ;
        for(int i = 0; i < nums.length; i++){
            sum = sum + nums[i];
        }
        int actualSum = (nums.length*(nums.length+1))/2;

        int missingNum = actualSum-sum ;

        return missingNum;

    }
}
*/