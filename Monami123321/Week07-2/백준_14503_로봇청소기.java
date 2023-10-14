import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_14503_로봇청소기 {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 세로
		int m = Integer.parseInt(st.nextToken()); // 가로
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int dir = Integer.parseInt(st.nextToken());

		int[][] map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

			}

		}

		int ans = 0;
		boolean flag;
		first: while (true) {

			if (map[r][c] == 0) {
				map[r][c] = -1; // 청소 ㅡ> -1칠하기
				ans++;
			}
			flag = false;
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				if (map[nr][nc] == 0) {
					flag = true;
					break;
				}

			} // 상하좌우 청소 안 한 칸이 있는지 확인 ㅡ> flag

			if (flag) { // 청소 안한 칸이 있음
				while (true) {
					dir = (dir + 3) % 4; // 반시계방향 회전
					int nr = r + dr[dir];
					int nc = c + dc[dir];
					if (map[nr][nc] == 0) {
						r = nr;
						c = nc;
						break;
					} // 한칸 전진 , 반복문 탈출

				}
				continue first;

			} else { // 청소안한 칸이 없을 떄
				int nr = r + dr[(dir + 2) % 4];
				int nc = c + dc[(dir + 2) % 4];
				if (map[nr][nc] != 1) { // 후진 가능하면 후진
					r = nr;
					c = nc;
					continue first;
				} else { // 후진 불가능 ㅡ> 끝
					break first;
				}

			}

		}

		System.out.println(ans);

		br.close();
	}
}