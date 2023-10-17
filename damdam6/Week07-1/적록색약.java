package DATE1011;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class BAEK10026 {
	
	static int n;
	static char[][] arr;
	static int[][] vt;
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		n = Integer.parseInt(bf.readLine());

		arr = new char[n][n];
		
		String str;
		for(int i=0;i<n;i++) {
			str = bf.readLine();
			for(int j=0;j<n;j++) {
				arr[i][j] = str.charAt(j);
			}
		}
		
		vt = new int[n][n];
		
		int cnt0 =0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(vt[i][j]==1)continue;
				bfs(i,j,0);
				cnt0++;
			}
		}
		
		vt = new int[n][n];
		int cnt1 =0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(vt[i][j]==1)continue;
				bfs(i,j,1);
				cnt1++;
			} 
		}

		System.out.println(cnt0+" "+cnt1);
		
	}
	
	static int[] dx = new int[] {0,0,1,-1};
	static int[] dy = new int[] {1,-1,0,0};
	
	static public void bfs(int x, int y, int can) {
		
		Deque<node> qu = new ArrayDeque<>();
		
//		for(int i=0;i<n;i++) {
//			System.out.println(Arrays.toString(vt[i]));
//		}
		qu.add(new node(x,y));
		vt[x][y] = 1;
		
		node tmp;
		while(!qu.isEmpty()) {
			tmp = qu.poll();

			//System.out.println(qu.toString());
			for(int i=0;i<4;i++) {
				int nx = tmp.x + dx[i];
				int ny = tmp.y + dy[i];

				if(nx<0||nx>=n||ny<0||ny>=n)continue;
				if(vt[nx][ny]==1)continue;
				//can이 0이면 다 구분
				//can이 1이면 적녹 구분x
				if(arr[nx][ny] == arr[tmp.x][tmp.y]) {

					qu.add(new node(nx, ny));
					vt[nx][ny] = 1;
				}else if(can ==1 &&arr[tmp.x][tmp.y] == 'R' &&arr[nx][ny] == 'G' ) {

					qu.add(new node(nx, ny));
					vt[nx][ny] = 1;
				}else if(can ==1 &&arr[tmp.x][tmp.y] == 'G' &&arr[nx][ny] == 'R') {
					qu.add(new node(nx, ny));
					vt[nx][ny] = 1;
				}
			}

		}

		
	}
	static public class node{
		int x;
		int y;
		node(int x, int y){
			this.x = x;
			this.y = y;
		}
		
	public	String toString() {
			
		return x+" "+y;
		
		}
	}

}
