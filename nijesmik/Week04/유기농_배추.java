package nijesmik.Week04;
import java.util.*;

public class 유기농_배추 {
	static char[][] map;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int testcase = sc.nextInt();
		for (int t = 0; t < testcase; t++) {
			int cSize = sc.nextInt(), rSize = sc.nextInt(), k = sc.nextInt();
			map = new char[rSize+2][cSize+2];
			for (int i = 0; i < k; i++) {
				int c = sc.nextInt()+1, r = sc.nextInt()+1;
				map[r][c] = 1;
			}
			int cnt = 0;
			for (int i = 1; i <= rSize; i++) {
				for (int j = 1; j <= cSize; j++) {
					if (map[i][j] == 0) continue;
					bfs(i, j);
					cnt++;
				}
			}
			System.out.println(cnt);
		}
	}
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	static void bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[]{r, c});
		map[r][c] = 0;
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur[0]+dr[i], nc = cur[1]+dc[i];
				if (map[nr][nc] == 1) {
					q.add(new int[]{nr, nc});
					map[nr][nc] = 0;
				}
			}

		}
	}
}