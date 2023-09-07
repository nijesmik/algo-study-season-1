package Queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 카드2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		Queue<Integer> queue = new LinkedList<>();
		for(int i = 1; i <= N; i++) {
			queue.add(i);
		}
		int answer = queue.peek();
		while(queue.size() != 1) {
			queue.poll();
			answer = queue.poll();
			queue.add(answer);
		}
		System.out.println(answer);
	}
}
