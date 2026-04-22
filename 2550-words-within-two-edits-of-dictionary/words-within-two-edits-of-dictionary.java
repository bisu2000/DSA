class Solution {
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        
    int q = queries.length;
        int d = dictionary.length;

        int n = queries[0].length();

        List<String> res = new ArrayList<>();

        for (int i = 0; i < q; i++) {
            for (int j = 0; j < d; j++) {
                int unMatched = 0;
                String query = queries[i];
                String dict = dictionary[j];
                for (int k = 0; k < n; k++) {
                    if (query.charAt(k) != dict.charAt(k)) {
                        unMatched++;
                    }
                }
                if (unMatched < 3) {
                    res.add(query);
                    break;
                }
            }
        }
        return res;
    }
}