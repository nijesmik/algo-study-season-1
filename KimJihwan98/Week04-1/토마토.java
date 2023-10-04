package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 토마토 {
	static int M, N, H, max;
	static int[][][] map;
	static boolean[][][] visited;
	static Queue<Integer[]> queue;
	static int[] dr = { 0, 1, 0, -1, 0, 0 };
	static int[] dc = { 1, 0, -1, 0, 0, 0 };
	static int[] dh = { 0, 0, 0, 0, 1, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][N][M];
		visited = new boolean[H][N][M];
		queue = new LinkedList<>();
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < M; k++) {
					map[i][j][k] = Integer.parseInt(st.nextToken());
					if (map[i][j][k] == 1) {
						visited[i][j][k] = true;
						queue.add(new Integer[] { i, j, k, 0 });
					}
				}
			}
		}
		int cnt = 0;
		while (!queue.isEmpty()) {
			while (!queue.isEmpty() && queue.peek()[3] == cnt) {
				Integer[] temp = queue.poll();
				int level = temp[3];
				for (int i = 0; i < 6; i++) {
					int nh = temp[0] + dh[i];
					int nr = temp[1] + dr[i];
					int nc = temp[2] + dc[i];
					if (!(nr >= 0 && nr < N && nc >= 0 && nc < M && nh >= 0 && nh < H) || visited[nh][nr][nc]
							|| map[nh][nr][nc] != 0)
						continue;
					visited[nh][nr][nc] = true;
					map[nh][nr][nc] = 1;
					queue.offer(new Integer[] { nh, nr, nc, level + 1 });
				}
			}
			cnt++;
		}
		boolean flag = true;
        outer:for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if(map[i][j][k] == 0) {
                        flag = false;
                        break outer;
                    }
                }
            }
        }
		if (flag)
			System.out.println(cnt-1);
		else
			System.out.println(-1);
	}
}
