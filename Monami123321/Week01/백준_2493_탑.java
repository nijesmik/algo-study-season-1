import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 백준_2493_탑 { // 틀림
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		Stack<int[]> stack = new Stack<>();

		for (int i = 1; i <= n; i++) {
			int k = Integer.parseInt(st.nextToken());

			while (!stack.isEmpty()) {
				if (stack.peek()[0] > k) {
					sb.append(stack.peek()[1]).append(" ");
					stack.push(new int[] { k, i });
					break;
				} else {
					stack.pop();

				}
			}
			if (stack.isEmpty()) {
				sb.append(0).append(" ");
				stack.push(new int[] { k, i });
			}

		}
		System.out.println(sb);

		br.close();

	}

}
