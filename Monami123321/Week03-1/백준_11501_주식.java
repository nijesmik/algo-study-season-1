import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_11501_주식 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int testCases = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= testCases; tc++) {
			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int[] arr = new int[n];

			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());

			}

			long ans = 0;
			int max = 0;
			for (int i = n - 1; i >= 0; i--) {
				if (arr[i] > max) {
					max = arr[i];
				} else {
					ans += max - arr[i];

				}

			}

			sb.append(ans).append("\n");

		}
		System.out.println(sb);

		br.close();

	}

}
