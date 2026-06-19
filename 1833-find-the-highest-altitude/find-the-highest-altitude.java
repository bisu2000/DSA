class Solution {
    public int largestAltitude(int[] gain) {
        
    int curr = 0;
        int maxAlt = 0;

        for (int g : gain) {
            curr += g;
            maxAlt = Math.max(maxAlt, curr);
        }

        return maxAlt;
    }
}