package pract;

import java.util.*;
class Solution {
    static int[][] arr;
    static int iCnt;
    static int jCnt;
    static int[] chk;
    static int cnt;
    static Queue<Integer> Q;
    
    static int bfs(int n, int s){
        chk = new int[n+1];
        Q = new LinkedList<Integer>();
        Q.offer(s);
        cnt = 0;
        while(!Q.isEmpty()){
            int tmp = Q.poll();
            chk[tmp]=1;
            
            for(int i=1; i<=n; i++){
                if(chk[i]==0 && arr[tmp][i]==1){
                    Q.offer(i);
                    cnt++;
                }
                
            }
        }
        return cnt;
        
    }
    public int solution(int n, int[][] wires) {
        
        int answer = Integer.MAX_VALUE;
        arr = new int[n+1][n+1];
        for(int i=0; i<n-1; i++){
            arr[wires[i][0]][wires[i][1]]=1;
            arr[wires[i][1]][wires[i][0]]=1;
        }
    
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                if(arr[i][j]==1){
                    arr[i][j]=0;
                    arr[j][i]=0;
            
                   iCnt = bfs(n,i);
                    jCnt = bfs(n,j);
                    
                    answer = Math.min(answer,Math.abs(iCnt-jCnt));
                    
                    arr[i][j]=1;
                    arr[j][i]=1;
                    
                    }
                    
                }
            }
        return answer;
            
        }
    }
