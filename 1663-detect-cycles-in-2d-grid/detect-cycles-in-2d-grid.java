class Solution {

    int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};

    public boolean containsCycle(char[][] grid) {
        int m = grid.length, n = grid[0].length;

        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (!visited[i][j]) {
                    if (dfs(grid, i, j, -1, -1, visited)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] grid, int r, int c, int pr, int pc, boolean[][] visited) {
        visited[r][c] = true;

        for (int[] d : dirs) {
            int nr = r + d[0];
            int nc = c + d[1];

            // Check bounds
            if (nr < 0 || nc < 0 || nr >= grid.length || nc >= grid[0].length)
                continue;

            // Only move to same character
            if (grid[nr][nc] != grid[r][c])
                continue;

            // Skip the parent cell
            if (nr == pr && nc == pc)
                continue;

            // If already visited → cycle found
            if (visited[nr][nc])
                return true;

            // DFS deeper
            if (dfs(grid, nr, nc, r, c, visited))
                return true;
        }

        return false;
    }
}