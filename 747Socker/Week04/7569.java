import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class Main {
	static class Point{
		public int x, y,z;
		Point(int z, int x, int y){
			this.x=x;
			this.y=y;
			this.z=z;
		}
	}
	static int[] dx = {-1,0,1,0,0,0};
	static int[] dy = {0,1,0,-1,0,0};
	static int[] dz = {0,0,0,0,1,-1};
	static int[][][] arr;
	static int[][][] dis;
	static int N;
	static int M;
	static int H;
	static int ans;
	static Queue<Point> que;
	
	static void bfs() {
		while(!que.isEmpty()) {
			Point tmp = que.poll();
			for(int i=0; i<6; i++) {
				int nx = tmp.x + dx[i];
				int ny = tmp.y + dy[i];
				int nz = tmp.z + dz[i];
				if(nx>=0 && ny>=0 && nz>=0 && nx<N && ny<M && nz<H && arr[nz][nx][ny]==0) {
					arr[nz][nx][ny]=1;
					dis[nz][nx][ny] = dis[tmp.z][tmp.x][tmp.y]+1;
					que.offer(new Point(nz,nx,ny));
				}
				      
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		N = sc.nextInt();
		H = sc.nextInt();
		arr = new int[H][N][M];
		dis = new int[H][N][M];
		que = new LinkedList<Point>();
		for(int h=0; h<H;h++) {
			for(int n=0; n<N; n++) {
				for(int m=0; m<M; m++) {
					arr[h][n][m]=sc.nextInt();
					
					if(arr[h][n][m]==1) que.add(new Point(h,n,m));
				}
			}
		}
		boolean flag=true; 
		bfs();
		for(int i=0; i<H; i++) {
			for(int j=0; j<N; j++) {
				for(int k=0; k<M; k++) {
					if(arr[i][j][k]==0) flag=false;
				}
			}
		}
		ans = Integer.MIN_VALUE;
		if(flag) {
			for(int i=0; i<H;i++) {
				for(int j=0; j<N; j++) {
					for(int k=0; k<M; k++) {
						ans = Math.max(ans, dis[i][j][k]);
					}
				}
			}
			System.out.println(ans);
		}else System.out.println(-1);
	}
}
