package nijesmik.Week07;
import java.util.*;

public class 로봇_청소기 {
	static int rsize, csize, r, c, dir, ans;
	static int[][] map;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		rsize = sc.nextInt(); csize = sc.nextInt();
		r = sc.nextInt(); c = sc.nextInt(); dir = sc.nextInt();
		map = new int[rsize][csize];
		for (int i = 0; i < rsize; i++) {
			for (int j = 0; j < csize; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		ans = 1;
		map[r][c] = 2;
		while (r >= 0 && r < rsize && c >= 0 && c < csize && map[r][c] != 1) {
			move();
		}
		System.out.println(ans);
	}
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static void move() {
		for (int i = 3; i >= 0; i--) {
			int ndir = (dir+i)%4, nr = r+dr[ndir], nc = c+dc[ndir];
			if (nr < 0 || nr >= rsize || nc < 0 || nc >= csize || map[nr][nc] > 0) continue;
			map[nr][nc] = 2;
			dir = ndir;
			ans++;
			r = nr; c = nc;
			return;
		}
		r -= dr[dir]; c -= dc[dir];
	}
}