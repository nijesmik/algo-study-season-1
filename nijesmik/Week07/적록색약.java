package nijesmik.Week07;
import java.util.Scanner;

public class 적록색약 {
	static char[][] board;
	static int n, cnt;
	static boolean[][] visit;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		board = new char[n][];
		for (int i = 0; i < n; i++) {
			board[i] = sc.next().toCharArray();
		}
		StringBuilder sb = new StringBuilder();
		for (int k = 0; k < 2; k++) {
			cnt = 0;
			visit = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (!visit[i][j]) {
						cnt++;
						dfs(i, j, board[i][j]);
					}
				}
			}
			sb.append(cnt+" ");
		}
		System.out.println(sb);
	}
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	static void dfs(int r, int c, char color) {
		if (visit[r][c]) return;
		visit[r][c] = true;
		if (board[r][c] == 'G') board[r][c] = 'R';
		for (int i = 0; i < 4; i++) {
			int nr = r+dr[i], nc = c+dc[i];
			if (nr >= 0 && nr < n && nc >= 0 && nc < n && board[nr][nc] == color)
				dfs(nr, nc, color);
		}
	}
}