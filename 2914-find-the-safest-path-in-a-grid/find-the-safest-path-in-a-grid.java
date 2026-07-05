class Solution {
    private static final int[][] DIRECTIONS = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

     private boolean isValid(int i, int j, int n) {
        return i >= 0 && i < n && j >= 0 && j < n;
    }

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        int[][] safety = new int[n][n]; // Closest distance to a thief
        Queue<int[]> queue = new LinkedList<>();

        // Initialize safety distances and fill the queue with thief positions
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    safety[i][j] = 0;
                    queue.add(new int[]{i, j, 0});
                } else {
                    safety[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        // If start or end cell contains a thief, return 0 immediately
        if (grid.get(0).get(0) == 1 || grid.get(n - 1).get(n - 1) == 1) return 0;

        // Multi-source BFS to calculate the minimum distance to a thief for all cells
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int currX = curr[0];
            int currY = curr[1];
            int currSafety = curr[2];
            for (int[] dir : DIRECTIONS) {
                int nextX = currX + dir[0];
                int nextY = currY + dir[1];
                if (isValid(nextX, nextY, n) && safety[nextX][nextY] > currSafety + 1) {
                    queue.add(new int[]{nextX, nextY, currSafety + 1});
                    safety[nextX][nextY] = currSafety + 1;
                }
            }
        }

        // Priority queue for BFS from the start cell
        PriorityQueue<int[]> path = new PriorityQueue<>((a, b) -> Integer.compare(b[2], a[2]));
        path.add(new int[]{0, 0, safety[0][0]});
        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true;

        // BFS to find the maximum safeness factor path
        while (!path.isEmpty()) {
            int[] curr = path.poll();
            int currX = curr[0];
            int currY = curr[1];
            int currSafety = curr[2];

            // If we reach the bottom-right cell, return the current safeness factor
            if (currX == n - 1 && currY == n - 1) return currSafety;

            // Explore adjacent cells
            for (int[] dir : DIRECTIONS) {
                int nextX = currX + dir[0];
                int nextY = currY + dir[1];
                if (isValid(nextX, nextY, n) && !visited[nextX][nextY]) {
                    path.add(new int[]{nextX, nextY, Math.min(currSafety, safety[nextX][nextY])});
                    visited[nextX][nextY] = true;
                }
            }
        }

        return 0;
    }
}