package nijesmik.Week10;
import java.util.*;

public class 벽_부수고_이동하기 {
	static int r, c;
	static char[][] map;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		r = sc.nextInt(); c = sc.nextInt();
		map = new char[r][];
		for (int i = 0; i < r; i++) {
			map[i] = sc.next().toCharArray();
		}
		System.out.println(bfs());
	}
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	static int bfs() {
		boolean[][][] visit = new boolean[r][c][2];
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0, 0));
		visit[0][0][0] = true;
		int dist = 1;
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				Node cur = q.poll();
				for (int i = 0; i < 4; i++) {
					int nr = cur.r + dr[i], nc = cur.c + dc[i];
					if (nr < 0 || nr >= r || nc < 0 || nc >= c || visit[nr][nc][cur.crash]) continue;
					if (nr == r-1 && nc == c-1) return dist+1;
					visit[nr][nc][cur.crash] = true;
					if (map[nr][nc] == '0') {
						q.add(new Node(nr, nc, cur.crash));
					} else if (cur.crash == 0) {
						q.add(new Node(nr, nc, 1));
					}
				}
			}
			dist++;
		}
		if (r == 1 && c == 1) return 1;
		return -1;
	}
	static class Node {
		int r, c, crash;
		Node(int r, int c, int crash) {
			this.r = r;
			this.c = c;
			this.crash = crash;
		}
	}
}