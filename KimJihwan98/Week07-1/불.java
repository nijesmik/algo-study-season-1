import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int r;
	int c;
	int time;

	public Node(int r, int c, int time) {
		this.r = r;
		this.c = c;
		this.time = time;
	}
}

public class 불 {
	static int w, h, answer, sr, sc;
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			map = new char[h][w];
			for (int i = 0; i < h; i++) {
				String s = br.readLine();
				for (int j = 0; j < w; j++) {
					map[i][j] = s.charAt(j);
					if (map[i][j] == '@') {
						sr = i;
						sc = j;
					}
				}
			}
			answer = Integer.MAX_VALUE;
			bfs();
			if (answer == Integer.MAX_VALUE)
				System.out.println("IMPOSSIBLE");
			else
				System.out.println(answer);
		}
	}

	static boolean[][] visited;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	static void bfs() {
		int time = 0;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(sr, sc, time));
		Queue<Node> queue = new LinkedList<>();
		visited = new boolean[h][w];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (map[i][j] == '*') {
					queue.add(new Node(i, j, time));
				}
			}
		}
		boolean flag = true;
		while (flag) {
			time++;
			while (!queue.isEmpty()) {
				if (time == queue.peek().time)
					break;
				Node now = queue.poll();
				int r = now.r;
				int c = now.c;
				int firetime = now.time;

				for (int k = 0; k < 4; k++) {
					int nr = r + dr[k];
					int nc = c + dc[k];
					if (!(nr >= 0 && nr < h && nc >= 0 && nc < w))
						continue;
					if (visited[nr][nc])
						continue;
					if (map[nr][nc] == '.' || map[nr][nc] == '@') {
						map[nr][nc] = '*';
						visited[nr][nc] = true;
						queue.add(new Node(nr, nc, firetime + 1));
					}
				}
			}
			while (!q.isEmpty()) {
				if (time == q.peek().time)
					break;
				Node now = q.poll();
				int r = now.r;
				int c = now.c;
				int qtime = now.time;
				if (qtime > answer)
					return;

				for (int i = 0; i < 4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					// 종료 조건
					if (!(nr >= 0 && nr < h && nc >= 0 && nc < w)) {
						answer = Math.min(answer, qtime + 1);
						return;
					}
					if (map[nr][nc] != '.')
						continue;
					map[nr][nc] = '@';
					q.add(new Node(nr, nc, qtime + 1));
				}
				if(q.isEmpty()) {
					flag = false;
				}
			}
		}
	}
}
