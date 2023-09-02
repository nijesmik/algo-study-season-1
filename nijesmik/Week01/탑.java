package nijesmik.Week01;
import java.util.*;

public class 탑 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		Stack<Integer[]> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();

		// 배열로 안하고 sc.nextInt()로 하나씩 받으면 메모리 초과가 뜸!
		String[] arr = sc.nextLine().split(" ");
		for (int i = 0; i < n; i++) {
			Integer[] tower = {Integer.parseInt(arr[i]), i+1};
			while (!stack.isEmpty() && stack.peek()[0] < tower[0]) {
				stack.pop();
			}

			// 삼항연산자가 문제가 아니라 print가 느린게 문제였다
			int idx = stack.isEmpty() ? 0 : stack.peek()[1];
			// 따라서 stringbuilder로 한번에 출력하도록 변경
			sb.append(idx).append(" ");
			stack.add(tower);
		}
		System.out.println(sb);
	}
}