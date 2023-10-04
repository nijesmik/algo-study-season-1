package pract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_bfs_2660_회장뽑기 {
	static class point {
		int x;
		int y;

		public point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static void bfs(int s) {
		Q = new LinkedList<>();
		chk[s]=1;
		cycle = 0;
		Q.offer(s);
		while(!Q.isEmpty()) {
			int qSize = Q.size();
			for(int qs=0; qs<qSize;qs++) {
				int tmp = Q.poll();
				for(int i=1; i<=N; i++) {
					if(graph[tmp][i]==1 && chk[i]==0) {
						chk[i]=1;
						Q.offer(i);
					}
				}
			}
			cycle++;
		}
		
	}

	static Queue<Integer> Q; 
	static int N;
	static int[][] graph;
	static int[] chk;
	static int cycle;
	static int[] arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		graph = new int[N+1][N+1];
		arr = new int[N+1];
		while(true) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			if(a==-1 && b==-1) break;
			
			graph[a][b]=1;
			graph[b][a]=1;
		}
		
		for(int i=1; i<=N;i++) {
			chk = new int[N+1];
			bfs(i);
			arr[i] = cycle-1;
		}
		
		int cnt=0;
		int min=Integer.MAX_VALUE;
		for(int i=1; i<=N;i++) {
			if(min>arr[i]) min=arr[i];
		}
		for(int i=1; i<=N;i++) {
			if(min==arr[i]) cnt++;
		}
		System.out.println(min+" "+cnt);
		for(int i=1; i<=N;i++) {
			if(arr[i]==min)
			System.out.print(i+" ");
		}
		System.out.println();
	}
}