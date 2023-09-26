package 백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 로또 {
	static int[] lotto;
	static int[] ans;
	static boolean[] visited;
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			if(N==0) break;
			visited = new boolean[N];
			lotto = new int[N];
			ans = new int[6];
			for(int i = 0; i < N; i++) {
				lotto[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(lotto);
			dfs(0,0);
			System.out.println();
		}
	}
	public static void dfs(int idx, int level) {
		if(level == 6) {
			for(int i = 0; i < 6; i++) {
				System.out.print(ans[i] + " ");
			}
			System.out.println();
			return;
		}
		for(int i = idx; i < N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				ans[level] = lotto[i];
				dfs(i, level+1);
				visited[i] = false;
			}
		}
	}
}
