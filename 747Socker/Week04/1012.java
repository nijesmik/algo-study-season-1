import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static class Point {
		int x;
		int y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	
	static void bfs(int x, int y) {
		Queue<Point> Q = new LinkedList<Point>();
		Q.offer(new Point(x,y));
		while(!Q.isEmpty()){
			Point tmp = Q.poll();
			for(int i=0; i<4; i++) {
				int nx = tmp.x + dx[i];
				int ny = tmp.y + dy[i];
				if(nx>=0 && nx<M && ny>=0 && ny<N && arr[nx][ny]==1) {
					arr[nx][ny]=0;
					Q.offer(new Point(nx,ny));
				}
			}
			
		}
	}
	
	static int[][] arr;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static int ans;
	static int N, M;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			M = sc.nextInt();
			N = sc.nextInt();
			int K = sc.nextInt();
			arr = new int[M][N];
			ans = 0;
			for(int i=0; i<K;i++) {
				int X = sc.nextInt();
				int Y = sc.nextInt();
				arr[X][Y]=1;
			}
			
			
			for(int i=0; i<M; i++) {
				for(int j=0; j<N; j++) {
					if(arr[i][j]==1) {
						bfs(i,j);
						ans++;
					}
				}
			}
			
			System.out.println(ans);
			
		}
	}
}
