import java.util.*;


class Solution {
    static node[][] bd;
    public int[] solution(String[] grid) {
        
        //grid는 격자임
        bd= new node[grid.length][grid[0].length()];
        ans = new ArrayList<Integer>();

        for(int i=0;i<bd.length;i++){            
               String tmp= grid[i]; 
            for(int j=0;j<bd[0].length;j++){   
                bd[i][j] = new node(tmp.charAt(j));
            }
        }
        
        for(int i=0;i<bd.length;i++){
             for(int j=0;j<bd[0].length;j++){ 
                 
                 for(int k=0; k<4;k++){
                     if(bd[i][j].dirVt[k] ==1)continue;
                     bfs(i,j,k);
                 }

                 
             }
            
        }
        Collections.sort(ans);
        int[] answer = new int[ans.size()];
        for(int i=0;i<ans.size();i++){
            answer[i] = ans.get(i);
        }

        
        return answer;
    }
    
    //상 우 하 좌 (0, 1, 2, 3)
    static int[] dx = new int[]{-1,0,1,0};
    static int[] dy = new int[]{0, 1, 0, -1};
    
    public static void bfs(int startx, int starty, int dir){
        
        
        node[][] newBd = bd;
        
        int nowDir = dir;
        Deque<forQu> qu = new ArrayDeque<>();
        
        qu.add(new forQu(startx, starty));        
        newBd[startx][starty].dirVt[nowDir] = 1;
        
        int cnt = 0;
        
        forQu tmp;
        while(!qu.isEmpty()){
            
            tmp = qu.poll();            
            
            cnt++;
            
            int nx = tmp.x + dx[nowDir];
            int ny = tmp.y + dy[nowDir];
            
            if(nx<0){
                nx = nx+newBd.length;
            }else if(nx>=newBd.length){
                nx =0;
            }
            
            if(ny<0){
                ny = ny+newBd[0].length;
            }else if(ny>=newBd[0].length){
                ny =0;
            }
            
            nowDir = getDir(newBd[nx][ny].dir, nowDir);
            if(nx == startx && ny == starty && newBd[nx][ny].dirVt[nowDir] == 1){
                
                ans.add(cnt);
               return;
            }
            
            if(newBd[nx][ny].dirVt[nowDir] == 1){
                break;
            }else{
                newBd[nx][ny].dirVt[nowDir] = 1;
                qu.add(new forQu(nx, ny));
            }
            
        }

    }
    
    static ArrayList<Integer> ans;

    static class node{
        char dir;
        int[] dirVt;
        node(char dir){
            this.dir = dir;
            dirVt = new int[4];
        }        
    }
    
    static class forQu{
        int x;
        int y;
        int stage;
        
        forQu(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
    //현재 칸의 char / 지금의 dir 동시에 넣기
    static int getDir(char c, int nowDir){
        
        switch(c){
                
            case 'L':
                nowDir= (nowDir+3)%4;
                break;
            case 'R':
                 nowDir= (nowDir+1)%4;
                break;
            case 'S':
                break;
        }
        
        return nowDir;
    }
}