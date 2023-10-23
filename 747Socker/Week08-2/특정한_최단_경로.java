package pract2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;


public class bj_다익스트라_특정한최단경로_1504 {
	static class Edge implements Comparable<Edge>{
		public int vex;
		public int cost;
		Edge(int vex, int cost){
			this.vex = vex;
			this.cost = cost;
		}
		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.cost-o.cost;
		}
		
	}
	static int n,m;
	static ArrayList<ArrayList<Edge>> graph;
	static int[] dis;
	static int v1,v2;
	static int INF;
	public int solution(int s, int e) {
		Arrays.fill(dis, INF);
		PriorityQueue<Edge> pQ = new PriorityQueue<>();
		pQ.offer(new Edge(s,0));
		dis[s]=0;
		while(!pQ.isEmpty()) {
			Edge tmp = pQ.poll();
			int now = tmp.vex;
			int nowCost=tmp.cost;
			if(nowCost>dis[now]) continue;
			for(Edge ob : graph.get(now)) {
				if(dis[ob.vex]>nowCost+ob.cost) {
					dis[ob.vex]=nowCost+ob.cost;
					pQ.offer(new Edge(ob.vex, nowCost+ob.cost));
				}
			}
		}
		return dis[e];
	}
	
	public static void main(String[] args) {
		INF = 1 << 29;
		bj_다익스트라_특정한최단경로_1504 T = new bj_다익스트라_특정한최단경로_1504();
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		graph = new ArrayList<ArrayList<Edge>>();
		for(int i=0; i<=n; i++) {
			graph.add(new ArrayList<Edge>());
		}
		dis = new int[n+1];
		Arrays.fill(dis, INF);
		
		for(int i=0; i<m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			graph.get(a).add(new Edge(b,c));
			graph.get(b).add(new Edge(a,c));
		}
		v1=sc.nextInt();
		v2=sc.nextInt();
		int ans = T.solution(1, v1)+T.solution(v1, v2)+T.solution(v2, n);
		int ans2 = T.solution(1, v2)+T.solution(v2, v1)+T.solution(v1, n);
		if(ans >= INF && ans2>=INF) System.out.println(-1);
		else System.out.println(Math.min(ans, ans2));
		
	}
}
