class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        
    int n = A.length;
        Set<Integer> set =new HashSet<>();
        int[] C = new int[n];
        for(int i=0;i<n;i++){
            set.add(A[i]);
            set.add(B[i]);
            C[i] = 2 * (i+1) - set.size();
        }
        return C;
    }
}