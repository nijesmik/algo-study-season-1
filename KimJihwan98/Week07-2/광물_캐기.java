import java.util.*;
class Solution {
    static int pick_num, answer;
    static boolean[] visited;
    static String[] pick_order, result;
    static HashMap<Integer, String> pick_index;
    static HashMap<String, Integer> mineral_index; 
    static HashMap<String, int[]> pirodo;
    public int solution(int[] picks, String[] minerals) {
        answer = Integer.MAX_VALUE;
        // 곡괭이별 인덱스
        pick_index  = new HashMap<>();
        pick_index.put(0, "diamond");
        pick_index.put(1, "iron");
        pick_index.put(2, "stone");
        //피로도
        pirodo =  new HashMap<>();
        pirodo.put("diamond", new int[] {1, 1, 1});
        pirodo.put("iron", new int[] {5, 1, 1});
        pirodo.put("stone", new int[] {25, 5, 1});
        // 광물 별 인덱스
        mineral_index = new HashMap<>();
        mineral_index.put("diamond", 0);
        mineral_index.put("iron", 1);
        mineral_index.put("stone", 2);
        
        //곡괭이 개수
        pick_num = 0;
        for(int i = 0; i < 3; i++) {
            pick_num += picks[i];
        }
        //곡괭이 배열 초기화
        pick_order = new String[pick_num];
        int idx = 0;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < picks[i]; j++) {
                pick_order[idx++] = pick_index.get(i);
            }
        }
        
        result = new String[pick_num];
        visited = new boolean[pick_num];
        perm(0, minerals);
        //System.out.println(Arrays.toString(pick_order));
        return answer;
    }
    
    static void perm(int idx, String[] minerals) {
        if(idx == pick_num) {
            int tmp_pirodo = get_pirodo(result, minerals);
            answer = Math.min(answer, tmp_pirodo);
        }
        
        for(int i = 0; i < pick_num; i++) {
            if(visited[i]) continue;
            result[idx] = pick_order[i];
            visited[i] = true;
            perm(idx+1, minerals);
            visited[i] = false;
        }
    }
    
    static int get_pirodo(String[] picks_order, String[] minerals) {
        int pn = pick_num;
        int mn = minerals.length;
        int p_idx = 0;
        int m_idx = 0;
        int tmp_pirodo = 0;
        while(true) {
            String pick = picks_order[p_idx++];
            //System.out.println("곡괭이 : " + pick);
            for(int i = 0; i < 5; i++) {
                int min_index = mineral_index.get(minerals[m_idx++]);
                tmp_pirodo += pirodo.get(pick)[min_index];
                if(m_idx == mn) return tmp_pirodo;
            }
            if(p_idx == pn) return tmp_pirodo;
        }
    }
}