import java.util.*;

public class 불 {
	static char[][] map;
	static Queue<int[]> fire, move;
	static int rsize, csize;
	static boolean[][] visit;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int testcase = sc.nextInt();
		for (int t = 0; t < testcase; t++) {
			csize = sc.nextInt(); rsize = sc.nextInt();
			map = new char[rsize+2][csize+2];
			fire = new LinkedList<>();
			move = new LinkedList<>();
			visit = new boolean[rsize+2][csize+2];
			for (int i = 1; i <= rsize; i++) {
				char[] tmp = sc.next().toCharArray();
				for (int j = 1; j <= csize; j++) {
					map[i][j] = tmp[j-1];
					if (map[i][j] == '*') fire.add(new int[]{i, j});
					else if (map[i][j] == '@') {
						move.add(new int[]{i, j});
						visit[i][j] = true;
						map[i][j] = '.';
					}
				}
			}
			int time = bfs();
			if (time > 0) System.out.println(time);
			else System.out.println("IMPOSSIBLE");
		}
	}
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	static int bfs() {
		int time = 1;
		int[] cur;
		while (!move.isEmpty()) {
			int size = move.size();
			for (int s = 0; s < size; s++) {
				cur = move.poll();
				if (map[cur[0]][cur[1]] == '*') return 0;
				for (int i = 0; i < 4; i++) {
					int nr = cur[0]+dr[i], nc = cur[1]+dc[i];
					if (map[nr][nc] == 0) return time;
					else if (map[nr][nc] == '.' && !visit[nr][nc]) {
						visit[nr][nc] = true;
						move.add(new int[]{nr, nc});
					}
				}
			}
			size = fire.size();
			for (int s = 0; s < size; s++) {
				cur = fire.poll();
				for (int i = 0; i < 4; i++) {
					int nr = cur[0]+dr[i], nc = cur[1]+dc[i];
					if (map[nr][nc] == '.') {
						map[nr][nc] = '*';
						fire.add(new int[]{nr, nc});
					}
				}
			}
			time++;
		}
		return 0;
	}
}