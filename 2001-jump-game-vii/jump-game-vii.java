class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {
        
     int n = s.length();
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        int farthest = 0;
        while (!q.isEmpty()) {
            int curr = q.poll();
            if (curr == n - 1) {
                return true;
            }
            // possible next jump range
            int start = Math.max(curr + minJump, farthest + 1);
            int end = Math.min(curr + maxJump, n - 1);
            for (int next = start; next <= end; next++) {
                if (s.charAt(next) == '0' ) {          
                    q.offer(next);
                }
            }
            farthest = Math.max(farthest, end);
        }
        return false;
    }
}