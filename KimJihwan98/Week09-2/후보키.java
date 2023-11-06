import java.util.*;
class Solution {
    static String[][] rel;
    static int r_num, c_num;
    static HashSet<String> set;
    public int solution(String[][] relation) {
        int answer = 0;
        
        rel = relation;
        set = new HashSet<>();
        r_num = rel.length;
        c_num = rel[0].length;
        
        for(int i = 1; i <= c_num; i++) {
            boolean[] visited = new boolean[c_num];
            dfs(0,0,i,visited);
        }
        answer = set.size();
        for(String s : set) {
            System.out.println(s);
        }
        
        return answer;
    }
    
    static void dfs(int sidx, int ridx, int r, boolean[] visited) {
        if(ridx == r) {
            String s = "";
            for(int i = 0; i < c_num; i++) {
                if(visited[i]) s += i;
            }
            //System.out.println(s);
            if(isCandidate(s, visited)) {
                set.add(s);
            }
            return;
        }
        
        if(sidx == c_num) return;
        
        visited[sidx] = true;
        dfs(sidx+1, ridx+1, r, visited);
        visited[sidx] = false;
        dfs(sidx+1, ridx, r, visited);
    }
    
    static boolean isCandidate(String s, boolean[] visited) {
        // 뽑은 컬럼들이 후보키가 될 수 있는지(set이랑 비교해서 확인)
        for(String str : set) {
            boolean check = true;
            for(int i = 0; i < str.length(); i++) {
                if(!s.contains(str.charAt(i) + "")) check = false;
            }
            if(check) return false;
        }
        
        HashSet<String> tmp = new HashSet<>();
        for(int i = 0; i < r_num; i++) {    
            String temp = "";
            for(int j = 0; j < c_num; j++) {
                if(visited[j]) {
                    temp += rel[i][j] + "/";
                }
            }
            tmp.add(temp);
        }
        if(tmp.size() != r_num) return false;        
        
        return true;
    }
}