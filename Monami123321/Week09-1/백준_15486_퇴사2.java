import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_15486_퇴사2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());

		int[] dp = new int[n + 2];

		int max = Integer.MIN_VALUE;
		for (int i = 1; i < n + 1; i++) {
			st = new StringTokenizer(br.readLine());
			// i일에 a만큼 일해야 b를 벌 수 있음
			// 현재 dp테이블에 적혀있는 값과 비교하여 누적합이 더 크면 갱신
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			max = Math.max(max, dp[i]);
			if (i + a > n + 1) {
				continue;
			}
			dp[i + a] = Math.max(max + b, dp[i + a]);

		}
		System.out.println(Math.max(max, dp[n + 1]));

		br.close();
	}

}