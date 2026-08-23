class Solution {
    public boolean sumGame(String num) {
        
   int n = num.length();
        int s1 = 0, q1 = 0;
        int s2 = 0, q2 = 0;

        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);
            if (i < n / 2) {
                if (c == '?') {
                    q1++;
                } else {
                    s1 += c - '0';
                }
            } else {
                if (c == '?') {
                    q2++;
                } else {
                    s2 += c - '0';
                }
            }
        }

        return (q1 + q2) % 2 != 0 || (s1 - s2) != (q2 - q1) / 2 * 9;
    }
}