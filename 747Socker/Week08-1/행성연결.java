package pract2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class bj_행성연결_16398 {
	static int N,M;
	static int[][] map;
	static int[] unf;
	static long answer;
	public static int find(int v) {
		if (v == unf[v])
			return v;
		else
			return unf[v] = find(unf[v]);
	}

	public static void union(int a, int b) {
		int fa = find(a);
		int fb = find(b);
		if (fa != fb)
			unf[fa] = fb;
	}

	static class Edge implements Comparable<Edge> {
		int v1;
		int v2;
		int cost;

		public Edge(int v1, int v2, int cost) {
			super();
			this.v1 = v1;
			this.v2 = v2;
			this.cost = cost;
			{
			}
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.cost - o.cost;
		}


	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][N];
		unf = new int[N + 1];
		ArrayList<Edge> arr = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			unf[i] = i;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int c = sc.nextInt();
				map[i][j] = c;
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = i; j < N; j++) {
				if (map[i][j] != 0)
					arr.add(new Edge(i, j, map[i][j]));
			}
		}
		Collections.sort(arr);
		System.out.println(Arrays.deepToString(args));
		for(Edge ob: arr) {
			int fv1 = find(ob.v1); 
			int fv2 = find(ob.v2);
			if(fv1!=fv2) {
				answer+=ob.cost;
				union(ob.v1,ob.v2);
			}
		}
		System.out.println(answer);
	}
}
