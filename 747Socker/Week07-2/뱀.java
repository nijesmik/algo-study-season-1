package pract2;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_뱀_3190 {
	static int N,K;
	static int[][] map;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,-1,0,1};
	static int dir;
	
	static class Point{
		int x;
		int y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static Deque<Point> dq = new ArrayDeque<>();
	static Queue<Point> moves = new LinkedList<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		map = new int[N][N];
		dir = 3;
		dq.offer(new Point(0,0));
		map[0][0]=2;
		
		for(int i=0; i<K; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			map[a-1][b-1] = 1;
		}
		
		int L = sc.nextInt();
		for(int i=0; i<L; i++) {
			int x = sc.nextInt();
			int ic;
			char c = sc.next().charAt(0);
			if(c=='L') ic = 0;
			else ic = 1;
			
			moves.add(new Point(x,ic));
		}
		int sec = 0;
		int X = moves.peek().x;
		int C = moves.poll().y;
		
		while(true) {
			sec++;
			int nx = dq.peekFirst().x+dx[dir];
			int ny = dq.peekFirst().y+dy[dir];
			
			if(nx < 0 || nx>= N || ny<0 || ny>=N || map[nx][ny]==2) {
				break;
			}
			
			if(map[nx][ny]==1) {
				map[nx][ny]=2;
				dq.offerFirst(new Point(nx,ny));
			}else {
				map[nx][ny]=2;
				dq.offerFirst(new Point(nx,ny));
				map[dq.peekLast().x][dq.pollLast().y]=0;
			}
			if(sec == X) {
				if(C==0) {
					dir = (dir+1)%4;
				}else {
					dir = (dir+3)%4;
				}
				if(!moves.isEmpty()) {
					X = moves.peek().x;
					C = moves.poll().y;
				}
			}
		}
		System.out.println(sec);
		
	}
}