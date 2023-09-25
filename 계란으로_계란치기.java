import java.util.Scanner;

public class 계란으로_계란치기 {
	static int[] egg, weight;
	static int n, max;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		egg = new int[n];
		weight = new int[n];
		for (int i = 0; i < n; i++) {
			egg[i] = sc.nextInt();
			weight[i] = sc.nextInt();
		}
		max = 0;
		dfs(0);
		System.out.println(max);
	}
	static void dfs(int idx) {
		if (idx == n) {
			getMax();
			return;
		}
		if (egg[idx] <= 0) {
			dfs(idx+1);
			return;
		}
		for (int i = 0; i < n; i++) {
			if (i == idx || egg[i] <= 0) continue;
			egg[i] -= weight[idx];
			egg[idx] -= weight[i];
			dfs(idx+1);
			egg[i] += weight[idx];
			egg[idx] += weight[i];
		}
		getMax();
	}
	static void getMax() {
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			if (egg[i] <= 0) cnt++;
		}
		max = Math.max(cnt, max);
	}
}