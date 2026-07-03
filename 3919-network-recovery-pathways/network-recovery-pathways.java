class Solution {

    private boolean check(int mid, int n, long k, Map<Integer, List<int[]>> adj) {
        long[] result = new long[n];
        Arrays.fill(result, Long.MAX_VALUE);

        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));

        result[0] = 0;
        pq.offer(new long[] {0, 0});

        while (!pq.isEmpty()) {
            long[] top = pq.poll();
            long d     = top[0];
            int  node  = (int) top[1];

            if (d > k)
                return false;

            if (node == n - 1)
                return true;

            if (d > result[node])
                continue;

            for (int[] vec : adj.getOrDefault(node, Collections.emptyList())) {
                int adjNode = vec[0];
                int edgeCost    = vec[1];

                if (edgeCost < mid) //because I want the score to be mid
                    continue;

                if (d + edgeCost < result[adjNode]) {
                    result[adjNode] = d + edgeCost;
                    pq.offer(new long[] {d + edgeCost, adjNode});
                }
            }
        }
        return false;
    }

    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        Map<Integer, List<int[]>> adj = new HashMap<>();

        int l = Integer.MAX_VALUE, r = 0;

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            if (!online[u] || !online[v])
                continue;  

            adj.computeIfAbsent(u, x -> new ArrayList<>()).add(new int[] {v, w});
            l = Math.min(l, w);
            r = Math.max(r, w);
        }

        int answer = -1;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (check(mid, n, k, adj)) {
                answer = mid;
                l = mid + 1; 
            } else {
                r = mid - 1;
            }
        }

        return answer;
    }
}