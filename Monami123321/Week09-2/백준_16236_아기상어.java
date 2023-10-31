import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class 백준_16236_아기상어 {
	static class Shark {
		int r, c, size, cnt;

		public Shark(int r, int c, int size) {
			super();
			this.r = r;
			this.c = c;
			this.size = size;
		}

	}

	static int bfs(int r, int c) {
		boolean[][] visited = new boolean[n][n];

		queue.add(new int[] { r, c, 0 });
		visited[r][c] = true;
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			if (map[now[0]][now[1]] != 0 && map[now[0]][now[1]] < shark.size) {

				List<int[]> tmpList = new ArrayList<>();
				tmpList.add(now);
				while (!queue.isEmpty()) {
					int[] tmp = queue.poll();
					if (tmp[2] == now[2]) {
						if (map[tmp[0]][tmp[1]] != 0 && map[tmp[0]][tmp[1]] < shark.size) {
							tmpList.add(tmp);
						}

					} else {
						break;
					}
				}
				List<int[]> chosen = tmpList.stream().sorted((a, b) -> {
					if (a[0] != b[0]) {
						return a[0] - b[0];
					} else {
						return a[1] - b[1];
					}

				}).collect(Collectors.toList());

				int[] chosenChk = chosen.get(0);

				if (++shark.cnt == shark.size) {
					shark.size++;
					shark.cnt = 0;
				}
				queue.clear();
				map[chosenChk[0]][chosenChk[1]] = 0;
				shark.r = chosenChk[0];
				shark.c = chosenChk[1];

				return chosenChk[2];
			}
			for (int i = 0; i < 4; i++) {
				int nr = now[0] + dr[i];
				int nc = now[1] + dc[i];

				if (nr < 0 || nr > n - 1 || nc < 0 || nc > n - 1 || map[nr][nc] > shark.size || visited[nr][nc]) {
					continue;

				}
				queue.add(new int[] { nr, nc, now[2] + 1 });
				visited[nr][nc] = true;

			}

		}
		return -1;

	}

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };
	static int[][] map;
	static Shark shark;
	static int n;
	static Queue<int[]> queue;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		queue = new LinkedList<>();
		map = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				map[i][j] = tmp;
				if (tmp == 9) {
					shark = new Shark(i, j, 2);
					map[i][j] = 0;
				}

			}

		}
		int t = 0; // 최종 정답
		while (true) {

			int timeSpent = bfs(shark.r, shark.c);
			if (timeSpent == -1) {
				break;
			} else {
				t += timeSpent;
			}

		}
		System.out.println(t);

		br.close();

	}

}
