package nijesmik.Week01;
import java.util.*;

public class 괄호의_값 {
	public static void main(String[] args) {
		Stack<Character> stack = new Stack<>();
		Scanner sc = new Scanner(System.in);
		int ans = 0, tmp = 1;
		char prev = 0;
		for (char c : sc.next().toCharArray()) {
			if (c == '(') {
				tmp *= 2;
				stack.push(c);
			} else if (c == '[') {
				tmp *= 3;
				stack.push(c);
			} else if (stack.empty()) {
				ans = 0;
				break;
			} else if (c == ']' && stack.peek() == '[') {
				if (prev == '[') ans += tmp;
				stack.pop();
				tmp /= 3;
			} else if (c == ')' && stack.peek() == '(') {
				if (prev == '(') ans += tmp;
				stack.pop();
				tmp /= 2;
			} else {
				ans = 0;
				break;
			}
			prev = c;
		}
		if (!stack.empty()) ans = 0;
		System.out.println(ans);
	}
}