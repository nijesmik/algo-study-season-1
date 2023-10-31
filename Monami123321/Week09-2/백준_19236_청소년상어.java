import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_19236_청소년상어 { // 실패

	static class Fish {
		int r, c, size, dir;
		boolean isShark;

		public Fish(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		public Fish(int r, int c, int size, int dir) {
			super();
			this.r = r;
			this.c = c;
			this.size = size;
			this.dir = dir;
		}

	}

	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 }; // 12시부터 반시계
	static int[] dc = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int cnt;

//	static boolean isMovable(Fish[][] map, Fish shark) {
//		boolean flag = false;
//		int multiple = 1;
//		while (multiple < 4) {
//			int nr = shark.r + dr[shark.dir] * multiple;
//			int nc = shark.c + dc[shark.dir] * multiple;
//			if (nr < 0 || nc < 0 || nr > 3 || nc > 3 || map[nr][nc] == null) {
//				multiple++;
//				continue;
//
//			}
//			flag = true;
//			break;
//
//		}
//		return flag;
//	}

	static void dfs(Fish[][] map, Fish[] info, Fish shark, int tmpCnt) {
		move(map, info);

//		if (!isMovable(map, shark)) {
//			cnt = Math.max(cnt, tmpCnt);
//			return;
//		}
		int multiple = 1;
		while (multiple < 4) {
			int nr = shark.r + dr[shark.dir] * multiple;
			int nc = shark.c + dc[shark.dir] * multiple;

			if (nr < 0 || nr > 3 || nc < 0 || nc > 3 || map[nr][nc] == null) {
				multiple++;
				continue;
			}

			Fish[][] nextMap = copyMap(map);
			Fish[] nextInfo = copyArr(info);
			Fish nextShark = new Fish(shark.r, shark.c);
			nextShark.size = shark.size;
			nextShark.dir = shark.dir;

			int nextCnt = eat(nextMap, nextInfo, nextShark, multiple);
			dfs(nextMap, nextInfo, nextShark, tmpCnt + nextCnt);
			multiple++;

		}
		cnt = Math.max(cnt, tmpCnt);
		return;

	}

	static int eat(Fish[][] map, Fish[] info, Fish shark, int multiple) {
		int r = shark.r;
		int c = shark.c;
		int dir = shark.dir;

		info[shark.size] = null;
		Fish nextShark = map[r + dr[dir] * multiple][c + dc[dir] * multiple];
		nextShark.isShark = true;
		shark = nextShark;

		map[r][c] = null;
		return nextShark.size;

	}

	static Fish[][] copyMap(Fish[][] original) {
		Fish[][] copied = new Fish[4][4];
		for (int i = 0; i < copied.length; i++) {
			for (int j = 0; j < copied.length; j++) {
				if (original[i][j] == null) {
					continue;
				}
				copied[i][j] = new Fish(i, j);
				copied[i][j].size = original[i][j].size;
				copied[i][j].dir = original[i][j].dir;

			}

		}

		return copied;
	}

	static Fish[] copyArr(Fish[] original) {
		Fish[] copied = new Fish[17];

		for (int i = 1; i < copied.length; i++) {
			Fish tmp = original[i];
			if (tmp == null) {
				continue;
			}
			copied[i] = new Fish(tmp.r, tmp.c, tmp.size, tmp.dir);

		}

		return copied;
	}

	static void move(Fish[][] prevMap, Fish[] prevInfo) {

		for (int i = 1; i < prevInfo.length; i++) {

			Fish now = prevInfo[i];

			if (now == null || now.isShark == true) {
				continue;
			}

			for (int j = 0; j < 8; j++) {
				int nr = now.r + dr[(now.dir + j) % 8];
				int nc = now.c + dc[(now.dir + j) % 8];
				if (nr < 0 || nr > 3 || nc < 0 || nc > 3 || prevMap[nr][nc] != null && prevMap[nr][nc].isShark) {
					// 인덱스 범위 넘거나 상어면 못움직임
					continue;

				}
				// 움직일 수 있으면 자리교체하고 방향 갱신
				if (prevMap[nr][nc] == null) {
					swap(prevMap, now, nr, nc);
				} else {
					swap(prevMap, now, prevMap[nr][nc]);
				}
				now.dir = (now.dir + j) % 8;
				break; // 여기까지 진행하면 맵에 고기정보입력끝. 배열에도 갱신됨

			} // 8방 다돌아봤는데도 못움직이면 그대로임

		}

	}

	static void swap(Fish[][] tmpMap, Fish from, Fish to) {

		tmpMap[from.r][from.c] = to;
		tmpMap[to.r][to.c] = from;
		int tmpR = from.r;
		int tmpC = from.c;
		from.r = to.r;
		from.c = to.c;
		to.r = tmpR;
		to.c = tmpC;
		// 주어진 맵에서 두 물고기 자리 바꾸고, r,c 갱신함

	}

	static void swap(Fish[][] tmpMap, Fish from, int toR, int toC) {
		tmpMap[toR][toC] = from;
		tmpMap[from.r][from.c] = null;
		from.r = toR;
		from.c = toC;

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Fish[][] map = new Fish[4][4];
		Fish[] info = new Fish[17]; // 물고기 정보를 순서대로 가지고 있는 배열 (1번~16번 고기)

		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int a = Integer.parseInt(st.nextToken()); // 물고기번호(사이즈)
				int b = Integer.parseInt(st.nextToken()) - 1; // 방향
				map[i][j] = new Fish(i, j, a, b);
				info[a] = map[i][j];

			}

		}

		map[0][0].isShark = true; // 0,0엔 상어를 넣고 출발
		Fish shark = map[0][0];
		dfs(map, info, shark, map[0][0].size);

		System.out.println(cnt);

		br.close();
	}

}
