import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] nums;
	static boolean[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		nums = new int[N+1];
		for(int i = 1; i < N+1; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		pelindrom();
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		int tmp = 0;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			tmp = dp[a][b] ? 1 : 0;
			sb.append(tmp + "\n");
		}
		System.out.println(sb);
	}
	
	static void pelindrom() {
		dp = new boolean[N+1][N+1];
		// 길이가 1일 때
		for(int i = 1; i<N+1; i++) {
			dp[i][i] = true;
		}
		
		//길이가 2일 때
		for(int i = 1; i < N; i++) {
			if(nums[i] == nums[i+1]) dp[i][i+1] = true;
		}
		
		//길이가 3이상일 때
		for(int i = N; i > 0; i--) {
			for(int j = 2; i + j < N+1; j++) {
				if(nums[i] == nums[i+j] && dp[i+1][i+j-1]) {
					dp[i][i+j] = true;
				}
			}
		}
		
	}
}
