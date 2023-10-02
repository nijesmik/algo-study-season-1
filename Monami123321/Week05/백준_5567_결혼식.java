import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_5567_결혼식 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		List<Integer>[] adjList = new ArrayList[n + 1];
		for (int i = 1; i < adjList.length; i++) {
			adjList[i] = new ArrayList<>();

		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjList[a].add(b);
			adjList[b].add(a);

		}
		int ans = -1;
		boolean[] visited = new boolean[n + 1];

		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { 1, 0 });
		visited[1] = true;

		while (!queue.isEmpty()) {
			int[] node = queue.poll();
			int depth = node[1];
			if (depth > 2)
				break;
			ans++;

			for (int i = 0; i < adjList[node[0]].size(); i++) {
				int nextNode = adjList[node[0]].get(i);
				if (visited[nextNode])
					continue;
				queue.add(new int[] { nextNode, depth + 1 });
				visited[nextNode] = true;
			}

		}

			System.out.println(ans);


		br.close();
	}

}
