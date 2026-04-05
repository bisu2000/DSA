class Solution {
    public int mirrorFrequency(String s) {
        Map<Character, Integer> mp = new HashMap<>();

        // Count frequency
        for (char ch : s.toCharArray()) {
            mp.put(ch, mp.getOrDefault(ch, 0) + 1);
        }

        int cnt = 0;

        // For a-m
        for (char ch = 'a'; ch <= 'm'; ch++) {
            char mch = (char) ('z' - (ch - 'a'));
            cnt += Math.abs(mp.getOrDefault(ch, 0) - mp.getOrDefault(mch, 0));
        }

        // For 0-4
        for (char ch = '0'; ch <= '4'; ch++) {
            char mch = (char) ('9' - (ch - '0'));
            cnt += Math.abs(mp.getOrDefault(ch, 0) - mp.getOrDefault(mch, 0));
        }

        return cnt;
    }
}
    