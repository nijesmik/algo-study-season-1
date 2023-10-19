import java.util.*;

class Solution {
    static int[][] pirodo = {{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};
    static int pn, mn, need_pn, answer;
    static HashMap<String, Integer> m_index;
    public int solution(int[] picks, String[] minerals) {
        m_index = new HashMap<>();
        m_index.put("diamond", 0);
        m_index.put("iron", 1);
        m_index.put("stone", 2);
            
        answer = Integer.MAX_VALUE;
        
        mn = minerals.length;
        pn = 0;
        need_pn = mn/5 +1;
        
        for(int i = 0; i < 3; i++) {
            pn += picks[i];
        }
        
        dfs(0, 0, 0, picks, minerals);
        return answer;
    }
    static int[] need_picks;
    static void dfs(int p_num, int midx, int result, int[] picks, String[] minerals) {
        if(result > answer) return;
        if(p_num == pn || p_num > need_pn || midx >= mn) {
            answer = Math.min(answer, result);
        }
        
        for(int i = 0; i < 3; i++) {
            if(picks[i] == 0) continue;
            picks[i]--;
            int tmp_pirodo = 0;
            for(int j = 0; j < 5; j++) {
                if(midx+j >= mn) break;
                tmp_pirodo += pirodo[i][m_index.get(minerals[midx+j])];
            }
            dfs(p_num+1, midx+5, result+tmp_pirodo, picks, minerals);
            picks[i]++;
        }
    }
    
}