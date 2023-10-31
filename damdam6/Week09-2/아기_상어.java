package DATE1026;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BAEK16236 {
	
	static int[][] sea;
	static int N;
	
	public static void main(String[] args) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(bf.readLine());
		sea = new int[N][N];
		;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < N; j++) {
				sea[i][j] = Integer.parseInt(st.nextToken());
				if(sea[i][j] == 9) {
					sea[i][j] = 0;
					baby = new shark(i,j);
				}
			}
		}
		
		while(bfs()) {
			
		}
		System.out.println(baby.time);
		
		
	}
	
	static shark baby;
	static int[] dx = new int[] {-1,0,0,1};
	static int[] dy = new int[] {0, -1, 1, 0};
	static boolean bfs() {
		
		int[][] vt = new int[N][N];
		PriorityQueue<pos> qu = new PriorityQueue<>();
		
		qu.add(new pos(baby.x, baby.y, 0));
		vt[baby.x][baby.y] = 1;
		
		pos tmp;
		while(!qu.isEmpty()) {
			
			tmp = qu.poll();

			if(sea[tmp.x][tmp.y] != 0 && sea[tmp.x][tmp.y] < baby.size ) {
				sea[tmp.x][tmp.y] = 0;
				baby.eat(tmp.x, tmp.y, tmp.sec);
				return true;
			}
			
			for(int i=0;i<4;i++) {
				
				int nx = tmp.x+dx[i];
				int ny = tmp.y+dy[i];
				
				if(nx<0 || ny < 0 || nx >= N || ny >= N || vt[nx][ny] == 1)continue;
				
				if(sea[nx][ny] > baby.size) continue;		
				
				
				qu.add(new pos(nx, ny, tmp.sec+1));
				vt[nx][ny] = 1;
				
			}
			
		}
		
		return false;
	}
	
	static class shark{
		int x;
		int y;
		int time;
		int size;
		int forSizeUp;
		public shark(int x, int y) {
			this.x = x;
			this.y = y;
			this.time = 0;
			this.size = 2;
			this.forSizeUp = 2;
		}
		
		public void eat(int x, int y, int sec) {
			this.x = x;
			this.y = y;
			
			this.time += sec;
			forSizeUp--;
			if(forSizeUp==0) {
				size++;
				forSizeUp = size;
			}
		}
		
	}
	static class pos implements Comparable<pos>{
		int x, y, sec;
		
		pos(int x, int y, int sec){
			this.x = x;
			this.y  = y;
			this.sec = sec;
		}

		@Override
		public int compareTo(pos o) {
			
			if(this.sec < o.sec) {
				return -1;
			}else if(this.sec > o.sec) {
				return 1;				
			}
			
			
			if(this.x < o.x) {
				return -1;
			}else if(this.x > o.x) {
				return 1;				
			}
			
			if(this.y < o.y) {
				return -1;
			}else if(this.y > o.y) {
				return 1;				
			}

			return 0;
		}
		
		
	}

}
