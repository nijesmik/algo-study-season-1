package Stack;

import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Baekjoon_탑2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		int[] nums = new int[N];
		StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
		int idx = 0;
		while(st.hasMoreTokens()) {
			nums[idx++] = Integer.parseInt(st.nextToken());
		}
		
		Stack<Integer[]> stack = new Stack<>();
		int[] answer = new int[N];
		for(int i = 0; i < N; i++) {
			Integer[] tmp = {nums[i], i};
			if(i == 0) {
				answer[i] = 0;
				stack.add(tmp);
				continue;
			} 
			while(!stack.isEmpty() && nums[i] >= stack.peek()[0]) {
				stack.pop();
			}
			if(stack.isEmpty()) {
				stack.add(tmp);
				answer[i] = 0;
			}else if(nums[i] < stack.peek()[0]) {
				answer[i] = stack.peek()[1]+1;
				stack.add(tmp);
			}
		}
		for(int i = 0; i < N; i++) {
			System.out.print(answer[i] + " ");
		}
	}
}
