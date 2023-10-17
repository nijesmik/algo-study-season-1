package pract;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_bfs_빙산 {

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int[][] arr;
	static int N;
	static int M;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int[][] chk;
	static int[][] vst;
	static int answer=0;
	static Queue<Point> Q;
	static boolean flag;
	static void bfs(int x, int y) {
		Q = new LinkedList<>();
		chk[x][y] = 1;
		Q.offer(new Point(x, y));
		while (!Q.isEmpty()) {
			Point tmp = Q.poll();
			for(int d=0; d<4; d++) {
				int nx = tmp.x+dx[d];
				int ny = tmp.y+dy[d];
				if(nx>=0 && nx<N && ny>=0 && ny<M && chk[nx][ny]==0 && arr[nx][ny]>0) {
					chk[nx][ny]=1;
					Q.offer(new Point(nx,ny));
				}
			}
		}
		
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		flag = true;
		while (flag) {
			int cnt = 0;
			chk = new int[N][M];
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(arr[i][j]>0 && chk[i][j]==0) {
						bfs(i,j);
						cnt++;
					}
				}
			}
			if(cnt>=2) {
				answer=-1;
				break; 
			}
			
			flag = false;
			vst = new int[N][M];
			for (int i = 1; i < N-1; i++) {
				for (int j = 1; j < M-1; j++) {
					if(arr[i][j]!=0) vst[i][j]=1;
					if(arr[i][j]>0) {
						int count = 0;
						for(int d=0; d<4; d++) {
							int nx = i + dx[d];
							int ny = j + dy[d];
							if(arr[nx][ny]==0 && vst[nx][ny]==0) {
								count++;
							}
						}
						arr[i][j] -= count;
						if(arr[i][j]<0) arr[i][j]=0;
					}
				}
			}
			
			for(int i=0 ;i<N; i++) {
				for(int j=0; j<M; j++) {
					if(arr[i][j]>0) flag = true;
				}
			}
			cnt = 0;
			chk = new int[N][M];
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(arr[i][j]>0 && chk[i][j]==0) {
						bfs(i,j);
						cnt++;
					}
				}
			}
			if(cnt>=2) break;
			else answer++;
		}
		if(!flag) answer = -1;
		System.out.println(answer+1);
	}
}