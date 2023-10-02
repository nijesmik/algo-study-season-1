import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_1012_유기농배추 {
	static int[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int w, h, k;
	static Queue<int[]> queue = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int testCases = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCases; tc++) {
			String[] tmp = br.readLine().split(" ");
			w = Integer.parseInt(tmp[0]);
			h = Integer.parseInt(tmp[1]);
			k = Integer.parseInt(tmp[2]);
			map = new int[h][w];
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				map[r][c] = 1;

			}
			int cnt = 0;
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (map[i][j] == 1) {
						cnt++;
						bfs(i, j, new boolean[h][w]);
					}

				}

			}

			System.out.println(cnt);

		}

		br.close();
	}

	static void bfs(int i, int j, boolean[][] visited) {
		queue.add(new int[] { i, j });
		visited[i][j] = true;

		while (!queue.isEmpty()) {
			int[] node = queue.poll();
			int r = node[0];
			int c = node[1];

			map[r][c] = 0;

			for (int k = 0; k < 4; k++) {
				int nr = r + dr[k];
				int nc = c + dc[k];

				if (nr < 0 || nr > h - 1 || nc < 0 || nc > w - 1 || map[nr][nc] == 0 || visited[nr][nc]) {
					continue;
				}
				visited[nr][nc] = true;
				queue.add(new int[] { nr, nc });

			}

		}

	}

}
