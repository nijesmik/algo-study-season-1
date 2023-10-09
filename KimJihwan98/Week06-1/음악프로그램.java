package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 음악프로그램 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] degree = new int[N+1];
		List<Integer>[] adj = new ArrayList[N+1];
		for(int i = 0; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			Queue<Integer> temp = new LinkedList<>();
			while(st.hasMoreTokens()) {
				temp.add(Integer.parseInt(st.nextToken()));
			}
			temp.poll();
			while(true) {
				int a = temp.poll();
				if(temp.isEmpty()) break;
				int b = temp.peek();
				adj[a].add(b);
				degree[b]++;
			}
		}
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		Queue<Integer> answer = new LinkedList<>();
		for(int i = 1; i <= N; i++) {
			if(degree[i] == 0) {
				queue.add(i);
				visited[i] = true;
			}
			
		}
		
		while(!queue.isEmpty()) {
			int tmp = queue.poll();
			answer.add(tmp);
			for(int i = 0; i < adj[tmp].size(); i++) {
				degree[adj[tmp].get(i)]--;
			}
			for(int i = 1; i <= N; i++) {
				if(visited[i]) continue;
				if(degree[i] == 0) {
					queue.add(i);
					visited[i] = true;
				}
			}
		}
		boolean check = false;
		for(int i = 1; i <=N; i++) {
			if(degree[i] != 0) {
				check = true;
				break;
			}
		}
		if(check) System.out.println(0);
		else {
			while(!answer.isEmpty()) {
				System.out.println(answer.poll());
			}
		}
	}
}
