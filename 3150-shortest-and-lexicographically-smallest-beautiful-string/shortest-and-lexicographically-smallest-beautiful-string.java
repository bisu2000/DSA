class Solution {
    public String shortestBeautifulSubstring(String s, int k) {

        int n = s.length();
        int i = 0;
        int ones = 0;

        String result = "";

        for (int j = 0; j < n; j++) {

            // Add current character
            if (s.charAt(j) == '1') {
                ones++;
            }

            // Remove extra 1s
            while (i <= j && ones > k) {
                if (s.charAt(i) == '1') {
                    ones--;
                }
                i++;
            }

            // Remove leading 0s
            while (i <= j && s.charAt(i) == '0') {
                i++;
            }

            // If window contains exactly k ones
            if (ones == k) {

                String temp = s.substring(i, j + 1);

                // Update result if:
                // 1. result is empty
                // 2. temp is shorter
                // 3. same length but temp is lexicographically smaller
                if (result.isEmpty()
                        || temp.length() < result.length()
                        || (temp.length() == result.length()
                            && temp.compareTo(result) < 0)) {

                    result = temp;
                }
            }
        }

        return result;
    }
}