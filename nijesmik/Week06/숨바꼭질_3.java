package nijesmik.Week06;
import java.util.*;

public class 숨바꼭질_3 {
	static class Node implements Comparable<Node>{
		int idx, time;
		Node(int i, int t) {
			idx = i;
			time = t;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.time, o.time);
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int start = sc.nextInt(), end = sc.nextInt();
		int[] delta = {0, -1, 1};
		boolean[] visit = new boolean[100001];
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.add(new Node(start, 0));
		while (!q.isEmpty()) {
			Node cur = q.poll();
			if (visit[cur.idx]) continue;
			visit[cur.idx] = true;
			if (cur.idx == end) {
				System.out.println(cur.time);
				break;
			}
			delta[0] = cur.idx;
			for (int i = 0; i < 3; i++) {
				int next = cur.idx + delta[i];
				if (next < 0 || next > 100000) continue;
				int time = cur.time;
				if (i > 0) time++;
				q.add(new Node(next, time));
			}
		}
	}
}