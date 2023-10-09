package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 빙산 {
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		boolean[][] visited = new boolean[N][M];
		Queue<int[]> queue = new LinkedList<>();
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < M; c++) {
				int a = Integer.parseInt(st.nextToken());
				map[r][c] = a;
				if(a != 0) {
					visited[r][c] = true;
					queue.offer(new int[] {r, c, 0});
				}
			}
		}
		
		int level = 0;
		int answer = 0;
		while(!queue.isEmpty()) {
			int[] next = queue.poll();
			if(next[2] > level) {
				if(bfs(N, M, map)) {
					answer = next[2];
					break;
				}
				visited = new boolean[N][M];
				for(int r = 0; r < N; r++) {
					for(int c = 0; c < M; c++) {
						if(map[r][c] != 0) visited[r][c] = true;
					}
				}
				level++;
			}
			int r = next[0];
			int c = next[1];
			visited[r][c] = true;
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(!(nr >= 0 && nr < N && nc >= 0 && nc < M) || visited[nr][nc]) continue;
				map[r][c]--;
				if(map[r][c] == 0) break;
			}
			if(map[r][c] != 0) queue.offer(new int[] {r, c, level+1});
		}
		
		System.out.println(answer);
	}
	
	static boolean bfs(int N, int M, int[][] map) {
		boolean[][] check = new boolean[N][M];
		Queue<int[]> q = new LinkedList<>();
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(map[r][c] == 0) check[r][c] = true;
			}
		}
		int cnt = 0;
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(map[r][c] == 0 || check[r][c]) continue;
				q.offer(new int[] {r, c});
				while(!q.isEmpty()) {
					int[] next = q.poll();
			
					for(int i = 0; i < 4; i++) {
						int nr = next[0] + dr[i];
						int nc = next[1] + dc[i];
						if(!(nr >= 0 && nr < N && nc >= 0 && nc < M) || check[nr][nc]) continue;
						check[nr][nc] = true;
						q.offer(new int[] {nr, nc});
					}
				}
				cnt++;
			}
		}
		if(cnt == 1 || cnt == 0) return false;
		else return true;
	}
}
