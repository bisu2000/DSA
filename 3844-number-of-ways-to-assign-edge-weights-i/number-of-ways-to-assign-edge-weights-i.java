class Solution {
    static final int MOD = 1_000_000_007;

    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;

        // Build adjacency list
        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        // Find maximum depth using DFS
        int maxDepth = dfs(1, 0, graph);

        // Answer = 2^(maxDepth - 1)
        long ans = 1;
        for (int i = 1; i < maxDepth; i++) {
            ans = (ans * 2) % MOD;
        }

        return (int) ans;
    }

    private int dfs(int node, int parent, List<Integer>[] graph) {
        int depth = 0;

        for (int next : graph[node]) {
            if (next != parent) {
                depth = Math.max(depth, 1 + dfs(next, node, graph));
            }
        }

        return depth;
    }
}