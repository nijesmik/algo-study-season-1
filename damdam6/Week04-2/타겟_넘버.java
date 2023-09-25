import java.util.*;

class Solution {
    
    public int solution(int[] numbers, int target) {
        
        answer = 0;
        result = 0;
        
        dfs(0, numbers, target);
        return answer;
    }
    
    public static int answer;
    public static int result;
    
    public static void dfs(int idx, int[] numbers, int target){
        if(idx==numbers.length){
            
            //System.out.println(result);
            if(result == target){
                answer ++;
            }
            return;
        }
        //덧셈인 경우
        result += numbers[idx];
        dfs(idx+1, numbers, target);
        
        //뺄셈인경우
        result -= numbers[idx]*2;
        dfs(idx+1, numbers, target);
        result += numbers[idx];
        
    }
}