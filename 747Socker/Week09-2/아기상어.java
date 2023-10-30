package pract2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class bj_re_아기상어 {
	static class bShrk{
		int x;
		int y;
		int dis;
		int size;
		public bShrk(int x, int y, int dis,int size) {
			this.x = x;
			this.y = y;
			this.dis =dis;
			this.size = size;
		}
	}
	
	static int ans,eat,total;
	static int N,M;
	static int[][] map;
	static bShrk bs;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,-1,0,1};
	static boolean[][] chk;
	
	static void move() {
		PriorityQueue<bShrk> Q = new PriorityQueue<>((o1,o2) ->
		o1.dis != o2.dis ? Integer.compare(o1.dis, o2.dis) : (o1.x != o2.x ? Integer.compare(o1.x, o2.x) : Integer.compare(o1.y, o2.y))
	    );
		chk = new boolean[N][N];
		
		Q.add(bs);
		chk[bs.x][bs.y] = true;
		
		while(!Q.isEmpty()) {
			bShrk tmp = Q.poll();
			
			for(int d=0; d<4; d++) {
				int nx = tmp.x + dx[d];
				int ny = tmp.y + dy[d];
				
				if(nx>=0 && ny>=0 && nx<N && ny<N && !chk[nx][ny] && map[nx][ny]<=tmp.size) {
					Q.add(new bShrk(nx,ny,tmp.dis+1, tmp.size));
					chk[nx][ny]=true;
				}
			}
			if(Q.peek() != null) {
				bShrk P = Q.peek();
				if(map[P.x][P.y] < P.size && map[P.x][P.y]>0) {
					eat++;
					if(eat==P.size) {
						P.size++;
						eat=0;
					}
					map[P.x][P.y] = 0;
					Q.clear();
					Q.add(new bShrk(P.x,P.y,0,P.size));
					ans+=P.dis;
					chk = new boolean[N][N];
					chk[P.x][P.y] = true;
					
				}
			}
			
		}
		}
	
	
	public static void main(String[] args) {
		Scanner sc =  new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		
		
		for(int i=0; i<N;i++	) {
			for(int j=0; j<N; j++) {
				int inp = sc.nextInt();
				map[i][j]= inp;
				if(inp == 9) {
					bs = new bShrk(i,j,0,2);
					map[i][j]=0;
				}else if(inp != 9 && inp >0) {
					M++;
				}
			}
		}
		
		if(M==0) {
			System.out.println(0);
		}else {
			move();
			System.out.println(ans);
		}
	}
}
