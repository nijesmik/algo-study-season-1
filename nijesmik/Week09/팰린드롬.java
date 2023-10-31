package nijesmik.Week09;
import java.util.Scanner;

public class 팰린드롬 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n+1];
		boolean[][] dp = new boolean[n+1][n+1];
		for (int i = 1; i <= n; i++) {
			arr[i] = sc.nextInt();
			dp[i][i] = true;
			if (arr[i] == arr[i-1]) dp[i-1][i] = true;
		}
		for (int i = n; i > 0; i--) {
			for (int j = i+1; j <= n; j++) {
				if (arr[i] == arr[j] && dp[i+1][j-1]) dp[i][j] = true;
			}
		}
		int m = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			int ans = dp[sc.nextInt()][sc.nextInt()] ? 1 : 0;
			sb.append(ans+"\n");
		}
		System.out.println(sb);
	}
}