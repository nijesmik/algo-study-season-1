import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 백준_1504_특정한최단경로 {

	static boolean dijkstra(int start, int end) {

		int[] minEdges = new int[n + 1];
		for (int i = 1; i < minEdges.length; i++) {
			minEdges[i] = Integer.MAX_VALUE;

		}
		boolean[] visited = new boolean[n + 1];
		minEdges[start] = 0; // 다익스트라 시작점

		while (true) {
			int min = Integer.MAX_VALUE;
			int minIndex = -1;

			for (int i = 1; i < minEdges.length; i++) {
				if (visited[i])
					continue;

				if (minEdges[i] < min) {
					min = minEdges[i];
					minIndex = i;
				}

			}

			if (minIndex == end) { // start -> end 최단경로 발견
				break;
			}

			if (minIndex == -1) { // 가는길이 없음
				return false;
			}

			visited[minIndex] = true;

			for (int[] tmp : adjList[minIndex]) {
				if (visited[tmp[0]]) {
					continue;
				}
				minEdges[tmp[0]] = Math.min(minEdges[tmp[0]], min + tmp[1]);

			}

		}

		ans += minEdges[end];

		return true;
	}

	static int n, e, ans;
	static List<int[]>[] adjList;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		adjList = new ArrayList[n + 1];
		for (int i = 1; i < adjList.length; i++) {
			adjList[i] = new ArrayList<>();

		}

		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			adjList[from].add(new int[] { to, weight });
			adjList[to].add(new int[] { from, weight });

		}

		st = new StringTokenizer(br.readLine());
		int stop1 = Integer.parseInt(st.nextToken());
		int stop2 = Integer.parseInt(st.nextToken());
		ans = 0;
		boolean flag1 = true;
		boolean flag2 = true;

		if (!dijkstra(1, stop1)) {
			flag1 = false;
			ans = 200_000_001;
		}
		if (flag1) {
			if (!dijkstra(stop1, stop2)) {
				flag1 = false;
				ans = 200_000_001;
			}
		}

		if (flag1) {
			if (!dijkstra(stop2, n)) {
				flag1 = false;
				ans = 200_000_001;
			}
		}
		int tmp = ans;
		ans = 0;

		if (!dijkstra(1, stop2)) {
			flag2 = false;
			ans = 200_000_001;
		}
		if (flag2) {
			if (!dijkstra(stop2, stop1)) {
				flag2 = false;
				ans = 200_000_001;
			}
		}
		if (flag2) {
			if (!dijkstra(stop1, n)) {
				flag2 = false;
				ans = 200_000_001;
			}
		}

		if (!flag1 && !flag2) {
			System.out.println(-1);
		} else {
			System.out.println(Math.min(tmp, ans));

		}

		br.close();
	}
}
