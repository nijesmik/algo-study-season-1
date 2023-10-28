package nijesmik.Week09;
import java.util.*;

public class 퇴사_2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] time = new int[n+1];
		int[] revenue = new int[n+1];
		int[] dp = new int[n+1];
		for (int i = 0; i < n; i++) {
			time[i] = sc.nextInt();
			revenue[i] = sc.nextInt();
		}
		int now = n;
		while (now-- > 0) {
			int next = now + time[now];
			int total = 0;
			if (next <= n) total = dp[next] + revenue[now];
			dp[now] = Math.max(dp[now+1], total);
		}
		System.out.println(dp[0]);
	}
}