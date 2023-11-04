import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class 백준_14002_가장긴증가하는부분수열4 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n + 1]; // 주어진 수열
		int[] prev = new int[n + 1]; // arr[i]가 LIS 마지막에 섰을 때, 그 앞에 서있는 애 인덱스
		int[] dp = new int[n + 1]; // arr[i]를 마지막에 세웠을 때 LIS 길이

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			dp[i] = 1;

		}
		int maxIndex = 0;
		int max = -1;

		for (int i = 1; i < n + 1; i++) {
			int prevIndex = 0;
			for (int j = 1; j < i; j++) {
				if (arr[i] > arr[j]) {
					if (dp[i] < dp[j] + 1) {
						dp[i] = dp[j] + 1;
						prevIndex = j;

					}
				}

			}
			if (dp[i] > max) {
				max = dp[i];
				maxIndex = i;
			}
			prev[i] = prevIndex;

		}
		// System.out.println(Arrays.toString(prev));
		// System.out.println(maxIndex);

		Stack<Integer> stack = new Stack<Integer>();

		while (maxIndex != 0) {
			stack.add(arr[maxIndex]);
			maxIndex = prev[maxIndex];
		}
		sb.append(max).append("\n");
		while (!stack.isEmpty()) {
			sb.append(stack.pop()).append(" ");
		}

		System.out.print(sb);

		br.close();
	}

}
