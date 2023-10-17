import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 백준_5427_불 {
	static class Node {
		int r, c, depth;

		public Node(int r, int c, int depth) {
			super();
			this.r = r;
			this.c = c;
			this.depth = depth;
		}

	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static char[][] map;
	static boolean[][] visitedF;
	static boolean[][] visitedP;
	static int w, h;
	static int ans;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int testCases = scanner.nextInt();
		for (int tc = 1; tc <= testCases; tc++) {
			w = scanner.nextInt();
			h = scanner.nextInt();

			map = new char[h][w];
			for (int i = 0; i < h; i++) {
				map[i] = scanner.next().toCharArray();

			}
			ans = 0;
			visitedF = new boolean[h][w];
			visitedP = new boolean[h][w];
			Queue<Node> qPerson = new LinkedList<>();
			Queue<Node> qFire = new LinkedList<>();

			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (map[i][j] == '@') {
						qPerson.add(new Node(i, j, 0));
						visitedP[i][j] = true;
					} else if (map[i][j] == '*') {
						qFire.add(new Node(i, j, 0));
						visitedF[i][j] = true;
					}
				}

			}

			int t = 0;
			boolean flag = false;

			outer: while (!qPerson.isEmpty()) {

				while (!qFire.isEmpty() && qFire.peek().depth == t) {
					Node fire = qFire.poll();
					int r = fire.r;
					int c = fire.c;

					map[r][c] = '*'; // 불칠하기

					for (int i = 0; i < 4; i++) {
						int nr = r + dr[i];
						int nc = c + dc[i];

						if (nr < 0 || nr > h - 1 || nc < 0 || nc > w - 1 || map[nr][nc] == '#' || visitedF[nr][nc]) {
							continue;
						}
						visitedF[nr][nc] = true;
						qFire.add(new Node(nr, nc, fire.depth + 1));

					}

				}

				while (!qPerson.isEmpty() && qPerson.peek().depth == t) {
					Node person = qPerson.poll();
					int r = person.r;
					int c = person.c;

					if (map[r][c] == '*') {
						continue;
					}
					if (r == 0 || r == h - 1 || c == 0 || c == w - 1) {
						ans = t;
						flag = true;
						break outer;
					}

					for (int i = 0; i < 4; i++) {
						int nr = r + dr[i];
						int nc = c + dc[i];

						if (nr < 0 || nr > h - 1 || nc < 0 || nc > w - 1 || visitedP[nr][nc] || map[nr][nc] != '.') {
							continue;
						}

						visitedP[nr][nc] = true;
						qPerson.add(new Node(nr, nc, person.depth + 1));

					}

				}
				t++;

			}

			if (flag) {
				System.out.println(ans + 1);

			} else {
				System.out.println("IMPOSSIBLE");
			}

		}

		scanner.close();
	}

}
