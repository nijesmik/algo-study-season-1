import java.util.*;

public class 숨바꼭질_3 {
	static int start, end;
	static int[] delta = {0, -1, 1};
	static int[] dp;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		start = sc.nextInt(); end = sc.nextInt();
		dp = new int[100001];
		Arrays.fill(dp, Integer.MAX_VALUE);
		bfs();
		System.out.println(dp[end]);
	}
	static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		dp[start] = 0;
		while (!q.isEmpty()) {
			int cur = q.poll();
			delta[0] = cur;
			for (int i = 0; i < 3; i++) {
				int next = cur + delta[i];
				if (next < 0 || next > 100000) continue;
				int time = delta[i] == cur ? 0 : 1;
				dp[next] = Math.min(dp[next], dp[cur]+time);
				q.add(next);
				if (next == end) return;
			}
		}
	}
}