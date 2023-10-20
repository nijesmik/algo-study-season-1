package nijesmik.Week08;
import java.util.*;

public class 우주신과의_교감 {
	static class Edge implements Comparable<Edge> {
		int a, b;
		long dist;
		Edge (int from, int to, long d) {
			a = from;
			b = to;
			dist = d;
		}
		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.dist, o.dist);
		}
	}
	static long powdist(int[] a, int[] b) {
		long x = Math.abs(a[0] - b[0]);
		long y = Math.abs(a[1] - b[1]);
		return x*x + y*y;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(), m = sc.nextInt();
		int[][] position = new int[n+1][2];
		parent = new int[n+1];
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
			position[i][0] = sc.nextInt();
			position[i][1] = sc.nextInt();
			for (int j = 1; j < i; j++) {
				pq.add(new Edge(i, j, powdist(position[i], position[j])));
			}
		}
		for (int i = 0; i < m; i++) {
			pq.add(new Edge(sc.nextInt(), sc.nextInt(), 0));
		}
		double ans = 0;
		int cnt = 1;
		while (cnt < n) {
			Edge cur = pq.poll();
			int p1 = find(cur.a), p2 = find(cur.b);
			if (p1 == p2) continue;
			parent[p2] = p1;
			ans += Math.sqrt(cur.dist);
			cnt++;
		}
		System.out.printf("%.2f\n", ans);
	}
	static int[] parent;
	static int find(int i) {
		if (parent[i] != i)
			parent[i] = find(parent[i]);
		return parent[i];
	}
}