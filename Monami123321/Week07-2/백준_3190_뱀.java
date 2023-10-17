import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 백준_3190_뱀 {
	static int[] dr = { -1, 0, 1, 0 }; // 위부터 시계방향임
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine()) + 2; // 맵 크기 - 하나 더 크게해서 벽쳐놓은걸로
		int k = Integer.parseInt(br.readLine()); // 사과 갯수

		// 사과 = 2, 벽 = -1, 뱀 =1;
		int[][] map = new int[n][n];
		for (int i = 0; i < map.length; i++) {
			map[0][i] = -1;
			map[n - 1][i] = -1;
			map[i][0] = -1;
			map[i][n - 1] = -1; // 벽
		}
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r][c] = 2; // 사과

		}
		int r = 1;
		int c = 1;
		map[r][c] = 1; // 뱀

		List<int[]> snake = new LinkedList<>();
		snake.add(new int[] { r, c });

		int dir = 1; // 첨 시작 - 오른쪽 바라봄,(1,1)에서 시작
		int l = Integer.parseInt(br.readLine()); // 방향전환횟수 L
		HashMap<Integer, String> hashMap = new HashMap<>();
		for (int i = 0; i < l; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			String cmd = st.nextToken();
			hashMap.put(time, cmd);
		}

		int t = 0;
		while (true) {
			t++;
			int nr = r + dr[dir];
			int nc = c + dc[dir];

			int[] tail = snake.get(snake.size() - 1);

			if (map[nr][nc] == 1 || map[nr][nc] == -1) {
				break;
			}

			if (map[nr][nc] == 2) {
				map[nr][nc] = 1;
				snake.add(0, new int[] { nr, nc });
			} else {
				map[tail[0]][tail[1]] = 0;
				snake.remove(tail);
				map[nr][nc] = 1;
				snake.add(0, new int[] { nr, nc });

			}
			r = nr;
			c = nc;

			if (hashMap.get(t) != null) {
				String cmd = hashMap.get(t);
				switch (cmd) {
				case "L":
					dir = (dir + 3) % 4;
					break;
				case "D":
					dir = (dir + 1) % 4;
					break;

				default:
					break;
				}
			}

		}

		System.out.println(t);

		br.close();
	}

}
