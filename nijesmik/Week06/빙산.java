package nijesmik.Week06;
import java.util.Scanner;

public class 빙산 {
	static int[][] map;
	static int rsize, csize;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		rsize = sc.nextInt(); csize = sc.nextInt();
		map = new int[rsize][csize];
		for (int r = 0; r < rsize; r++) {
			for (int c = 0; c < csize; c++) {
				map[r][c] = sc.nextInt();
			}
		}
		int time = 0;
		while (true) {
			time++;
			melt();
			int cnt = count();
			if (cnt == 0) time = 0;
			if (cnt != 1) break;
		}
		System.out.println(time);
	}
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	static void melt() {
		int[][] tmp = new int[rsize][csize];
		for (int r = 1; r < rsize-1; r++) {
			for (int c = 1; c < csize-1; c++) {
				if (map[r][c] == 0) continue;
				int cnt = 0;
				for (int i = 0; i < 4; i++) {
					int nr = r+dr[i], nc = c+dc[i];
					if (map[nr][nc] == 0) cnt++;
				}
				tmp[r][c] = Math.max(0, map[r][c]-cnt);
			}
		}
		map = tmp;
	}
	static boolean[][] visit;
	static void dfs(int r, int c) {
		visit[r][c] = true;
		for (int i = 0; i < 4; i++) {
			int nr = r+dr[i], nc = c+dc[i];
			if (map[r][c] > 0 && !visit[nr][nc]) dfs(nr, nc);
		}
	}
	static int count() {
		int cnt = 0;
		visit = new boolean[rsize][csize];
		for (int r = 1; r < rsize-1; r++) {
			for (int c = 1; c < csize-1; c++) {
				if (map[r][c] > 0 && !visit[r][c]) {
					dfs(r, c);
					cnt++;
				}
			}
		}
		return cnt;
	}
}