class Solution {
    static int answer, sum, N;
    public int solution(int[] numbers, int target) {
        answer = 0;
        sum = 0;
        N = numbers.length;
        dfs(0, numbers, target, 0);
        return answer;
    }
    public void dfs(int idx, int[] numbers, int target, int sum) {
        if(idx == N) {
            if(sum == target) answer++;
            return;
        }
        
        sum += numbers[idx];
        dfs(idx+1, numbers, target, sum);
        sum -= 2*numbers[idx];
        dfs(idx+1, numbers, target, sum);
        
    }
}