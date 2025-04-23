class Solution {
    public boolean isPalindrome(int x) {
        if(x<0){
            return false;
        }
        int num = x;
        int revnum = 0;

        while(num>0){
            int digit = num%10;
            revnum = revnum*10+digit;
            num = num/10;
        }
        if(revnum==x){
            return true;
        }
        else{
            return false;
        }
        
    }
}