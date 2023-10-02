package pract;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_bfs_5567_결혼식 {
	static class point {
		int x;
		int y;

		public point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static void bfs() {
		int cycle = 1;
		while (!Q.isEmpty()) {
			int qSize = Q.size();
			for (int qs = 0; qs < qSize; qs++) {
				point tmp = Q.poll();
				for (int i = 1; i <=N; i++) {
					if(graph[tmp.y][i]==1 && chk[i]==0) {
						chk[i]=1;
						ans++;
					}
				}
				cycle++;
			}
			if(cycle>2) break;
		}

	}

	static Queue<point> Q; 
	static int N;
	static int M;
	static int[][] graph;
	static int[] chk;
	static int ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		graph = new int[N + 1][N + 1];
		chk = new int[N + 1];
		for (int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();

			graph[a][b] = 1;
			graph[b][a] = 1;
		}
		ans = 0;
		Q = new LinkedList<point>();
		chk[1]=1;
		for (int i = 1; i <= N; i++) {
			if (graph[1][i] == 1) {
				chk[i]=1;
				ans++;
				Q.offer(new point(1, i));
			}
		}
		bfs();
		System.out.println(ans);
	}
}