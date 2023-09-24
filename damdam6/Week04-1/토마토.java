package DATE0924;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BAEK7569 {

	static int M;
	static int N;
	static int H;
	static ArrayList<int[][]> box;
	static ArrayList<int[]> toma;

	public static void main(String[] args) throws Exception {

		StringBuilder sb = new StringBuilder();
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;

		st = new StringTokenizer(bf.readLine());

		// 세로 N 가로 M
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		box = new ArrayList<>();
		toma = new ArrayList<>();
		for (int h = 0; h < H; h++) {
			box.add(new int[N][M]);
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine());
				for (int j = 0; j < M; j++) {
					box.get(h)[i][j] = Integer.parseInt(st.nextToken());

					if (box.get(h)[i][j] == 1) {
						toma.add(new int[] { h, i, j });
					}
				}
			}

		}

		int stage = bfs();

		allfor: for (int h = 0; h < H; h++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (box.get(h)[i][j] == 0) {
						stage = -1;
						break allfor;
					}
				}
			}
		}
		
		System.out.println(stage);
	}

	static int[] dh = new int[] { 1, -1, 0, 0, 0, 0 };
	static int[] dx = new int[] { 0, 0, 1, -1, 0, 0 };
	static int[] dy = new int[] { 0, 0, 0, 0, 1, -1 };

	static int bfs() {

		Queue<node> qu = new ArrayDeque<>();

		// 현재 익은 토마토인 케이스를 다 함께 넣어야됨
		for (int i = 0; i < toma.size(); i++) {
			qu.offer(new node(toma.get(i)[0], toma.get(i)[1], toma.get(i)[2], 0));
			box.get(toma.get(i)[0])[toma.get(i)[1]][toma.get(i)[2]] = 2;
		}

		node tmp = new node(0,0,0,0);
		while (!qu.isEmpty()) {

			tmp = qu.poll();

			for (int i = 0; i < 6; i++) {

				int nh = tmp.h + dh[i];
				int nx = tmp.x + dx[i];
				int ny = tmp.y + dy[i];

				if (nh >= H || nh < 0 || nx >= N || nx < 0 || ny >= M || ny < 0 || box.get(nh)[nx][ny] != 0)
					continue;

				qu.offer(new node(nh, nx, ny, tmp));
				box.get(nh)[nx][ny] = 2;

			}

		}
		return tmp.stage;

	}

	static class node {

		int h, x, y;
		int stage;

		public node(int h, int x, int y, int stage) {
			super();
			this.h = h;
			this.x = x;
			this.y = y;
			this.stage = stage;
		}

		public node(int h, int x, int y, node n) {
			super();
			this.h = h;
			this.x = x;
			this.y = y;
			this.stage = n.stage + 1;
		}

	}

}
