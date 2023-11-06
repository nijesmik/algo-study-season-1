import java.util.*;

class Solution {
    static long[][] mtx;
    static long[][] bsc;
    public long solution(int n, int[] works) {
        mtx = new long[2][n+1];
        bsc = new long[2][n+1];
        Arrays.sort(works);
    
        //특정한 숫자를 뺏을 경우에 대한 mtx
        for(int j = 0; j < n+1 ; j ++){
            if(works[0] < j){
                bsc[0][j] = 0;
                continue;
            }  
            bsc[0][j] = (works[0]-j)*(works[0]-j);
            
            }


        //첫번째 숫자에 대한 mtx
        for(int j = 0; j<n+1;j++){
            mtx[0][j] = bsc[0][j];
        }
        
        for(int i=1;i<works.length;i++ ){
            
            for(int j = 0; j < n+1 ; j ++){
            if(works[i] < j){
                bsc[i%2][j] = 0;
                continue;
            }  
            bsc[i%2][j] = (works[i]-j)*(works[i]-j);
            
            }
            
            //새로 채울 mtx라인 초기화 (max로)
            for(int j = 0; j< n+1 ;j ++){
             mtx[i%2][j] = Long.MAX_VALUE;
            }
            //첫칸 채우기
             mtx[i%2][0] = mtx[(i+1)%2][0]+bsc[i%2][0];
                 
            for(int j = 0; j < n+1 ; j ++){
                for(int k = 0; k <= j; k++){
                    if(mtx[(i+1)%2][k] >= mtx[i%2][j] )continue;
                    mtx[i%2][j] = Math.min(mtx[i%2][j], mtx[(i+1)%2][k]+bsc[i%2][j-k]);
                }

            }
        }

        
        
        long answer = mtx[(works.length-1)%2][n];
        return answer;
    }
}