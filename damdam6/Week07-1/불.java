package DATE1013;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;


public class BAEK5427_RE {
	
	
	static int w;
	static int h;
	static char[][] bd;
	
	static block begin;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		
		int T = Integer.parseInt(bf.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			
			
			st = new StringTokenizer(bf.readLine());
			
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			bd = new char[h][w];
			
			vt = new int[h][w];
			
			for(int i=0;i<h;i++) {
				String str = (bf.readLine());
				for(int j=0;j<w;j++) {
					bd[i][j] = str.charAt(j);
					
					//출발지와 불 위치 따로 저장
					if(bd[i][j] == '@') {
						begin = new block(i, j,0);
					}else if(bd[i][j] == '*') {
						qu.add(new block(i,j,0));

					}
				}
				
			}
			
			
			mintime = Integer.MAX_VALUE;
			bfs();
			if(mintime == Integer.MAX_VALUE) {
				sb.append("IMPOSSIBLE").append("\n");
			}else {
				sb.append(mintime).append("\n");
			}
			qu.clear();
			
		}
		System.out.println(sb);
	}
	
	static Deque<block> qu = new ArrayDeque<>();
	static int[] dx = new int[] {1,-1,0,0};
	static int[] dy = new int[] {0,0,1,-1};
	
	static void fireBfs(int time) {
		
		//System.out.println(qu.toString());
		block tmp;
		while(!qu.isEmpty() && qu.peek().time <= time ) {
			
			tmp = qu.poll();
			
			
			
			for(int k=0;k<4;k++) {
				int nx = tmp.x + dx[k];
				int ny = tmp.y + dy[k];
				
				if(nx<0 || ny <0|| nx>=h || ny>=w ||bd[nx][ny] == '#'||bd[nx][ny]=='*')continue;
				bd[nx][ny] = '*';	
				
				
				qu.add(new block(nx, ny, tmp.time+1));
			}
			
		}
		
		
		
	}
	
	static int mintime;
	static int[][] vt;
	static public void bfs() {
		
		
		Deque<block> qugo = new ArrayDeque<>();
		qugo.add(new block(begin.x, begin.y, 0));
		vt[begin.x][begin.y] =1;
		block tmp;
		
		while(!qugo.isEmpty()) {
			
			tmp = qugo.poll();

			if(tmp.time >= mintime) {
				continue;
			}
			
			fireBfs(tmp.time);

			
			
//			System.out.println("tmptime ====== "+tmp.time);
//			for(int i=0;i<h;i++) {
//				System.out.println(Arrays.toString(bd[i]));
//			}
//			System.out.println();
			
			for(int i=0;i<4;i++) {
				
				
				int nx = tmp.x + dx[i];
				int ny = tmp.y + dy[i];
				
				
				//범위 밖으로 나가면 탈출
				if(nx<0||ny<0||nx>=h||ny>=w) {
					mintime = Math.min(mintime, tmp.time+1);
					continue;
				}
				
				//못 가는 위치
				if(bd[nx][ny]=='#' || bd[nx][ny] == '*' ||vt[nx][ny]==1)continue;
				vt[nx][ny] = 1;
				qugo.add( new block(nx, ny, tmp.time+1));
				
			}
			
		}
		
	}
	
	
	static class block{
		int x;
		int y;
		int time;
		
		public block(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
		
		public String toString() {
			return "time "+time;
		}
	}

}
