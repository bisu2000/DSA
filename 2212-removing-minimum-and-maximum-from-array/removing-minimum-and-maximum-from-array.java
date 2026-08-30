class Solution {
    public int minimumDeletions(int[] nums) {
        

        int n = nums.length;

        int minIndex = 0;
        int maxIndex = 0;

        // Find min and max index
        for (int i = 0; i < n; i++) {

            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }

            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        // Both from front
        int option1 = Math.max(minIndex, maxIndex) + 1;

        // Both from back
        int option2 = n - Math.min(minIndex, maxIndex);

        // One from front and one from back
        int option3 =
                Math.min(minIndex, maxIndex) + 1
                + n - Math.max(minIndex, maxIndex);

        return Math.min(option1, Math.min(option2, option3));
    }
}