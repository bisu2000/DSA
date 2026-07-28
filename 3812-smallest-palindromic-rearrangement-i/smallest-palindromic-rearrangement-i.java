class Solution {
    public String smallestPalindrome(String s) {
        
    int n = s.length();
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        StringBuilder middle = new StringBuilder();
        StringBuilder start = new StringBuilder();
        

        // Build the first start and determine the middle character if needed
        for (int i = 0; i < 26; i++) {
            int count = freq[i];

            for (int j = 0; j < count / 2; j++) {
                start.append((char) (i + 'a'));
            }
            if (count % 2 != 0) {
                middle.append((char) (i + 'a'));
                count--;
            }

        }

        // Build the final palindrome
        StringBuilder answer = new StringBuilder();
        answer.append(start);
        answer.append(middle);
        answer.append(start.reverse());

        return answer.toString();
    }
}