import java.util.*;

class Solution {

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static char[][] map;
	static boolean[][][] visited;
	static List<Integer> list;
	static int mapR, mapC;

	// 들어온 방향을 dir로
	static void dfs(int r, int c, int dir, int len, int startR, int startC, int startDir) {
		while (!visited[dir][r][c]) {
			visited[dir][r][c] = true;

			int offset = 0;
			if (map[r][c] == 'L') {
				offset = 3;

			} else if (map[r][c] == 'R') {
				offset = 1;
			}

			dir = (dir + offset) % 4;
			r = (r + dr[dir] + mapR) % mapR;
			c = (c + dc[dir] + mapC) % mapC;
			len++;
		}
		if (len == 0) {
			return;
		}

		if (r == startR && c == startC && dir == startDir) {
			list.add(len);
			// visited[dir][r][c] = false;
			return;
		}

	}

	public int[] solution(String[] grid) {
		mapR = grid.length;
		mapC = grid[0].length();

		map = new char[mapR][];
		list = new ArrayList<>();
		visited = new boolean[4][mapR][mapC];

		for (int i = 0; i < mapR; i++) {
			map[i] = grid[i].toCharArray();
		}

		for (int i = 0; i < mapR; i++) {
			for (int j = 0; j < mapC; j++) {
				for (int k = 0; k < 4; k++) {
					// visited = new boolean[4][mapR][mapC];
					dfs(i, j, k, 0, i, j, k);
				}

			}
		}
		return list.stream().sorted().mapToInt(Integer::intValue).toArray();
	}
}