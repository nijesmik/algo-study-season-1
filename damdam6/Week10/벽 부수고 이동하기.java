package DATE1102;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BAEK2206 {

	static int N;
	static int M;
	static block[][] arr;

	public static void main(String[] args) throws Exception {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new block[N][M];
		String tmpString;
		for (int i = 0; i < N; i++) {
			tmpString = bf.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = new block(i, j, tmpString.charAt(j) - '0');
			}
		}
		
		System.out.println(bfs());
	}

	static int bfs() {

		int nx = 0;
		int ny = 0;
//		vt[][] vtArr = new vt[N][M];
//		for(int i=0;i<N;i++) {
//			for(int j = 0 ;j<M;j++) {
//				vtArr[i][j] = new vt();
//			}
//		}
		int[][] vtArr = new int[N][M];
		for(int i=0;i<N;i++) {
			Arrays.fill(vtArr[i], Integer.MAX_VALUE);
		}

		PriorityQueue<block> qu = new PriorityQueue<>();

		block tmp = new block(0, 0, arr[0][0], false);
//		vtArr[0][0].stageFalse = 1;
//		//vtArr[0][0].stageTrue = 1;

		qu.add(tmp);

		while (!qu.isEmpty()) {
			
//			for (int i = 0; i < vtArr.length; i++) {
//				System.out.println(Arrays.toString(vtArr[i]));
//			}
			
			tmp = qu.poll();

			//System.out.println(tmp);
//			if (tmp.x == N - 1 && tmp.y == M - 1) {
//				return tmp.stage;
//			}

			for (int i = 0; i < 4; i++) {
				nx = tmp.x + dx[i];
				ny = tmp.y + dy[i];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;
				
				if (arr[nx][ny].wall == 1 && !tmp.destroy && tmp.stage + 1 < vtArr[nx][ny]) {
					vtArr[nx][ny]= tmp.stage + 1;
					qu.add(new block(nx, ny, tmp, true));
				}
				if (arr[nx][ny].wall == 1)continue;
				
				if(tmp.destroy && tmp.stage + 1 < vtArr[nx][ny]) {
					vtArr[nx][ny] = tmp.stage +1;
					qu.add(new block(nx, ny, tmp, false));
				}else if(!tmp.destroy && tmp.stage + 1 < vtArr[nx][ny]){
					vtArr[nx][ny] = tmp.stage +1;
					qu.add(new block(nx, ny, tmp, false));
				}
				
				
				
			}

		}
		
		int min = vtArr[N-1][M-1];

		if(min == Integer.MAX_VALUE)min = -1;
		return min;
	}

	static int[] dx = new int[] { 1, -1, 0, 0 };
	static int[] dy = new int[] { 0, 0, 1, -1 };

	static class block implements Comparable<block>{
		int stage;
		int x, y;
		int wall;
		boolean destroy;

		block(int x, int y, int wall) {
			this.x = x;
			this.y = y;
			this.wall = wall;
			this.destroy = false;
		}

		block(int x, int y, block b, boolean tf) {
			this.x = x;
			this.y = y;
			this.stage = b.stage + 1;
			if (tf) {
				this.destroy = true;
			} else {
				this.destroy = b.destroy;
			}
		}

		@Override
		public String toString() {
			return "[stage=" + stage + ", x=" + x + ", y=" + y + " des= " + destroy+ "]";
		}

		@Override
		public int compareTo(block o) {
			if(this.stage > o.stage)return 1;
			if(this.stage < o.stage)return -1;
			return 0;
		}

	}
	
	static class vt{
		int stageTrue;
		int stageFalse;
		vt(){
			stageTrue = Integer.MAX_VALUE;
			stageFalse = Integer.MAX_VALUE;
		}
		@Override
		public String toString() {
			return "[True=" + stageTrue + "| False=" + stageFalse + "]";
		}
		
		
	}

}
