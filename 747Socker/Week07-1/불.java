package pract2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_re_bfs_불_5427 {
	
	static class Point{
		int x;
		int y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	static Point s;
	static int W;
	static int H;
	static char[][] map;
	static int cr;
	static int cc;
	static int dx[]= {-1,0,1,0};
	static int dy[]= {0,1,0,-1};
	static boolean chk[][];
	static boolean flag;
	static Queue<Point> fQ;
	static int[][] 	dis;
	
	static void bfs() {
		Queue<Point> Q = new LinkedList<>();
		Q.add(s);
		
		while(!Q.isEmpty()) {
			int len = fQ.size();
			for(int l=0; l<len; l++) {
				Point tmp = fQ.poll();
				for(int d=0; d<4; d++) {
					int nx = tmp.x+dx[d];
					int ny = tmp.y+dy[d];
					if(nx>=0 && nx<H && ny>=0 && ny<W && map[nx][ny]!='#' && map[nx][ny]!='*') {
						map[nx][ny]='*';
						fQ.add(new Point(nx,ny));
					}
				}
			}
			len = Q.size();
			for(int l=0; l<len; l++) {
				Point tmp = Q.poll();
				if((tmp.x==0)||(tmp.x==H-1)||(tmp.y==0)||(tmp.y==W-1)) {
					flag=true;
					cr = tmp.x;
					cc = tmp.y;
					return;
				}
				for(int d=0; d<4; d++) {
					int nx = tmp.x + dx[d];
					int ny = tmp.y + dy[d];
					if(nx>=0 && ny>=0 && nx<H && ny<W && map[nx][ny]=='.') {
						map[nx][ny]='@';
						dis[nx][ny]=dis[tmp.x][tmp.y]+1;
						Q.add(new Point(nx,ny));
					}
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc=0; tc<T; tc++) {
			flag = false;
		W = sc.nextInt();
		H = sc.nextInt();
		map = new char[H][W];
		dis = new int[H][W];
		fQ = new LinkedList<Point>();
		for(int i=0; i<H; i++) {
			String tmp = sc.next();
			for(int j=0; j<W; j++) {
				map[i][j]=tmp.charAt(j);
				if(map[i][j]=='*') fQ.add(new Point(i,j));
				if(map[i][j]=='@') {
					s = new Point(i,j);
				}
			}
		}
		bfs();
		if(flag) System.out.println(dis[cr][cc]+1);
		else System.out.println("IMPOSSIBLE");
	}
}
}