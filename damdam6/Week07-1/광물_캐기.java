import java.util.*;

class Solution {
    
static public int answer;
static public String[] mine;
static public int tired;

    
public int solution(int[] picks, String[] minerals) {
        answer = Integer.MAX_VALUE;
        mine = minerals;
        
        map.put("diamond",0);
        map.put("iron",1);
        map.put("stone",2);
        
        
        tired = 0;
    
    for(int i=0;i<3;i++){
        if(picks[i] ==0)continue;
        nowP = new pick(i);
        picks[i]--;
        tire(picks, 0, nowP);
        picks[i]++;
}
    
        return answer;
    }

static int[][] table = new int[][]{{1,1,1},{5,1,1},{25,5,1}};
static Map<String, Integer> map = new HashMap<>();
static public pick nowP;
    
static void tire(int[] picks, int idx, pick nowP){
    if(tired >=answer)return;
    if(idx == mine.length){       
        answer = Math.min(answer, tired);
        return;
    }
    if((picks[0]==0 && picks[1]==0 && picks[2]==0 && nowP.cnt<=0)){
        answer = Math.min(answer, tired);
        return;
    }
    
    //지금 곡괭이 그대로 쓸때
    if(nowP.cnt !=0){
        tired+= table[nowP.typ][map.get(mine[idx])];
        nowP.cnt--;
        tire(picks, idx+1, nowP);
        nowP.cnt++;
        tired-= table[nowP.typ][map.get(mine[idx])];
    }
    
    
    else{
        
        for(int i=0;i<3;i++){
            if(picks[i]==0)continue;
            nowP = new pick(i);
            picks[i]--;
            tired+= table[nowP.typ][map.get(mine[idx])];
            nowP.cnt--;
            tire(picks, idx+1, nowP);
            nowP.cnt++;
            tired-= table[nowP.typ][map.get(mine[idx])];        
            picks[i]++;
        }
        
    }
    
    
}

static class pick{
    
    int cnt;
    int typ;
    
    public pick(int tp){
        this.cnt = 5;
        this.typ = tp; // 0 다이아, 1 철, 2 돌
    }
    
 }
}
