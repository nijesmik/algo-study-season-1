import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_2206_벽부수고이동하기 {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[][] map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), "01", true);
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

			}

		}

		int[][] accMap = new int[n][m];
		int[][] visited = new int[n][m]; // accMap에 최소값이 적힐 때 벽 뚫었는지 0 1
		boolean[][] visited2 = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				accMap[i][j] = Integer.MAX_VALUE;

			}

		}
		accMap[0][0] = 1;

		Queue<int[]> queue = new LinkedList<>();

		queue.offer(new int[] { 0, 0, 1, 0 });

		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int r = now[0];
			int c = now[1];
			int cost = now[2];
			int wall = now[3];

			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				if (nr < 0 || nr > n - 1 || nc < 0 || nc > m - 1) {
					continue;
				}
				if ((accMap[nr][nc] <= cost + 1 && visited[nr][nc] <= wall + map[nr][nc]) || wall + map[nr][nc] >= 2) {
					continue;
				}

				if (cost + 1 <= accMap[nr][nc]) {
					queue.add(new int[] { nr, nc, cost + 1, wall + map[nr][nc] });
					visited[nr][nc] = wall + map[nr][nc];
					accMap[nr][nc] = cost + 1;

				} else {
					if (visited2[nr][nc] == true) {
						continue;
					}
					queue.add(new int[] { nr, nc, cost + 1, wall + map[nr][nc] });
					visited2[nr][nc] = true;
				}

			}

		}
		if (accMap[n - 1][m - 1] == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(accMap[n - 1][m - 1]);
		}

		br.close();
	}

}
