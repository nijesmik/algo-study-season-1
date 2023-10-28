package nijesmik.Week09;
import java.util.*;

public class 아기_상어 {
	static int n;
	static int[][] map;
	static Fish shark;
	static List<Fish> list;

	static class Fish implements Comparable<Fish> {
		static int[] dr = {1, 0, -1, 0};
		static int[] dc = {0, 1, 0, -1};

		int r, c, dist, size, cnt;
		boolean visit;

		Fish(int x, int y, int s) {
			r = x; c = y; size = s;
		}

		@Override
		public int compareTo(Fish o) {
			if (dist == o.dist) {
				if (r == o.r) return Integer.compare(c, o.c);
				return Integer.compare(r, o.r);
			}
			return Integer.compare(dist, o.dist);
		}

		void calcDist() {
			Queue<int[]> q = new LinkedList<>();
			boolean[][] visit = new boolean[n][n];
			q.add(new int[]{shark.r, shark.c, 0});
			visit[shark.r][shark.c] = true;
			while (!q.isEmpty()) {
				int[] cur = q.poll();
				for (int i = 0; i < 4; i++) {
					int nr = cur[0]+dr[i], nc = cur[1]+dc[i], ndist = cur[2]+1;
					if (nr < 0 || nr >= n || nc < 0 || nc >= n || visit[nr][nc] || map[nr][nc] > shark.size) continue;
					if (nr == r && nc == c) {
						dist = ndist;
						return;
					}
					visit[nr][nc] = true;
					q.add(new int[]{nr, nc, ndist});
				}
			}
			dist = Integer.MAX_VALUE;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		map = new int[n][n];
		list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 9) {
					map[i][j] = 0;
					shark = new Fish(i, j, 2);
				} else if (map[i][j] > 0) {
					Fish fish = new Fish(i, j, map[i][j]);
					list.add(fish);
				}
			}
		}
		int time = 0;
		while (true) {
			int dist = move();
			if (dist == 0) break;
			time += dist;
		}
		System.out.println(time);
	}

	static int move() {
		PriorityQueue<Fish> pq = new PriorityQueue<>();
		for (Fish fish : list) {
			if (fish.visit || fish.size >= shark.size) continue;
			fish.calcDist();
			pq.add(fish);
		}
		if (pq.isEmpty()) return 0;
		Fish eat = pq.poll();
		if (eat.dist == Integer.MAX_VALUE) return 0;
		eat.visit = true;
		shark.r = eat.r;
		shark.c = eat.c;
		map[eat.r][eat.c] = 0;
		if (++shark.cnt == shark.size) {
			shark.cnt = 0;
			shark.size++;
		}
		return eat.dist;
	}
}