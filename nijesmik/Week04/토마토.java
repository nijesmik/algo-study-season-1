package nijesmik.Week04;
import java.util.*;

public class 토마토 {
	static int cLen, rLen, hLen, cnt;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st = new StringTokenizer(sc.nextLine());
		cLen = Integer.parseInt(st.nextToken());
		rLen = Integer.parseInt(st.nextToken());
		hLen = Integer.parseInt(st.nextToken());
		int[][][] tomato = new int[hLen][rLen][cLen];
		Queue<int[]> q = new LinkedList<>();
		for (int i = 0; i < hLen; i++) {
			for (int j = 0; j < rLen; j++) {
				int k = 0;
				for (String str : sc.nextLine().split(" ")) {
					int num = Integer.parseInt(str);
					tomato[i][j][k] = num;
					if (num == 1) q.add(new int[]{i, j, k});
					else if (num == 0) cnt++;
					k++;
				}
			}
		}
		int[] dh = {1, -1, 0, 0, 0, 0};
		int[] dr = {0, 0, 1, -1, 0, 0};
		int[] dc = {0, 0, 0, 0, 1, -1};
		int day = -1;
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				int[] cur = q.poll();
				for (int i = 0; i < 6; i++) {
					int nh = cur[0]+dh[i], nr = cur[1]+dr[i], nc = cur[2]+dc[i];
					if (nh < 0 || nh >= hLen || nr < 0 || nr >= rLen || nc < 0 || nc >= cLen)
					continue;
					if (tomato[nh][nr][nc] == 0) {
						tomato[nh][nr][nc] = tomato[cur[0]][cur[1]][cur[2]] + 1;
						q.add(new int[]{nh, nr, nc});
						cnt--;
					}
				}
			}
			day++;
		}
		System.out.println(cnt > 0 ? -1 : day);
	}
}