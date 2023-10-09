import java.util.*;

class Solution {
    static LinkedList<Integer>[] link;
    static int tmp;
    
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        tmp =0;
        for(int w =0; w<wires.length;w++ ){
                link = new LinkedList[n+1];
        
        for(int i=0;i<n+1;i++  ){
            link[i] = new LinkedList<>();
        }
            
            for(int i =0; i<wires.length;i++ ){
            
                if(i==w)continue;
                
                link[wires[i][0]].add(wires[i][1]);
                link[wires[i][1]].add(wires[i][0]);          
                

            }
            
            //System.out.println("w"+w);
            for(int i=1;i<n+1;i++  ){
           //System.out.println(link[i].toString());
            }
            
            tmp = BFS(n);
            
            
            answer = Math.min(answer, tmp);
            
        }
        
 
        return answer;
    }
    
    static int[] vt;
    public int BFS(int n){
        vt = new int[n+1];
        Deque<Integer> qu = new ArrayDeque<>();
        
        qu.add(1);
        vt[1] = 1;
        
        int cnt = 0;
        int now;
        while(!qu.isEmpty()){
            
            now = qu.poll();
            cnt++;
            for(int a : link[now]){
                if(vt[a]!=1){
                    vt[a]=1;
                    qu.add(a);
                }
            }
    
        }
        
        int cnt2 =0;
        
        for(int i =1;i<n+1;i++ ){
            if(vt[i]!=1){
                qu.add(i);
                vt[i] = 1;
                break;
            }
        }
        
        while(!qu.isEmpty()){
            
            now = qu.poll();
            cnt2++;
            for(int a : link[now]){
                if(vt[a]!=1){
                    vt[a]=1;
                    qu.add(a);
                }
            }
    
        }
        //System.out.println(cnt+" "+cnt2);
        return Math.abs(cnt-cnt2);
        
    }
    
}