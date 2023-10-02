import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 백준_15681_트리와쿼리 {

	static void dfs(int now, int prev, boolean[] visited) {
		visited[now] = true;

		for (int i = 0; i < adjList[now].size(); i++) {

			int child = adjList[now].get(i);
			if (visited[child])
				continue;
			dfs(child, now, visited);

		}

		for (int i = 0; i < adjList[now].size(); i++) {
			int child = adjList[now].get(i);
			if (visited[child])
				continue;
			subtree[now] += subtree[child];

		}
		subtree[now]++;
		visited[now] = false;

		return;
	}

	static List<Integer>[] adjList;
	static int[] subtree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());

		subtree = new int[n + 1];
		adjList = new ArrayList[n + 1];
		for (int i = 1; i < adjList.length; i++) {
			adjList[i] = new ArrayList<>();

		}
		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			adjList[a].add(b);
			adjList[b].add(a);

		}

		dfs(r, 0, new boolean[n + 1]);
		for (int i = 0; i < q; i++) {
			int query = Integer.parseInt(br.readLine());
			sb.append(subtree[query]).append("\n");

		}
		System.out.println(sb);

		br.close();
	}

}
