package nijesmik.Week09;
import java.util.*;

public class 청소년_상어 {
	static int[] dr = {-1,-1,0,1,1,1,0,-1};
	static int[] dc = {0,-1,-1,-1,0,1,1,1};
	static class Shark {
		int[][] fish, dir, pos;
		int r, c, total;
		Shark(int[][] fish, int[][] dir, int[][] pos, int r, int c, int total) {
			this.fish = copy(fish);
			this.dir = copy(dir);
			this.pos = copy(pos);
			this.r = r;
			this.c = c;
			this.total = total;
		}
		int[][] copy(int[][] arr) {
			int[][] copy = new int[arr.length][];
			int i = 0;
			for (int[] row : arr) {
				copy[i++] = Arrays.copyOf(row, row.length);
			}
			return copy;
		}
		void eat() {
			int eatenFish = fish[r][c];
			fish[r][c] = 0;
			total += eatenFish;
			pos[eatenFish][0] = -1;
		}
		void fishmove() {
			for (int i = 1; i < 17; i++) {
				int x = pos[i][0], y = pos[i][1];
				if (x < 0) continue;
				int newDir = selectDir(x, y);
				if (newDir < 0) continue;
				dir[x][y] = newDir;
				swap(i, x+dr[newDir], y+dc[newDir]);
			}
		}
		int selectDir(int x, int y) {
			for (int i = 0; i < 8; i++) {
				int d = (dir[x][y] + i) % 8;
				if (fish[x+dr[d]][y+dc[d]] != 0)
					return d;
			}
			return -1;
		}
		void swap(int fishA, int br, int bc) {
			int fishB = fish[br][bc];
			int ar = pos[fishA][0], ac = pos[fishA][1];
			int dirA = dir[ar][ac], dirB = dir[br][bc];
			fish[ar][ac] = fishB; fish[br][bc] = fishA;
			dir[ar][ac] = dirB; dir[br][bc] = dirA;
			pos[fishA][0] = br; pos[fishA][1] = bc;
			if (fishB > 0) {
				pos[fishB][0] = ar; pos[fishB][1] = ac;
			}
		}
		// void print() {
		// 	for (int i = 1; i < 5; i++) {
		// 		System.out.print("[");
		// 		for (int j = 1; j < 5; j++) {
		// 			System.out.printf("%d ",fish[i][j]);
		// 		}
		// 		System.out.print("][");
		// 		for (int j = 1; j < 5; j++) {
		// 			System.out.printf("%d ",dir[i][j]);
		// 		}
		// 		System.out.println("]");
		// 	}
		// 	System.out.println("---");
		// }
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] fish = new int[6][6];
		int[][] dir = new int[6][6];
		int[][] pos = new int[17][2];
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 5; j++) {
				int fishNum = sc.nextInt();
				fish[i][j] = fishNum;
				dir[i][j] = sc.nextInt() - 1;
				pos[fishNum][0] = i;
				pos[fishNum][1] = j;
			}
		}
		int max = 0;
		Queue<Shark> q = new LinkedList<>();
		q.add(new Shark(fish, dir, pos, 1, 1, 0));
		while (!q.isEmpty()) {
			Shark cur = q.poll();
			cur.eat();
			cur.fishmove();
			max = Math.max(max, cur.total);
			int nr = cur.r, nc = cur.c, ndir = cur.dir[nr][nc];
			cur.fish[nr][nc] = -1;
			nr += dr[ndir]; nc += dc[ndir];
			while (nr > 0 && nr < 5 && nc > 0 && nc < 5) {
				if (cur.fish[nr][nc] > 0) {
					q.add(new Shark(cur.fish, cur.dir, cur.pos, nr, nc, cur.total));
				}
				nr += dr[ndir]; nc += dc[ndir];
			}
		}
		System.out.println(max);
	}
}