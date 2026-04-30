class Solution {
    public int maxPathScore(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;

        // dp[i][j][c] = max score reaching (i,j) with cost c
        int[][][] dp = new int[m][n][k + 1];

        // initialize with -1 (invalid)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int c = 0; c <= k; c++) {
                    dp[i][j][c] = -1;
                }
            }
        }

        // start at (0,0)
        dp[0][0][0] = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                for (int cost = 0; cost <= k; cost++) {
                    if (dp[i][j][cost] == -1) continue;

                    // move right
                    if (j + 1 < n) {
                        int val = grid[i][j + 1];
                        int newCost = cost + (val == 0 ? 0 : 1);
                        int newScore = dp[i][j][cost] + val;

                        if (newCost <= k) {
                            dp[i][j + 1][newCost] = Math.max( dp[i][j + 1][newCost],newScore );
                        }
                    }

                    // move down
                    if (i + 1 < m) {
                        int val = grid[i + 1][j];
                        int newCost = cost + (val == 0 ? 0 : 1);
                        int newScore = dp[i][j][cost] + val;

                        if (newCost <= k) {
                            dp[i + 1][j][newCost] = Math.max(dp[i + 1][j][newCost], newScore );
                        }
                    }
                }
            }
        }

        // find best answer at destination
        int ans = -1;
        for (int c = 0; c <= k; c++) {
            ans = Math.max(ans, dp[m - 1][n - 1][c]);
        }

        return ans;
    }
}