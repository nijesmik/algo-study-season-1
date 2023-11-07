package ssafybackup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_벽부수고이동하기 {
	static int N,M;
	static int[][] map;
	static int[][][] chk;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static int min;
	
	static class Point{
		int x;
		int y;
		int z;
		public Point(int x, int y, int z) {
			this.x =x;
			this.y = y;
			this.z =z;
		}
	}
	static int bfs() {
		Queue<Point> Q = new LinkedList<>();
		Q.add(new Point(0,0,0));
		chk[0][0][0]=1;
		while(!Q.isEmpty()) {
			Point tmp = Q.poll();
			if(tmp.x==N-1 && tmp.y==M-1) {
				return chk[tmp.x][tmp.y][tmp.z];
			}
			for(int i=0; i<4; i++) {
				int nx = tmp.x + dx[i];
				int ny = tmp.y + dy[i];
				if(nx>=0 && nx<N && ny>=0 && ny<M) {
					if(map[nx][ny]==0 && chk[nx][ny][tmp.z]==0) {
						chk[nx][ny][tmp.z] = chk[tmp.x][tmp.y][tmp.z]+1;
						Q.add(new Point(nx,ny,tmp.z));
					}else if(map[nx][ny]==1 && tmp.z==0) {
						chk[nx][ny][tmp.z+1] = chk[tmp.x][tmp.y][tmp.z]+1;
						Q.add(new Point(nx,ny,tmp.z+1));
					}
				}
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		chk = new int[N][M][2];
		for(int i=0; i<N; i++) {
			String temp = sc.next();
			for(int j=0; j<M; j++) {
				map[i][j]=temp.charAt(j)-'0';
			}
		}
		System.out.println(bfs());
		
	}
}
