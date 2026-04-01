class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;

        // Step 1: Merge all data → {position, health, direction, original index}
        int[][] merged = new int[n][4];
        for (int i = 0; i < n; i++) {
            merged[i][0] = positions[i];              // position
            merged[i][1] = healths[i];               // health
            merged[i][2] = directions.charAt(i);     // direction ('L' or 'R')
            merged[i][3] = i;                        // original index
        }

        // Step 2: Sort robots based on position (important for collision order)
        Arrays.sort(merged, (a, b) -> a[0] - b[0]);

        // Stack to simulate collisions
        Deque<int[]> stack = new ArrayDeque<>();

        // Step 3: Traverse robots in sorted order
        for (int i = 0; i < n; i++) {

            // If stack empty OR robot moving right → push directly
            if (stack.isEmpty() || merged[i][2] == 'R') {
                stack.push(merged[i]);
            } else {
                // Current robot is moving LEFT → possible collision

                // Case 1: Keep removing weaker right-moving robots
                while (!stack.isEmpty() &&
                       stack.peek()[2] == 'R' &&
                       stack.peek()[1] < merged[i][1]) {

                    stack.pop();          // right robot destroyed
                    merged[i][1]--;       // left robot loses 1 health
                }

                // Case 2: If still collision possible
                if (!stack.isEmpty() && stack.peek()[2] == 'R') {

                    if (stack.peek()[1] == merged[i][1]) {
                        // Both robots destroyed
                        stack.pop();
                    } else {
                        // Right robot survives with reduced health
                        stack.peek()[1]--;
                    }

                } else {
                    // No collision → push current robot
                    stack.push(merged[i]);
                }
            }
        }

        // Step 4: Store surviving robots using original index
        Map<Integer, Integer> survivingRobots = new HashMap<>();
        for (int[] robot : stack) {
            survivingRobots.put(robot[3], robot[1]);
        }

        // Step 5: Build result in original order
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (survivingRobots.containsKey(i)) {
                result.add(survivingRobots.get(i));
            }
        }

        return result;
    }
}