import java.util.*;

class Solution {
    
    static int row;
    static int col;
    static String[][] table;
    public int solution(String[][] relation) {
        int answer = 0;
        table = relation;
        row = table.length;
        col = table[0].length;
        visited = new int[col];
        chkArr = new ArrayList<>();
        
        for(int size = 1;size<=col;size++){
            dfs(-1, size, 0);
        }
        answer = chkArr.size();
        return answer;
    }
    
    static int[] visited;
public void dfs(int idx, int size, int already){
    if(idx > col)return;
    if(already == size){
        
        check();
        
        return;
    } 
    for(int i = idx+1; i<col ; i++){
        visited[i] = 1;
        dfs(i, size, already+1);
        visited[i] = 0;
    }
}
    
static ArrayList<String> chkArr;
static void check(){


    String str="";
    for(int i=0; i<col;i++){
    if(visited[i] == 0)continue;
     str += table[0][i]+i+"/"; 
    }
    
    //System.out.println(chkArr.toString());
    //겹치면 아웃
    for(String chk : chkArr){
        String[] stringArr = chk.split("/", -1);
        int chkT = 0;
        for(String ss : stringArr){
            if(str.contains(ss))chkT++;
        }
        if(chkT == stringArr.length)return;
    }
    if(candi()){
        //System.out.println(Arrays.toString(visited));
        chkArr.add(str);
    }
    
 }
    
    static boolean candi(){
        
        HashSet<String> tmpSet = new HashSet<>();
        
        String str = "";
        for(int i=0;i<row;i++){
            
            str = "";
            for(int j=0;j<col;j++){
                if(visited[j]  == 0 )continue;
                str += table[i][j]+"/";
            }
            tmpSet.add(str);
        }
        
        if(tmpSet.size() != row)return false;
        
        
        return true;
    }
}



