package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 유기농_배추 {
	static int M, N, K, cnt;
	static int[][] map;
	static boolean[][] visited;
	static Queue<Integer[]> queue;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[M][N];
			visited = new boolean[M][N];
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				map[a][b] = 1;
			}
			
			queue = new LinkedList<>();
			cnt = 0;
			for(int i = 0; i < M; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] == 1 && !visited[i][j]) {
						bfs(i, j);
						cnt++;
					}
				}
			}
			System.out.println(cnt);
		}
	}
	
	public static void bfs(int x, int y) {
		
		queue.add(new Integer[] {x, y});
		
		while(!queue.isEmpty()) {
			Integer[] tmp = queue.poll();
			x = tmp[0];
			y = tmp[1];
			visited[x][y] = true;
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx>=0 && nx < M && ny>=0 && ny<N && map[nx][ny] == 1 && !visited[nx][ny]) {
					visited[nx][ny] = true;
					queue.add(new Integer[] {nx, ny});
				}
			}
		}
		
	}
}
