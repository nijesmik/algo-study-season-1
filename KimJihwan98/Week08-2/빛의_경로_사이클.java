import java.util.*;

class Node1 {
	char type;
	boolean[] visited;

	public Node1(char type, boolean[] visited) {
		this.type = type;
		this.visited = visited;
	}
}

class Solution {
	static Node1[][] map;
	static int rnum, cnum, temp_count;

	public int[] solution(String[] grid) {
		rnum = grid.length;
		cnum = grid[0].length();
		map = new Node1[rnum][cnum];
		for (int i = 0; i < rnum; i++) {
			for (int j = 0; j < cnum; j++) {
				map[i][j] = new Node1(grid[i].charAt(j), new boolean[4]);
			}
		}

		temp_count = 0;
		List<Integer> tmp = new ArrayList<>();
		// 사이클 개수
		for (int i = 0; i < rnum; i++) {
			for (int j = 0; j < cnum; j++) {
				for (int d = 0; d < 4; d++) {
					if (map[i][j].visited[d])
						continue;
					find_cycle(i, j, d, 0);
					tmp.add(temp_count);
				}
			}
		}

		int[] answer = new int[tmp.size()];
		Collections.sort(tmp);

		for (int i = 0; i < tmp.size(); i++) {
			answer[i] = tmp.get(i);
		}

		return answer;
	}

	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	public void find_cycle(int r, int c, int dir, int count) {

		while (true) {

			// int nr = (r + dr[dir])%rnum < 0 ? rnum-1 : (r+dr[dir])%rnum;
			// int nc = (c + dc[dir])%cnum < 0 ? cnum-1 : (c+dc[dir])%cnum;

			int nr = r + dr[dir];
			int nc = c + dc[dir];

			if (nr < 0) {
				nr = rnum - 1;
			} else if (nr >= rnum) {
				nr = 0;
			}
			if (nc < 0) {
				nc = cnum - 1;
			} else if (nc >= cnum) {
				nc = 0;
			}
			r = nr;
			c = nc;

			// 방향설정
			if (map[r][c].type == 'L') {
				dir = (dir + 3) % 4;
			} else if (map[r][c].type == 'R') {
				dir = (dir + 1) % 4;
			}

			if (map[r][c].visited[dir]) {
				temp_count = count;
				return;
			}
			map[r][c].visited[dir] = true;
			count++;
		}

		// find_cycle(nr, nc, dir, count+1);
	}
}