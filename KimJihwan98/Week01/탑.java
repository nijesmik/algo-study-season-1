package Stack;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Baekjoon_탑 {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
		int[] nums = new int[st.countTokens()];
		int i = 0;
		while (st.hasMoreTokens()) {
			nums[i++] = Integer.parseInt(st.nextToken());
		}

		Stack<Integer> stack = new Stack<>();
		int[] answer = new int[nums.length];
		int idx = 1;
		for (i = 0; i < N; i++) {
			if (i == 0) {
				stack.add(i);
				answer[i] = 0;
			} else if (nums[stack.peek()] <= nums[i]) {
				while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
					stack.pop();
				}
				if (stack.isEmpty()) {
					stack.add(i);
					answer[i] = 0;
				}
				else {
					answer[i] = stack.peek()+1;
					stack.add(i);
				}
			} else if( nums[stack.peek()] > nums[i]) {
				answer[i] = stack.peek() + 1;
				stack.add(i);
			}
		}

		for(i = 0; i < answer.length; i++) {
			System.out.print(answer[i] + " ");
		}
	}
}
