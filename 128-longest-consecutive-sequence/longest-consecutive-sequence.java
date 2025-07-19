class Solution {
    public int longestConsecutive(int[] nums) {
        int longestLength =0;
        Map<Integer, Boolean> numberTravelledMap = new HashMap<>();
        for(int num : nums){
            numberTravelledMap.put(num, Boolean.FALSE);
        }
        for(int num : nums){
            int currentLength = 1;

            // check in forward direction 
            int nextNum = num + 1;
            while (numberTravelledMap.containsKey(nextNum) && numberTravelledMap.get(nextNum)==false){
                currentLength++;
                numberTravelledMap.put(nextNum, Boolean.TRUE);

                // move th the next number
                nextNum++;
            } 
            // check in reverse direction 
            int prevNum = num-1;
            while(numberTravelledMap.containsKey(prevNum) && numberTravelledMap.get(prevNum) == false) {
                currentLength++;
                numberTravelledMap.put(prevNum,Boolean.TRUE);

                //move th the previous number
                prevNum--;
            }
            longestLength = Math.max(longestLength,currentLength);
        }
        return longestLength;
    }
}