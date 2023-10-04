import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_1068_트리 {
	static class Node {
		int parent, cnt;
		List<Node> child;

		public Node() {
			super();
			this.child = new ArrayList<>();
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		Node[] nodes = new Node[n];
		for (int i = 0; i < nodes.length; i++) {
			nodes[i] = new Node();

		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			if (tmp == -1)
				continue;
			nodes[i].parent = tmp;
			nodes[tmp].child.add(nodes[i]);
			nodes[tmp].cnt++;

		}
		int del = Integer.parseInt(br.readLine()); // 지울 노드 번호

		Queue<Node> queue = new LinkedList<>();
		queue.add(nodes[del]);
		nodes[nodes[del].parent].cnt--;

		while (!queue.isEmpty()) {
			Node node = queue.poll();
			node.cnt = Integer.MAX_VALUE;

			for (int i = 0; i < node.child.size(); i++) {
				queue.add(node.child.get(i));

			}

		}

		int ans = 0;
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i].cnt == 0)
				ans++;

		}
		System.out.println(ans);

		br.close();

	}

}
