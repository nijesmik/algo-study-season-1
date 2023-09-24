package DATE0924;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BAEK1012 {
	static int[][] bd;
	static int M;
	static int N;
	public static void main(String[] args) throws Exception {

		StringBuilder sb = new StringBuilder();
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		int T = Integer.parseInt(bf.readLine());
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(bf.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			
			bd = new int[M][N];
			for(int i=0;i<K;i++) {
				st = new StringTokenizer(bf.readLine());
				bd[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
			}
			
			int cnt =0;
			for(int i=0;i<M;i++) {
				for(int j=0;j<N;j++) {
					if(bd[i][j]==1) {
						bfs(i,j);
						cnt++;
					}
				}
			}
			sb.append(cnt).append("\n");
		}

		System.out.println(sb);
	
	}
	
	static int[] dx = new int[] {0,0,1,-1};
	static int[] dy = new int[] {1,-1,0,0};
	public static void bfs(int x, int y) {
		
		Queue<int[]> qu = new ArrayDeque<>();
		
		qu.offer(new int[] {x,y});
		bd[x][y] = -1;
		
		while(!qu.isEmpty()) {
			int[] tmp = qu.poll();
			
			for(int i=0;i<4;i++) {
				int nx = tmp[0]+dx[i];
				int ny = tmp[1]+dy[i];
				
				if(nx<0||nx>=M||ny<0||ny>=N||bd[nx][ny]!=1)continue;
				
				qu.offer(new int[] {nx,ny});
				bd[nx][ny] = -1;

			}
			
			
			
		}
		
	}
	

}
