package nijesmik.Week01;
import java.util.*;

public class 탑 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		Stack<Integer[]> stack = new Stack<>();

		// 배열로 안하고 sc.nextInt()로 하나씩 받으면 메모리 초과가 뜸!
		String[] arr = sc.nextLine().split(" ");
		for (int i = 0; i < n; i++) {
			Integer[] tower = {Integer.parseInt(arr[i]), i+1};
			while (!stack.isEmpty() && stack.peek()[0] < tower[0]) {
				stack.pop();
			}

			// 삼항연산자로 하면 시간 초과가 뜸...
			int idx = 0;
			if (!stack.isEmpty()) idx = stack.peek()[1];
			System.out.print(idx+" ");
			stack.add(tower);
		}
	}
}