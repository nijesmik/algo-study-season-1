import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 로봇_청소기 {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int sr = Integer.parseInt(st.nextToken());
        int sc = Integer.parseInt(st.nextToken());
        int sd = Integer.parseInt(st.nextToken());
        visited = new boolean[N][M];
        map = new int[N][M];
        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        int answer = 1;
        boolean flag = true;
        while(flag) {
            visited[sr][sc] = true; // 청소했으면 방문처리
            
            boolean check = false; // 주변 4칸중 청소되지 않은 빈칸이 있나없나 판단하는
            for(int i = 0; i < 4; i++) {
                sd = (sd+3)%4;
                int nr = sr + dr[sd];
                int nc = sc + dc[sd];
                
                if(map[nr][nc]==1) {
                    continue;
                }
                
                //청소되지 않은 빈칸이 있는 경우
                if(!visited[nr][nc]) {
                    answer++;
                    sr = nr;
                    sc = nc;
                    check = true;
                    break;
                }
                
            }
            if(!check) {
                sr += dr[(sd+2)%4];
                sc += dc[(sd+2)%4];
                if(map[sr][sc]==1) flag = false; 
            }
        }
        System.out.println(answer);
        
    }
}