
//https://leetcode.com/problems/coin-change/

class Solution {
    //We will solve this problem using recursion and DP
    
    public int coinChange(int[] coins, int v) {
        //dp array which will store minimum numbers of coins required for all the numbers in the range of '0' to amount(v). This will help us to not calculate for any number again and again which will result in reducing time complexity drastically from('m raise to power n' to 'm*n', where m is the length of coins array and n is the amount)
        int dp[] = new int[v + 1];    
        //we fill dp with -1
        Arrays.fill(dp, -1); 
        dp[0] = 0;      //because for '0' amount we need '0' coins
        
        int result = util(coins, v, dp);
        //if it's max value then it means we were not able to make any combination of coins that will give us the amount so we return '-1'
        if(result == Integer.MAX_VALUE) {      
            return -1;
        } else {
            return result;
        }
    }
    
    public int util(int[]coins, int v, int[] dp) {
        if(v == 0) {  //base condition
            return 0;
        }
        
        int ans = Integer.MAX_VALUE;    //this will store ans
        
        for(int i = 0; i < coins.length; i++) {
            if(v-coins[i] >= 0) {       //if the value of coin is greater than amount then we skip it
                int subAns = 0;         
                if(dp[v-coins[i]] != -1) {  //if we have solved for that number then we use that and don't do a recursive call
                    subAns = dp[v-coins[i]];
                } else {   //if it's not solved then we do a recursive call by subtracting amount(v) with current coin(coin[i])
                    subAns = util(coins, v-coins[i], dp);
                }
                if(subAns != Integer.MAX_VALUE && subAns + 1 < ans) {   //when we find a smaller number then we update our ans
                    ans = subAns + 1;
                }
            }
        }
        dp[v] = ans;    //we update our dp so that we don't have to find again for that number
        return ans;
    }
}

//Time complexity : O(m * n) where m is the length of coins array and n is the amount 
//Space complexity : O(n) because we are using dp array of size n + 1, we are not calculating recursive call stack