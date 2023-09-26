import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N;
	static int[][] arr;
	static int ans;
	static int max;

	static void dfs(int L) {
		if (L == N) {
			int cnt = 0;
			for(int i=0; i<N; i++) {
				if(arr[i][0]<=0) cnt++;
			}
			
			max = Math.max(max, cnt);
			return;
		} else {
			if (arr[L][0] <= 0)
				dfs(L + 1);
			else {
				boolean chk = false;
				for (int i = 0; i < N; i++) {
					if (arr[i][0] <= 0 || i == L) 
						continue;
						chk = true;
						arr[L][0] = arr[L][0] - arr[i][1];
						arr[i][0] = arr[i][0] - arr[L][1];
						dfs(L+1);
						arr[L][0] = arr[L][0] + arr[i][1];
						arr[i][0] = arr[i][0] + arr[L][1];
					
				}
				if(!chk)
					dfs(L + 1);
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[N][2];

		for (int i = 0; i < N; i++) {
			arr[i][0] = sc.nextInt();
			arr[i][1] = sc.nextInt();
		}
		max = Integer.MIN_VALUE;
		dfs(0);
		System.out.println(max);

	}
}
