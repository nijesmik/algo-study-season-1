package pract;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_re_숨바꼭질3 {
	
	static class Point{
		int x;
		int time;
		public Point(int x, int time) {
			this.x = x;
			this.time = time;
		}
		
	}
	
	static int N;
	static int M;
	static int[] dis = {2,-1,1};
	static int[] chk;
	static Queue<Point> Q = new LinkedList<>();

	static int nx;
	static int nt;
	static int answer;
	
	static void bfs(int start, int end) {
		chk = new int[100001];
		chk[start]=1;
		Q.offer(new Point(start, 0));
		while(!Q.isEmpty()) {
			int len = Q.size();
			for(int i=0; i<len; i++) {
				Point tmp = Q.poll();
				for(int j=0; j<3; j++) {
					if(j==0) {
						nx = tmp.x*dis[j];
						nt = tmp.time;
					}else {
						nx = tmp.x+dis[j];
						nt = tmp.time+1;
					}
					if(nx==end) {
						answer = nt;
						return;
					}
					if(nx>=0 && nx<=100000 && chk[nx]==0) {
						chk[nx]=1;
						Point print = new Point(nx,nt);
						Q.add(print);
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		bfs(N,M);
		if(N==M) answer = 0;
		System.out.println(answer);
	}
}