class Solution {
    private boolean bfs(int[] arr, int start, int n) {
        Queue<Integer> que = new LinkedList<>();
        que.add(start);

        while(!que.isEmpty()) {
            int curr = que.poll();

            //reached 0
            if(arr[curr] == 0)
                return true;

            //This was visited before. Ignore
            if(arr[curr] < 0)
                continue;

            int left  = curr + arr[curr];
            int right = curr - arr[curr];

            if(left >= 0 && left < n)
                que.add(left);
            if(right >= 0 && right < n)
                que.add(right);

            //curr is visited and its neighbours are explored. We don't need it
            arr[curr] = -arr[curr];
        }

        return false;
    }

    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        return bfs(arr, start, n);
    }
}