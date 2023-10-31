package DATE1026;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BAEK10942 {
	static int N;
	static int M;
	static int[] arr;
	static int[][] dp;

	public static void main(String[] args) throws Exception {

		StringBuilder sb = new StringBuilder();
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(bf.readLine());
		arr = new int[N];
		dp = new int[N][N];

		st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			dp[i][i] = 1;
			if (i < N - 1 && arr[i] == arr[i + 1]) {
				dp[i][i + 1] = 1;
			}
		}

		for (int i = 0; i < N; i++) {

			dp(i, i);

			if (i == N - 1)
				continue;
			dp(i, i + 1);

		}

//		System.out.println();
//		for(int k = 0; k<N;k++) {
//			System.out.println(Arrays.toString(dp[k]));
//		}
//		System.out.println();
		
		M = Integer.parseInt(bf.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			sb.append(dp[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1]).append("\n");

		}
		System.out.println(sb);

	}

	static public void dp(int s, int e) {

		if (dp[s][e] != 1)
			return;

		int ns = s - 1;
		int ne = e + 1;

		if (ns < 0 || ne >= N || arr[ns] != arr[ne])
			return;
		dp[ns][ne] = 1;
		
		dp(ns,ne);

	}

}
