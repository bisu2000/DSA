class Solution {
    static final int MOD = 1_000_000_007;
    List<Integer>[] graph;
    int[][] up;
    int[] depth;
    int LOG;
    int[] pow2;
    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        int n = edges.length + 1;
        // Build graph
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        // Binary lifting size
        LOG = 1;
        while ((1 << LOG) <= n) LOG++;
        up = new int[LOG][n + 1];
        depth = new int[n + 1];
        dfs(1, 0);
        // Build ancestors
        for (int j = 1; j < LOG; j++) {
            for (int i = 1; i <= n; i++) {
                int p = up[j - 1][i];
                up[j][i] = (p == 0) ? 0 : up[j - 1][p];
            }
        }
        // Precompute powers of 2
        pow2 = new int[n];
        pow2[0] = 1;
        for (int i = 1; i < n; i++) {
            pow2[i] = (int) ((pow2[i - 1] * 2L) % MOD);
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];
            int lca = lca(u, v);
            int dist = depth[u] + depth[v] - 2 * depth[lca];
            if (dist == 0) {
                ans[i] = 0;
            } else {
                ans[i] = pow2[dist - 1];
            }
        }
        return ans;
    }

    private void dfs(int node, int parent) {
        up[0][node] = parent;
        for (int next : graph[node]) {
            if (next != parent) {
                depth[next] = depth[node] + 1;
                dfs(next, node);
            }
        }
    }
    private int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        int diff = depth[u] - depth[v];
        for (int j = LOG - 1; j >= 0; j--) {
            if (((diff >> j) & 1) == 1) {
                u = up[j][u];
            }
        }
        if (u == v) return u;
        for (int j = LOG - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }
        return up[0][u];
    }
}