import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_10942_펠린드롬 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[n + 1];
		for (int i = 1; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());

		}
		int m = Integer.parseInt(br.readLine());

		int[][] dp = new int[n + 1][n + 1];
		for (int i = 1; i < dp.length; i++) {
			dp[i][i] = 1;

		}
		for (int i = n+1; i >= 1; i--) {
			for (int j = i + 1; j < dp.length; j++) {
				if (arr[i] == arr[j]) {
					if (j - i == 1) {
						dp[i][j] = 1;
						continue;
					}
					if (dp[i + 1][j - 1] == 1) {
						dp[i][j] = 1;

					}

				} else {
					dp[i][j] = 0;

				}

				// dp[i][j] = dp[j][i];
			}

		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			sb.append(dp[a][b]).append("\n");

		}
		System.out.print(sb);

		br.close();
	}

}
