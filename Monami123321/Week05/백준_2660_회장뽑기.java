import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_2660_회장뽑기 {

	static List<Integer>[] adjList;
	static int[] ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		ans = new int[n + 1]; // i번째 사람의 점수
		adjList = new ArrayList[n + 1];

		for (int i = 1; i < adjList.length; i++) {
			adjList[i] = new ArrayList<>();

		}

		while (true) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (a == -1)
				break;

			adjList[a].add(b);
			adjList[b].add(a);

		}

		Queue<int[]> queue = new LinkedList<>();

		for (int i = 1; i < n + 1; i++) {
			boolean[] visited = new boolean[n + 1];
			visited[i] = true;
			queue.add(new int[] { i, 0 });

			while (!queue.isEmpty()) {
				int[] node = queue.poll();
				int depth = node[1];
				ans[i] = depth;

				for (int j = 0; j < adjList[node[0]].size(); j++) {
					int nextNode = adjList[node[0]].get(j);
					if (visited[nextNode])
						continue;

					queue.add(new int[] { nextNode, depth + 1 });
					visited[nextNode] = true;

				}

			}

		}

		int min = Integer.MAX_VALUE;
		int cnt = 0;
		List<Integer> ansNodes = new ArrayList<>();
		for (int i = 1; i < ans.length; i++) {
			min = Math.min(min, ans[i]);

		}

		for (int i = 1; i < ans.length; i++) {
			if (ans[i] == min) {
				cnt++;
				ansNodes.add(i);
			}

		}
		System.out.printf("%d %d\n", min, cnt);
		for (int i = 0; i < ansNodes.size(); i++) {
			System.out.printf("%d ", ansNodes.get(i));

		}

		br.close();
	}

}