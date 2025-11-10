class Solution {
    public int minOperations(int[] nums) {
        Stack <Integer> stack = new Stack <> ();
        int ans = 0;
        for (int num : nums){
            while (!stack.isEmpty() && stack.peek()>num){
                stack.pop();
                ans++;
            }
            if(( num !=0 ) && ( stack.isEmpty() || stack.peek()!=num)) {
                stack.push(num);
            }
        }
        return ans+ stack.size();
    }
}