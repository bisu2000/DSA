class Solution {
    public int singleNumber(int[] nums) {
        
        //variable to store the XOR result of a 
        int singleValue =nums[0];

        //start a loop
        for(int i =1; i<nums.length; i++){

            singleValue = singleValue ^ nums[i];
        }
        return singleValue;
        
    }
}