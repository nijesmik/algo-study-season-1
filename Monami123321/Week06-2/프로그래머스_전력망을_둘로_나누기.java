import java.util.*;

class Solution {
	static int ans = Integer.MAX_VALUE; // 최종 답
	static List<Integer>[] adjList; // 인접리스트
	static Queue<Integer> queue = new LinkedList<>();

	public int solution(int n, int[][] wires) {

		adjList = new ArrayList[n + 1]; // 노드 숫자가 1부터 주어짐
		for (int i = 1; i < n + 1; i++) {
			adjList[i] = new ArrayList<>();
		}
		for (int i = 0; i < n - 1; i++) {
			int from = wires[i][0];
			int to = wires[i][1];
			adjList[from].add(to);
			adjList[to].add(from);
		} // 인접리스트 작성 끝
			// 각 간선마다 끊어보면서 BFS, 가장 차이가 작은 것이 최종 답
		for (int i = 0; i < n - 1; i++) {
			int node1 = wires[i][0];
			int node2 = wires[i][1];
			adjList[node1].remove((Integer) node2); // 간선 끊기
			adjList[node2].remove((Integer) node1);

			int cnt1 = 0;
			int cnt2 = 0;
			boolean[] visited = new boolean[n + 1]; // 1번 노드쪽 BFS
			queue.add(node1);
			visited[node1] = true;
			while (!queue.isEmpty()) {
				int now = queue.poll(); // BFS 중 현재 뽑은 노드
				cnt1++;

				for (int j = 0; j < adjList[now].size(); j++) {
					int next = adjList[now].get(j); // 다음 큐에 넣을 노드
					if (visited[next]) {
						continue;
					}
					visited[next] = true;
					queue.add(next);

				}

			} // node 1 BFS 끝

			visited = new boolean[n + 1]; // 2번 노드쪽 BFS
			queue.add(node2);
			visited[node2] = true;
			while (!queue.isEmpty()) {
				int now = queue.poll(); // BFS 중 현재 뽑은 노드
				cnt2++;

				for (int j = 0; j < adjList[now].size(); j++) {
					int next = adjList[now].get(j); // 다음 큐에 넣을 노드
					if (visited[next]) {
						continue;
					}
					visited[next] = true;
					queue.add(next);

				}

			} // node 2 BFS 끝

			ans = Math.min(ans, Math.abs(cnt1 - cnt2)); // 두 노드쪽 결과의 차이랑 ans 비교해서 갱신

			adjList[node1].add(node2); // 끊었던 간선 복구
			adjList[node2].add(node1);

		}

		return ans;

	}
}