class Solution {
    public String generateString(String str1, String str2) {
        int n = str1.length(), m = str2.length();
        char[] word = new char[n+m-1];
        Arrays.fill(word, 'a');  //step 1: fill with 'a'
        boolean[] fixed = new boolean[n + m - 1];

        //step 2: stamp T positions
        for(int i = 0; i < n; i++){
            if(str1.charAt(i) == 'T'){
                for(int j = 0; j < m; j++){
                    if(fixed[i +j ] && word[i + j] != str2.charAt(j))
                    return "";     //conflict between two T -stamps
                    word[i + j]=str2.charAt(j);
                    fixed [i+j]= true;

                }

            }
        }
        //step 3:verify F - positions and fix if needed
        for(int i = 0; i < n; i++) {
            if(str1.charAt(i) == 'F'){
                //check if window equals str2
                boolean equal = true;
                for(int j = 0 ; j < m; j++) {
                    if(word[i+j] != str2.charAt(j)) {
                        equal = false;
                        break;
                    }

                }
                if(equal) {
                    //find rightmost free cell to bump
                    boolean fixed_ = false;
                    for(int j = m-1; j >= 0; j--){
                        if( ! fixed[i+j] ) {
                            word [i+j] = (char) (str2.charAt (j) + 1); //bump
                            fixed_ = true;
                            break;

                        }
                    }
                    if(! fixed_)
                    return ""; // all cells fixed, can't change
                }

            }
        }
        return new String (word);   
        
    }
}