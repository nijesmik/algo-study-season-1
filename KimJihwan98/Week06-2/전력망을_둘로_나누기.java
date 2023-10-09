package Baekjoon;

import java.util.*;

public class 전력망을_둘로_나누기 {
	static List<Integer>[] graph;

	public int solution(int n, int[][] wires) {
		int answer = Integer.MAX_VALUE;
		graph = new ArrayList[n + 1];
		for (int i = 1; i < n + 1; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < wires.length; i++) {
			int a = wires[i][0];
			int b = wires[i][1];
			graph[a].add(b);
			graph[b].add(a);
		}

		for (int i = 0; i < wires.length; i++) {
			int a = wires[i][0];
			int b = wires[i][1];
			graph[a].remove(Integer.valueOf(b));
			graph[b].remove(Integer.valueOf(a));
			int temp = bfs(1, n);
			answer = Math.min(answer, temp);
			graph[a].add(b);
			graph[b].add(a);
		}

		return answer;
	}

	static int bfs(int idx, int n) {
		int count = 1;
		boolean[] visited = new boolean[n + 1];
		Queue<Integer> q = new LinkedList<>();
		q.add(idx);
		visited[idx] = true;
		while (!q.isEmpty()) {
			int tmp = q.poll();
			for (int i = 0; i < graph[tmp].size(); i++) {
				if (visited[graph[tmp].get(i)])
					continue;
				visited[graph[tmp].get(i)] = true;
				q.add(graph[tmp].get(i));
				count++;
			}
		}
		return Math.abs(n - 2 * count);
	}
}
