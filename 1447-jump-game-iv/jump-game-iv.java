class Solution {
    public int minJumps(int[] arr) {
        
     int n = arr.length;
        if (n == 1) {
            return 0;
        }
        // value -> all indices having that value
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        queue.offer(0);
        visited[0] = true;
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                int index = queue.poll();
                // reached last index
                if (index == n - 1) {
                    return steps;
                }
                // index - 1
                if (index - 1 >= 0 && !visited[index - 1]) {
                    visited[index - 1] = true;
                    queue.offer(index - 1);
                }
                // index + 1
                if (index + 1 < n && !visited[index + 1]) {
                    visited[index + 1] = true;
                    queue.offer(index + 1);
                }
                // same value jumps
                if (map.containsKey(arr[index])) {
                    for (int next : map.get(arr[index])) {
                        if (!visited[next]) {
                            visited[next] = true;
                            queue.offer(next);
                        }
                    }
                    // IMPORTANT OPTIMIZATION
                    map.remove(arr[index]);
                }
            }
            steps++;
        }
        return -1;
    }
}