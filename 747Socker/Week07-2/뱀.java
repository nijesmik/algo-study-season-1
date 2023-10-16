package pract2;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_re_뱀_3190 {
	
	static class Point{
		int x;
		int y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	static Deque<String> dq = new ArrayDeque<>();
	static int N;
	static int K;
	static int L;
	static int[][] map;
	static int dx[]= {0,-1,1,0};
	static int dy[]= {1,0,0,-1};
	static boolean chk[][];
	static boolean flag;
	static Queue<Point> fQ;
	static int[][] 	arr;
	static int ans;
	static int sec;
	static int cx;
	static int cy;
	static int dir;
	
	static void move(int X, char C, int x, int y) {
		if(!flag) return;
		map[x][y]=2;
		while(sec<X) {
			if(dir==0) {
				int nx = x--;
				if(nx<0 || map[nx][y]==2) {
					flag =false;
					return;
				}else {
					if(map[nx][y]==1) {
						map[nx][y]=2;
					}else {
						map[x][y]=0;
					}
				}
				cx = nx;
				cy = y;
			}else if(dir ==1) {
				int ny = y++;
				if(ny>=N || map[x][ny]==2) {
					flag =false;
					return;
				}else {
					if(map[x][ny]==1) {
						map[x][ny]=2;
					}else {
						map[x][y]=0;
					}
				}
				cx = x;
				cy = ny;

			}else if(dir ==2) {
				int nx = x++;
				if(nx>=N || map[nx][y]==2) {
					flag = false;
					return;
				}else {
					if(map[nx][y]==1) {
						map[nx][y]=2;
					}else {
						map[x][y]=0;
					}
				}
				cx = nx;
				cy = y;

			}else if(dir ==3) {
				int ny = y--;
				if(ny<0 || map[x][ny]==2) {
					flag = false;
					return;
				}else {
					if(map[x][ny]==1) {
						map[x][ny]=2;
					}else {
						map[x][y]=0;
					}
				}
				cx = x;
				cy = ny;

			}
			sec++;
		}
		if(C=='L') dir = (dir+3)%4;
		else if(C=='D') dir =(dir+1)%4;
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
			flag = false;
		N = sc.nextInt();
		K = sc.nextInt();
		map = new int[N][N];
		for(int i=0; i<K; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			map[a-1][b-1]=1;
		}
		flag = true;
		dir =1;
		sec = 0;
		L = sc.nextInt();
		for(int l=0; l<L; l++) {
			int X = sc.nextInt();
			char C = sc.next().charAt(0);
			if(flag) 
				move(X,C,cx,cy);
			
		}
		System.out.println(sec);
	}
}