import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 백준_1774_우주신과의교감 { // 크루스칼

	static class Node {
		int r, c;

		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	static class Edge {
		int from, to;
		double weight;

		public Edge(int from, int to, double weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

	}

	static int findset(int x) {
		if (parent[x] != x) {
			parent[x] = findset(parent[x]);
		}
		return parent[x];

	}

	static void union(int x, int y) {
		parent[findset(y)] = findset(x);

	}

	static double calWeight(Node n1, Node n2) {

		return Math.sqrt(Math.pow((n1.r - n2.r), 2) + Math.pow((n1.c - n2.c), 2));
	}

	static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		Node[] nodes = new Node[n + 1]; // 노드 번호가 1부터
		parent = new int[n + 1];
		for (int i = 1; i < parent.length; i++) {
			parent[i] = i; // union-find set

		}
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());

			nodes[i] = new Node(r, c);

		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			union(x, y);

		}

		List<Edge> edges = new ArrayList<>();
		for (int i = 1; i < n + 1; i++) { // i에서 j로 가는 간선의 가중치를 계산해서 간선리스트에 넣기
			for (int j = i + 1; j < n + 1; j++) {
				double tmp = calWeight(nodes[i], nodes[j]);
				edges.add(new Edge(i, j, tmp));

			}

		} // 간선 입력 끝

		Collections.sort(edges, (e1, e2) -> {
			if (e1.weight < e2.weight) {
				return -1;
			} else if (e1.weight > e2.weight) {
				return 1;
			}
			return 0;

		});

		int cnt = 0;
		for (int i = 1; i < parent.length; i++) {
			if (findset(i) == i)
				cnt++;

		}
		// cnt가 1이 될때까지 크루스칼 해야 됨.

		// 간선 m개가 이미 선택된 상황임. 크루스칼 진행 ㅡ> 틀림
		// int pick = m; ㅡ> 중복된 간선이 올 수 있다고 함
		int index = 0;
		double ans = 0;
		while (cnt > 1) {
			Edge now = edges.get(index++);

			if (findset(now.from) == findset(now.to))
				continue;
			ans += now.weight;
			union(now.from, now.to);
			cnt--;

		} // 총 n-1개의 간선이 선택됨 끝

		System.out.printf("%.2f", ans);

		br.close();
	}

}
