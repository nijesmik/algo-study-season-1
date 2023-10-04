package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 결혼식 {
	static int n, m, ans;
	static List<Integer>[] graph;
	static boolean[] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		graph = new ArrayList[n+1];
		for(int i = 0; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}
		visited = new boolean[n+1];
		for(int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		ans = 0;
		dfs(1, 0);
		for(int i = 2; i <= n; i++) {
			if(visited[i]) ans++;
		}
		System.out.println(ans);
	}
	static void dfs(int idx, int level) {
		if(level > 1) return;
		visited[idx] = true;
		for(int i = 0; i < graph[idx].size(); i++) {
			int tmp = graph[idx].get(i);
			visited[tmp] = true;
			dfs(tmp, level+1);
		}
		
	}
}
