import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 백준_2164_카드2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		Queue<Integer> queue = new LinkedList<>();

		for (int i = 1; i <= n; i++) {
			queue.add(i);

		}
		while (queue.size() != 1) {
			queue.remove();
			if (queue.size() == 1) {
				break;
			}
			queue.add(queue.poll());

		}
		System.out.println(queue.poll());

		scanner.close();
	}

}
