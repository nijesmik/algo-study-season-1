import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_2573_빙산 {
	static int n, m;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[][] map;
	static boolean[][] visited;
	static Queue<int[]> queue = new LinkedList<int[]>();

	static void bfs(int r, int c) {
		queue.add(new int[] { r, c });
		visited[r][c] = true;

		while (!queue.isEmpty()) {
			int[] node = queue.poll();
			int nodeR = node[0];
			int nodeC = node[1];

			for (int i = 0; i < 4; i++) {
				int nr = nodeR + dr[i];
				int nc = nodeC + dc[i];

				if (map[nr][nc] <= 0 || visited[nr][nc])
					continue;
				queue.add(new int[] { nr, nc });
				visited[nr][nc] = true;

			}
		}

	}

	static void del() {
		int[][] tmp = new int[n][m];
		for (int i = 1; i < n - 1; i++) {
			for (int j = 1; j < m - 1; j++) {
				if (map[i][j] > 0) {
					int cnt = 0;
					for (int k = 0; k < 4; k++) {
						if (map[i + dr[k]][j + dc[k]] <= 0)
							cnt++;

					}
					tmp[i][j] = cnt;

				}
			}

		}

		for (int i = 1; i < n - 1; i++) {
			for (int j = 1; j < m - 1; j++) {
				map[i][j] -= tmp[i][j];

			}

		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken()); // 행
		m = Integer.parseInt(st.nextToken()); // 열

		map = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

			}

		} // 빙산 입력 끝 ㅡ>상하좌우 테두리는 무조건 0 이 주어짐

		int t = 0;
		while (true) {
			t++;
			del();
			visited = new boolean[n][m];
			int check = 0; // 빙산 갯수
			for (int i = 1; i < n - 1; i++) {
				for (int j = 1; j < m - 1; j++) {
					if (map[i][j] > 0 && !visited[i][j]) {
						check++;
						bfs(i, j);
					}

				}

			}
			if (check > 1) {
				break;
			}
			if (check == 0) {
				t = 0;
				break;
			}

		}

		System.out.println(t);

		br.close();
	}

}
