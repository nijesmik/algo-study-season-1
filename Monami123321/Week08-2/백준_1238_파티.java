import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 백준_1238_파티 {

	static int dijkstra(int start, int end) { // start 부터 end 까지 가는 최단경로구하는 메서드
		boolean[] visited = new boolean[n + 1];
		int[] minEdges = new int[n + 1];
		for (int i = 1; i < minEdges.length; i++) {
			minEdges[i] = Integer.MAX_VALUE;

		}
		minEdges[start] = 0;

		while (true) {
			int min = Integer.MAX_VALUE;
			int minIndex = -1;

			for (int i = 1; i < minEdges.length; i++) {
				if (visited[i]) {
					continue;
				}
				if (min > minEdges[i]) {
					min = minEdges[i];
					minIndex = i;
				}
			}
			visited[minIndex] = true;

			if (minIndex == end) {
				return minEdges[minIndex];

			}

			for (int[] tmp : adjList[minIndex]) {
				if (visited[tmp[0]]) {
					continue;
				}
				minEdges[tmp[0]] = Math.min(minEdges[tmp[0]], min + tmp[1]);
			}

		}

	}

	static int n, x, m;
	static List<int[]>[] adjList;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken()); // 학생수
		m = Integer.parseInt(st.nextToken()); // 파티 집 번호 1~
		x = Integer.parseInt(st.nextToken()); // 간선 갯수

		adjList = new ArrayList[n + 1]; // 인접리스트
		for (int i = 1; i < adjList.length; i++) {
			adjList[i] = new ArrayList<>();

		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			adjList[from].add(new int[] { to, weight });

		}
		int ans = 0;
		for (int i = 1; i <= n; i++) {
			int tmp = dijkstra(i, x);
			tmp += dijkstra(x, i);
			ans = Math.max(ans, tmp);

		} // 학생마다 다익스트라 하면서 최대값 구하기
		System.out.println(ans);

		br.close();
	}

}
