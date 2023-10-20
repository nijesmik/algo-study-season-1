package nijesmik.Week08;
import java.util.*;

public class 행성_연결 {
	static class Node implements Comparable<Node> {
		int idx, cost;
		Node(int i, int c) {
			idx = i; cost = c;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] cost = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				cost[i][j] = sc.nextInt();
			}
		}
		boolean[] visit = new boolean[n];
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.add(new Node(0, 0));
		int cnt = 0;
		long total = 0;
		while (cnt < n) {
			Node cur = q.poll();
			if (visit[cur.idx]) continue;
			visit[cur.idx] = true;
			cnt++;
			total += cur.cost;
			for (int i = 0; i < n; i++) {
				q.add(new Node(i, cost[cur.idx][i]));
			}
		}
		System.out.println(total);
	}
}