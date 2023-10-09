package DATE1009;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

import java.util.Deque;
import java.util.StringTokenizer;

public class BAEK2573 {
	static int N;
	static int M;
	static block[][] sea;

	public static void main(String[] args) throws Exception {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		sea = new block[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < M; j++) {
				sea[i][j] = new block(i, j, Integer.parseInt(st.nextToken()));
			}
		}

		year = 0;
		int answer = -1;
		while (answer == -1) {

			year++;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					sea[i][j].vt = false;
					sea[i][j].chkzero = false;
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {

					if (sea[i][j].val == 0)
						continue;

					for (int k = 0; k < 4; k++) {
						nx = i + dx[k];
						ny = j + dy[k];
						if (nx < 0 || nx >= N || ny < 0 || ny >= M || sea[nx][ny].val != 0 || sea[nx][ny].chkzero)
							continue;
						sea[i][j].val--;

						if (sea[i][j].val == 0) {
							sea[i][j].chkzero = true;
							break;
						}
					}
				}

			}
			answer = chkBfs();

//			System.out.println("----");
//			System.out.println(year);
//			for (int i = 0; i < N; i++) {
//				System.out.println();
//				for (int j = 0; j < M; j++) {
//					System.out.print(sea[i][j].val + " ");
//				}
//			}
//			System.out.println();
//			System.out.println("----");
		}

		System.out.println(answer);

	}

	static int nx;
	static int ny;
	static int year;
	static int[] dx = new int[] { -1, 1, 0, 0 };
	static int[] dy = new int[] { 0, 0, -1, 1 };

	static // -1이면 안됨 -> 시간 지나는거 살펴보기
	int chkBfs() {

		Deque<block> qu = new ArrayDeque<>();
		allfor: for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (sea[i][j].val == 0)
					continue;

				qu.add(sea[i][j]);
				sea[i][j].vt = true;
				break allfor;
			}
		}

		if (qu.isEmpty()) {
			return 0;
		}

		block tmp;
		while (!qu.isEmpty()) {
			tmp = qu.poll();

			for (int i = 0; i < 4; i++) {
				nx = tmp.x + dx[i];
				ny = tmp.y + dy[i];

				if (nx < 0 || nx >= N || ny < 0 || ny >= M || sea[nx][ny].val == 0 || sea[nx][ny].vt)
					continue;
				qu.add(sea[nx][ny]);
				sea[nx][ny].vt = true;

			}

		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (sea[i][j].val != 0 && !sea[i][j].vt) {
					return year;
				}

			}
		}

		return -1;
	}

	static class block {
		int x;
		int y;
		int val;
		boolean vt;
		boolean chkzero;

		public block(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		public block(int x, int y, int val) {
			this.x = x;
			this.y = y;
			this.val = val;
		}

		public String toString() {
			return this.val + "";
		}

	}
}
