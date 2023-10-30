import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	static int sr, sc, N, size, time;
	static int[][] map;
	static final int INF = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		size = 2;
		time = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int a = Integer.parseInt(st.nextToken());
				if(a == 0) map[i][j] = INF;
				else map[i][j] = a;
				if (map[i][j] == 9) {
					sr = i;
					sc = j;
				}
			}
		}
		
		int size_cnt = 0;
		while(true) {
			int tmp_time = find_fish(sr, sc);
			if(tmp_time == INF) break;
			time += tmp_time;
			size_cnt++;
			if(size_cnt == size) {
				size_cnt = 0;
				size++;
			}
		}
		
		System.out.println(time);
	}

	static int[] dr = {-1, 0, 0, 1};
	static int[] dc = {0, -1, 1, 0};


	static boolean[][] visited;
	static PriorityQueue<int[]> pq;

	static int find_fish(int start_r, int start_c) {
		// 자기보다 큰 물고기 자리는 미리 방문처리
		visited = new boolean[N][N];

		pq = new PriorityQueue<>((f1, f2) -> {
			if(f1[2] == f2[2]) {
				if(f1[0] == f2[0]) {
					return f1[1] - f2[1];
				}
				return f1[0] - f2[0];
			}
			return f1[2] - f2[2];
		});
		pq.add(new int[] { start_r, start_c, 0 });
		visited[start_r][start_c] = true;
		while (!pq.isEmpty()) {
			int[] tmp = pq.poll();
			int r = tmp[0];
			int c = tmp[1];
			int t = tmp[2];
			
			if(map[r][c] < size) {
				map[start_r][start_c] = INF;
				map[r][c] = INF;
				sr = r;
				sc = c;
				return t;
			}

			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if (!(nr >= 0 && nr < N && nc >= 0 && nc < N))
					continue;
				if (visited[nr][nc] || (map[nr][nc] != INF && map[nr][nc] > size))
					continue;
				visited[nr][nc] = true;
				pq.offer(new int[] { nr, nc, t + 1 });
			}
		}
		
		return INF;
	}
}
