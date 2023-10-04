import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 백준_2504_괄호의값 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), "([)]", true);
		Stack<String> stack = new Stack<>();
		int val = 1;
		int ans = 0;
		boolean flag = false;

		outer: while (st.hasMoreTokens()) {

			String tmp = st.nextToken();

			switch (tmp) {
			case "(":
				stack.push(tmp);
				val *= 2;
				flag = false;

				break;
			case "[":
				stack.push(tmp);
				val *= 3;
				flag = false;
				break;
			case ")":
				if (stack.isEmpty() || !stack.peek().equals("(")) {
					ans = 0;
					break outer;
				}
				if (flag) {
					val /= 2;
					stack.pop();
				} else {
					flag = true;
					ans += val;
					val /= 2;
					stack.pop();

				}

				break;
			case "]":
				if (stack.isEmpty() || !stack.peek().equals("[")) {
					ans = 0;
					break outer;
				}
				if (flag) {
					val /= 3;
					stack.pop();
				} else {
					flag = true;
					ans += val;
					val /= 3;
					stack.pop();

				}
				break;
			default:
				break;
			}

		}
		if (!stack.isEmpty()) {
			ans = 0;
		}

		System.out.println(ans);
		br.close();

	}

}
