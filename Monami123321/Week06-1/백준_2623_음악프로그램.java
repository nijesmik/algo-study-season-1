import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_2623_음악프로그램 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		List<Integer>[] adjList = new ArrayList[n + 1];
		for (int i = 1; i < adjList.length; i++) {
			adjList[i] = new ArrayList<>();

		}
		int[] inDegree = new int[n + 1];
		boolean[] visited = new boolean[n + 1];

		for (int i = 0; i < m; i++) {
			String[] tmp = br.readLine().split(" ");
			for (int j = tmp.length - 1; j > 1; j--) {
				int to = Integer.parseInt(tmp[j]);
				int from = Integer.parseInt(tmp[j - 1]);

				adjList[from].add(to);
				inDegree[to]++;

			}

		}

		Queue<Integer> queue = new LinkedList<>();
		for (int i = 1; i < inDegree.length; i++) {
			if (inDegree[i] == 0) {
				queue.add(i);
				visited[i] = true;
			}

		}

		int check = 0;
		while (!queue.isEmpty()) {
			int node = queue.poll();

			sb.append(node).append("\n");
			check++;

			for (int i = 0; i < adjList[node].size(); i++) {
				if (visited[adjList[node].get(i)])
					continue;
				inDegree[adjList[node].get(i)]--;
				if (inDegree[adjList[node].get(i)] == 0) {
					queue.add(adjList[node].get(i));
					visited[adjList[node].get(i)] = true;
				}

			}

		}
		if (check != n) {
			System.out.println(0);
		} else {
			System.out.println(sb);
		}

		br.close();

	}

}
