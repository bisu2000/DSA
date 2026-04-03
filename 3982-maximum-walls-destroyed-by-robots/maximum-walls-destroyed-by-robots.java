class Solution {
    public int maxWalls(int[] robots, int[] distance, int[] walls) {
       int n = robots.length;

        // sort walls
        Arrays.sort(walls);

        // create indices array
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) indices[i] = i;

        // sort indices based on robot positions
        Arrays.sort(indices, (a, b) -> robots[a] - robots[b]);

        // memoization cache
        int[][] cache = new int[n + 1][2];
        for (int[] row : cache) Arrays.fill(row, -1);

        return solve(0, 0, robots, distance, walls, indices, cache);
    }

    private int solve(int index, int prevDir,
                      int[] robots, int[] distance, int[] walls,
                      Integer[] indices, int[][] cache) {

        int n = robots.length;
        if (index >= n) return 0;

        if (cache[index][prevDir] != -1) {
            return cache[index][prevDir];
        }

        int robotIndex = indices[index];
        int pos = robots[robotIndex];
        int dist = distance[robotIndex];

        // -------- LEFT --------
        int startDist = pos - dist;

        if (index > 0) {
            int prevRobotIdx = indices[index - 1];
            startDist = Math.max(startDist, robots[prevRobotIdx] + 1);

            if (prevDir == 1) {
                startDist = Math.max(
                    robots[prevRobotIdx] + distance[prevRobotIdx] + 1,
                    startDist
                );
            }
        }

        startDist = Math.min(startDist, pos);

        int startIndex = lowerBound(walls, startDist);
        int currIndex = upperBound(walls, pos);

        int ans = 0;

        // take LEFT
        ans = Math.max(ans,
                (currIndex - startIndex) +
                solve(index + 1, 0, robots, distance, walls, indices, cache)
        );

        // -------- RIGHT --------
        int endDist = pos + dist;

        if (index < n - 1) {
            int nextRobotIdx = indices[index + 1];
            endDist = Math.min(endDist, robots[nextRobotIdx] - 1);
        }

        int endIndex = upperBound(walls, endDist);
        currIndex = lowerBound(walls, pos);

        // take RIGHT
        ans = Math.max(ans,
                (endIndex - currIndex) +
                solve(index + 1, 1, robots, distance, walls, indices, cache)
        );

        return cache[index][prevDir] = ans;
    }

    // lower_bound: first index >= target
    private int lowerBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] >= target) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    // upper_bound: first index > target
    private int upperBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] > target) r = mid;
            else l = mid + 1;
        }
        return l;
    }
}