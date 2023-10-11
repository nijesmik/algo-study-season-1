package pract2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int[][] arr;
	static int[] dis;
	static int N;
	static int M;
	static int dir;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static boolean flag;
	static boolean chk;
	static void nBlank(int x, int y, int dir) {
		int nx=0;
		int ny=0;
		if(dir==0) {
			nx=x+1;
			ny=y;
		}else if(dir==1) {
			nx=x;
			ny=y-1;
		}else if(dir==2) {
			nx=x-1;
			ny=y;
		}else if(dir==3) {
			nx=x;
			ny=y+1;
		}
		if(nx>0 && nx<N-1 && ny>0 && ny<M-1 && arr[nx][ny]!=1) {
			clean(nx,ny,dir);
		}else if(nx>=0 && nx<N && ny>=0 && ny<M && arr[nx][ny]==1){
			flag=true;
			return;
		}
	}

	static void blank(int x, int y, int dir) {
		dir = dir-1;
		if(dir==-1) dir=3;
		int nx = 0;
		int ny = 0;
		if(dir==0) {
			nx=x-1;
			ny=y;
		}
		else if(dir==1) {
			nx=x;
			ny=y+1;
		}else if(dir==2) {
			nx=x+1;
			ny=y;
		}else if(dir==3) {
			nx=x;
			ny=y-1;
		}
		
		if(nx >= 0 && nx < N && ny >= 0 && ny < M && arr[nx][ny]==0) clean(nx,ny,dir);
		else clean(x,y,dir);
	}

	static void clean(int x, int y, int dir) {
		chk =false;
		if(flag) return;
		if (arr[x][y] == 0) {
			arr[x][y] = 2;
		}

		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (nx >= 0 && nx < N && ny >= 0 && ny < M && arr[nx][ny] == 0) {
				chk = true;
			}
		}
		if(chk) blank(x,y,dir);
		else if(!chk) nBlank(x,y,dir);

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N][M];
		dis = new int[3];
		flag=false;
		for (int i = 0; i < 3; i++) {
			dis[i] = sc.nextInt();
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		int cnt = 0;
		clean(dis[0], dis[1], dis[2]);
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(arr[i][j]==2) cnt++;
			}
		}
		System.out.println(cnt);
	}
}