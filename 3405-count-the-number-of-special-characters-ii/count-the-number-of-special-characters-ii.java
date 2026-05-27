class Solution {
    public int numberOfSpecialChars(String word) {
        
     int[] lastLower = new int[26];
        int[] firstUpper = new int[26]; 
        // initialize
        Arrays.fill(lastLower, -1);
        Arrays.fill(firstUpper, Integer.MAX_VALUE);
        // store:
        // last occurrence of lowercase
        // first occurrence of uppercase
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                lastLower[ch - 'a'] = i;
            } else {
                firstUpper[ch - 'A'] = Math.min(firstUpper[ch - 'A'], i);
            }
        }
        int ans = 0;
        // valid if:
        // lowercase exists
        // uppercase exists
        // every lowercase comes before first uppercase
        for (int i = 0; i < 26; i++) {
            if (lastLower[i] != -1 &&
                firstUpper[i] != Integer.MAX_VALUE &&
                lastLower[i] < firstUpper[i]) {
                
                ans++;
            }
        }
        return ans;
    }
}