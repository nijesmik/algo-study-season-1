package DATE1026;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BAEK15486 {

	static int N;
	static int[] tArr;
	static int[] pArr;

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(bf.readLine());
		tArr = new int[N];
		pArr = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			tArr[i] = Integer.parseInt(st.nextToken());
			pArr[i] = Integer.parseInt(st.nextToken());
		}

		int[] dpArr = new int[N + 1];
		Arrays.fill(dpArr, Integer.MIN_VALUE);
		dpArr[0] = 0;
		for (int i = 0; i < N + 1; i++) {

			if (i >= 1) {
				dpArr[i] = Math.max(dpArr[i], dpArr[i - 1]);
			}
			if (i == N)
				continue;

			int idx = i + tArr[i];
			if (idx < N + 1) {
				dpArr[idx] = Math.max(dpArr[idx], dpArr[i] + pArr[i]);
			}
			// System.out.println(Arrays.toString(dpArr));

		}
		System.out.println(dpArr[N]);

	}

}
