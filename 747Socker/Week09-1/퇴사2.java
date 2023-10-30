package pract2;

import java.util.*;

class bj_15486_dp_퇴사2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int max = -1;
		int[] dp = new int[N+2];
		int[][] input = new int[N+2][2];
		for(int i=1; i<=N; i++) {
			int pt = sc.nextInt();
			int ps = sc.nextInt();
			input[i][0]=pt;
			input[i][1]=ps;
		}
		
		for(int i=1; i<=N+1; i++) {
			if(max<dp[i]) {
				max = dp[i];
			}
			int d = i+input[i][0];
			if(d<N+2) {
				dp[d] = Math.max(dp[d], max+input[i][1]);
			}
		}
		System.out.println(max);
	}
}