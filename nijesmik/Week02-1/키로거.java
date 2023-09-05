import java.util.*;

public class 키로거 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for (int i = 0; i < n; i++) {
			ArrayDeque<Character> front = new ArrayDeque<>();
			Stack<Character> rear = new Stack<>();
			for (char c : sc.next().toCharArray()) {
				if (c == '<') {
					if (!front.isEmpty())
						rear.add(front.pollLast());
				} else if (c == '>') {
					if (!rear.isEmpty())
						front.addLast(rear.pop());
				} else if (c == '-') {
					if (!front.isEmpty())
						front.pollLast();
				} else {
					front.add(c);
				}
			}
			StringBuilder sb = new StringBuilder();
			while (!front.isEmpty())
				sb.append(front.pollFirst());
			while (!rear.isEmpty())
				sb.append(rear.pop());
			System.out.println(sb);
		}
	}
}