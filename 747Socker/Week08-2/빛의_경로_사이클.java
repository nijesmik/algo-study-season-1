package pract2;
import java.util.*;
class dd {
    static int[] dx ={-1,0,1,0};
    static int[] dy ={0,-1,0,1};
    static String[][] map;
    static boolean[][][] chk;
    static class Point{
        int x;
        int y;
        int d;
        
        public Point(int x, int y, int d){
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
    
   private static Point getCur(int curX, int curY, int curDir) {
        int nextRow = curX;
        int nextCol = curY;
        int nextDir = curDir;

        if((map[curX][curY].equals("S") && curDir ==0) || (map[curX][curY].equals("L") && curDir == 3) || (map[curX][curY].equals("R") && curDir == 2)) {
            nextRow = curX +1;
            nextDir = 0;
        }
        else if((map[curX][curY].equals("S") && curDir ==1) || (map[curX][curY].equals("L") && curDir == 2) || (map[curX][curY].equals("R") && curDir == 3)) {
            nextRow = curX -1;
            nextDir = 1;
        }
        else if((map[curX][curY].equals("S") && curDir ==2) || (map[curX][curY].equals("L") && curDir == 0) || (map[curX][curY].equals("R") && curDir == 1)) {
            nextCol = curY +1;
            nextDir = 2;
        }
        else {
            nextCol =  curY-1;
            nextDir = 3;
        }

        if(checkEndpoint(nextRow, nextCol)) {
            if(nextDir == 0) {
                nextRow = 0;
            }
            else if(nextDir == 1) {
                nextRow = map.length-1;
            }
            else if(nextDir == 2) {
                nextCol = 0;
            }
            else {
                nextCol = map[nextRow].length-1;
            }
        }
        return new Point(nextRow, nextCol, nextDir);
    }

    private static boolean checkEndpoint(int nextRow, int nextCol) {
        return nextRow < 0 || nextRow >= map.length || nextCol < 0 || nextCol >= map[nextRow].length;
    }
    
    static int getCycle(int x, int y, int d){
        Queue<Point> Q = new LinkedList<>();
        Q.add(new Point(x,y,d));
        int cnt = 0;
        
        while(!Q.isEmpty()){
            Point tmp = Q.poll();
            int cx = tmp.x;
            int cy = tmp.y;
            int cd = tmp.d;
            
            if(chk[cx][cy][cd]){
                 return cnt;
            }
            
            chk[cx][cy][cd] = true;
            Point np = getCur(cx,cy,cd);
            Q.add(new Point(np.x, np.y, np.d));
            cnt++;
        }
        return cnt;
    }
    
    public int[] solution(String[] grid) {
        List<Integer> answer = new ArrayList<>();
        map = new String[grid.length][grid[0].length()];
        chk = new boolean[grid.length][grid[0].length()][4];
        for(int r=0; r<grid.length; r++){
            for(int c=0; c<grid[r].length(); c++){
                map[r][c] = String.valueOf(grid[r].charAt(c));
            }
        }
        
         for(int r=0; r<grid.length; r++){
            for(int c=0; c<grid[r].length(); c++){
                for(int d=0; d<4; d++){
                    if(!chk[r][c][d]){
                        
                    
                   int ans = getCycle(r,c,d);
                    answer.add(ans);
                    }
                    }
            }
        }
        
        Collections.sort(answer);
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}