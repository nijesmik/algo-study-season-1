class Solution {
    static int cnt;
    static int N;
    static void dfs(int L, int sum, int[] numbers, int target){
        if(L==N){
            if(sum==target) {
                cnt++;
                return;
            }
        }else{
                dfs(L+1,sum+numbers[L],numbers,target);
                dfs(L+1, sum-numbers[L],numbers,target);
        }
    }

    public int solution(int[] numbers, int target) {
        N = numbers.length;
        cnt=0;
        dfs(0,0,numbers,target);
        return cnt;
    }
}