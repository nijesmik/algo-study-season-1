package pract2;

import java.util.*;
class Solution {
    static HashMap<String, Integer> map;
    static int[][] cost;
    static String[] minerals;
    static int ans;
    
    static void dfs(int L, int[] arr, int[] picks){
        if(L==arr.length){
            int tmp = pLen(arr);
            ans = Math.min(ans,tmp);
        }else{
            for(int i=0; i<picks.length; i++){
                if(picks[i]!=0){
                    arr[L] = i;
                    picks[i]--;
                    dfs(L+1, arr,picks);
                    picks[i]++;
                }
            }
        }
    }
    
    static int pLen(int[] arr){
        int res = 0;
        int idx=0;
        for(int x:arr){
            int cnt = 5;
            while(cnt>0){
                if(idx==minerals.length) return res;
                res += cost[x][map.get(minerals[idx++])];
                cnt--;
            }
        }
        return res;
    }
    public int solution(int[] picks, String[] minerals) {
        map = new HashMap<>();
        int index = 0;
        this.minerals = minerals;
        ans = Integer.MAX_VALUE;
        map.put("diamond", index++);
        map.put("iron", index++);
        map.put("stone",index++);
        cost = new int[][]{{1,1,1},{5,1,1},{25,5,1}};
        int pickLen = 0;
        for(int x : picks) pickLen+=x;
        
        dfs(0,new int[pickLen], picks);
        
        return ans;

    }
}