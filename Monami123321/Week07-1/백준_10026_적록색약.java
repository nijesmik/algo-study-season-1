import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_10026_적록색약 {

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static Queue<int[]> queue = new LinkedList<>();
	static boolean[][] visited;
	static char[][] map;
	static int n;

	static void BFS(int r, int c) {
		queue.add(new int[] { r, c });
		char color = map[r][c];

		visited[r][c] = true;
		while (!queue.isEmpty()) {
			int[] node = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nr = node[0] + dr[i];
				int nc = node[1] + dc[i];
				if (nr < 0 || nr > n - 1 || nc < 0 || nc > n - 1 || visited[nr][nc] || map[nr][nc] != color) {
					continue;
				}
				visited[nr][nc] = true;
				queue.add(new int[] { nr, nc });

			}

		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(br.readLine());
		map = new char[n][];
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();

		}

		int cnt1 = 0; // 색약 아닌 경우 ㅡ> BFS 해서 카운팅

		visited = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j]) {
					cnt1++;
					BFS(i, j);
				}

			}

		}

		int cnt2 = 0; // 색약인 경우 ㅡ> 맵 색깔을 그냥 바꿔버리기, BFS해서 카운팅
		visited = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 'R') {
					map[i][j] = 'G';
				}

			}

		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j]) {
					cnt2++;
					BFS(i, j);
				}

			}

		}
		sb.append(cnt1).append(" ").append(cnt2).append(" ");
		System.out.println(sb);

		br.close();
	}

}
