package nijesmik.Week08;
import java.util.*;

public class 파티 {
	public static void main(String[] args) {
		int inf = 1 << 29;
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(), e = sc.nextInt(), x = sc.nextInt();
		int[][] dist = new int[n+1][n+1];
		for (int i = 1; i <= n; i++) {
			Arrays.fill(dist[i], inf);
			dist[i][i] = 0;
		}
		for (int i = 0; i < e; i++) {
			int a = sc.nextInt(), b = sc.nextInt();
			dist[a][b] = sc.nextInt();
		}
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					dist[i][j] = Math.min(dist[i][k] + dist[k][j], dist[i][j]);
				}
			}
		}
		int max = 0;
		for (int i = 1; i <= n; i++) {
			max = Math.max(max, dist[i][x]+dist[x][i]);
		}
		System.out.println(max);
	}
}