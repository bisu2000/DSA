class Solution {
    public boolean winnerSquareGame(int n) {
        
    boolean[] dp = new boolean[n + 1];

        // dp[0] = false
        // No stones -> current player cannot move -> loses

        for (int i = 1; i <= n; i++) {

            // Try removing every possible square number
            for (int j = 1; j * j <= i; j++) {

                // If opponent reaches a losing state,
                // current player can win.
                if (!dp[i - j * j]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}