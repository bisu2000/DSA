class Solution {
    int n;
    int MOD = 1_000_000_007;

    private int getIntFromChar(char ch) {
        return ch != 'S' ? ch - '0' : 0;
    }

    private boolean isValid(int i, int j, List<String> board) {
        return i >= 0 && i < n && j >= 0 && j < n && board.get(i).charAt(j) != 'X';
    }

    public int[] pathsWithMaxScore(List<String> board) {
        n = board.size();

        int[][] tScore = new int[n][n];
        int[][] tPaths = new int[n][n];

        tScore[0][0] = 0;
        tPaths[0][0] = 1;

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                char ch = board.get(i).charAt(j);

                if (ch == 'E') continue;
                if (ch == 'X') continue;

                int upScore = 0,   upPaths = 0;
                int leftScore = 0, leftPaths = 0;
                int diagScore = 0, diagPaths = 0;

                if (isValid(i - 1, j, board)) {              // move up
                    upScore = tScore[i - 1][j];
                    upPaths = tPaths[i - 1][j];
                    if (upPaths > 0)
                        upScore += getIntFromChar(ch);
                }
                if (isValid(i, j - 1, board)) {              // move left
                    leftScore = tScore[i][j - 1];
                    leftPaths = tPaths[i][j - 1];
                    if (leftPaths > 0)
                        leftScore += getIntFromChar(ch);
                }
                if (isValid(i - 1, j - 1, board)) {          // move up-left
                    diagScore = tScore[i - 1][j - 1];
                    diagPaths = tPaths[i - 1][j - 1];
                    if (diagPaths > 0)
                        diagScore += getIntFromChar(ch);
                }

                int bestScore, bestPaths;
                if (upScore == leftScore && leftScore == diagScore) {
                    bestScore = upScore;
                    bestPaths = upPaths + leftPaths + diagPaths;
                } else if (upScore == leftScore) {
                    bestScore = upScore;
                    bestPaths = upPaths + leftPaths;
                    if (diagScore > bestScore || (diagScore == bestScore && diagPaths > bestPaths)) {
                        bestScore = diagScore; bestPaths = diagPaths;
                    }
                } else if (leftScore == diagScore) {
                    bestScore = leftScore;
                    bestPaths = leftPaths + diagPaths;
                    if (upScore > bestScore || (upScore == bestScore && upPaths > bestPaths)) {
                        bestScore = upScore; bestPaths = upPaths;
                    }
                } else {
                    bestScore = upScore; bestPaths = upPaths;
                    if (leftScore > bestScore || (leftScore == bestScore && leftPaths > bestPaths)) {
                        bestScore = leftScore; bestPaths = leftPaths;
                    }
                    if (diagScore > bestScore || (diagScore == bestScore && diagPaths > bestPaths)) {
                        bestScore = diagScore; bestPaths = diagPaths;
                    }
                }

                tScore[i][j] = bestScore;
                tPaths[i][j] = (int)(((long) bestPaths) % MOD);
            }
        }

        return new int[]{tScore[n - 1][n - 1], tPaths[n - 1][n - 1]};
    }
}