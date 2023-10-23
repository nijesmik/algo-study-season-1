package pract2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class 우주신1774 {
	static int N;
	static int M;
	static int[] unf;
	static ArrayList<Edge> arr;
	static double ans;
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
	
	static class Point{
		double x;
		double y;
		int n;
		Point(double x, double y, int n){
			this.x = x;
			this.y = y;
			this.n = n;
		}
	}
	
	static class Edge implements Comparable<Edge> {
		int v1;
		int v2;
		double cost;

		public Edge(int v1, int v2, double cost) {
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
			return cost < o.cost ? -1 :1;
		}


	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		unf = new int[N + 1];
		arr = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			unf[i] = i;
		}
		
		Point[] pts = new Point[N+1];
		for(int i=1; i<=N; i++) {
			double x = sc.nextDouble();
			double y = sc.nextDouble();
			pts[i] = new Point(x,y,i);
		}
		
		for(int i=0; i<M; i++) {
			int s = sc.nextInt();
			int e = sc.nextInt();
			union(s,e);
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=i+1; j<=N; j++) {
				double cost = Math.sqrt(Math.pow(pts[i].x-pts[j].x,2)+ Math.pow(pts[i].y-pts[j].y,2));
				arr.add(new Edge(pts[i].n,pts[j].n,cost));
			}
		}
		
		Collections.sort(arr);

		for(Edge ob: arr) {
			int fv1 = find(ob.v1); 
			int fv2 = find(ob.v2);
			if(fv1!=fv2) {
				ans+=ob.cost;
				union(ob.v1,ob.v2);
			}
		}
		System.out.printf("%.2f",ans);
	}
}

