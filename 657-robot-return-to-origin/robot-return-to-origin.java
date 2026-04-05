class Solution {
    public boolean judgeCircle(String moves) {
      int countU = 0;
        int countD = 0;
        int countL = 0;
        int countR = 0;
        for(char c : moves.toCharArray()){
            if(c == 'U') countU++;
            else if(c == 'D') countD++;
            else if(c == 'L') countL++;
            else countR++;
        }
        if(countU == countD &&  countL == countR){
            return true;
        }
        return false;
    }
}  
    