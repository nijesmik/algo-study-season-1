package nijesmik.Week04;

public class 타겟_넘버 {
    int[] numbers;
    int target, answer;
    public int solution(int[] arr, int n) {
        numbers = arr;
        target = n;
        answer = 0;

        dfs(0, 0);
        return answer;
    }
    void dfs(int result, int idx) {
        if (idx == numbers.length) {
            if (result == target) answer++;
            return;
        }
        dfs(result+numbers[idx], idx+1);
        dfs(result-numbers[idx], idx+1);
    }
}