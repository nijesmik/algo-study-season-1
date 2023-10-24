package nijesmik.Week08;
import java.util.*;

public class 특정한_최단_경로 {
	public static void main(String[] args) {
		int inf = 1 << 29;
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(), e = sc.nextInt();
		int[][] cost = new int[n+1][n+1];
		for (int i = 1; i <= n; i++) {
			Arrays.fill(cost[i], inf);
			cost[i][i] = 0;
		}
		for (int i = 0; i < e; i++) {
			int a = sc.nextInt(), b = sc.nextInt();
			cost[a][b] = cost[b][a] = sc.nextInt();
		}
		int via1 = sc.nextInt(), via2 = sc.nextInt();
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					cost[i][j] = Math.min(cost[i][k] + cost[k][j], cost[i][j]);
				}
			}
		}
		int case1 = cost[1][via1] + cost[via1][via2] + cost[via2][n];
		int case2 = cost[1][via2] + cost[via2][via1] + cost[via1][n];
		int ans = Math.min(case1, case2);
		if (ans >= inf) ans = -1;
		System.out.println(ans);
	}
}