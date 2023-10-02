package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 회장뽑기 {

	static int n, min;
	static List<Integer>[] graph;
	static boolean[] visited;
	static Queue<Integer[]> queue;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		graph = new ArrayList[n + 1];
		for (int i = 0; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (a == -1 && b == -1)
				break;
			graph[a].add(b);
			graph[b].add(a);
		}
		
		int[] score = new int[n + 1];
		min = Integer.MAX_VALUE;
		for (int i = 1; i <= n; i++) {
			visited = new boolean[n + 1];
			int count = bfs(i);
			score[i] = count;
			min = Math.min(min, count);
		}

		int count = 0;
//		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			if (min == score[i]) {
				count++;
//				sb.append(i);
			}
		}
		System.out.println(min + " " + count);
		for(int i = 1; i <= n; i++) {
			if(min == score[i]) {
				System.out.print(i + " ");
			}
		}
//		for (int i = 0; i < sb.length(); i++) {
//			System.out.print(sb.charAt(i) + " ");
//		}

	}

	static int bfs(int idx) {
		queue = new LinkedList<>();
		int cnt = 0;
		queue.add(new Integer[] { idx, cnt });
		visited[idx] = true;

		while (!queue.isEmpty()) {
			Integer[] tmp = queue.poll();

			for (int i = 0; i < graph[tmp[0]].size(); i++) {
				int next = graph[tmp[0]].get(i);
				if (visited[next])
					continue;
				visited[next] = true;
				queue.add(new Integer[] { next, tmp[1] + 1 });
			}

			cnt = tmp[1];
		}

		return cnt;
	}
}
