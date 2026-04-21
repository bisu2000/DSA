class Solution {
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        
    int n = source.length;
        UnionFind uf = new UnionFind(n);
        // Step 1: Union all allowed swaps
        for (int[] swap : allowedSwaps) {
            uf.union(swap[0], swap[1]);
        }
        // Step 2: Group indices by root
        Map<Integer, List<Integer>> groups = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = uf.find(i);
            groups.computeIfAbsent(root, k -> new ArrayList<>()).add(i);
        }
        int hamming = 0;
        // Step 3: Process each group
        for (List<Integer> group : groups.values()) {
            Map<Integer, Integer> count = new HashMap<>();
            // Count source values
            for (int idx : group) {
                count.put(source[idx], count.getOrDefault(source[idx], 0) + 1);
            }
            // Match with target
            for (int idx : group) {
                int val = target[idx];
                if (count.getOrDefault(val, 0) > 0) {
                    count.put(val, count.get(val) - 1);
                } else {
                    hamming++;
                }
            }
        }
        return hamming;
    }
    class UnionFind {
        int[] parent;
        UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]); // path compression
            return parent[x];
        }
        void union(int a, int b) {
            int pa = find(a);
            int pb = find(b);
            if (pa != pb) {
                parent[pa] = pb;
            }
        }
    }
}