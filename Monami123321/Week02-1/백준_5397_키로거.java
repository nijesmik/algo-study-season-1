import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 백준_5397_키로거 { // 틀림
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int testCases = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= testCases; tc++) {
			char[] arr = br.readLine().toCharArray();
			Stack<Character> left = new Stack<>();
			Stack<Character> right = new Stack<>();

			for (int i = 0; i < arr.length; i++) {
				switch (arr[i]) {
				case '-':
					if (!left.isEmpty()) {
						left.pop();
					}

					break;

				case '<':
					if (!left.isEmpty()) {
						right.push(left.pop());
					}

					break;

				case '>':
					if (!right.isEmpty()) {
						left.push(right.pop());
					}

					break;

				default:
					left.push(arr[i]);
					break;

				}

			}
			while (!right.isEmpty()) {
				left.push(right.pop());
			}
			for (int i = 0; i < left.size(); i++) {
				sb.append(left.get(i));

			}
			sb.append("\n");

		}
		System.out.println(sb);

		br.close();
	}

}
