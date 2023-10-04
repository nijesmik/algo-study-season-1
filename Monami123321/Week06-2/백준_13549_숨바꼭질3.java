import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 백준_13549_숨바꼭질3 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int k = scanner.nextInt();

		int[] check = new int[200000]; // ?????
		for (int i = 0; i < check.length; i++) {
			check[i] = Integer.MAX_VALUE;

		}
		int len = check.length;

		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] { n, 0 });
		check[n] = 0;

		while (!queue.isEmpty()) {
			int[] node = queue.poll();
			int location = node[0];
			int time = node[1];


			if (location - 1 >= 0 && location - 1 < len && check[location - 1] > time + 1) {
				queue.add(new int[] { location - 1, time + 1 });
				check[location - 1] = time + 1;
			}
			if (location + 1 >= 0 && location + 1 < len && check[location + 1] > time + 1) {
				queue.add(new int[] { location + 1, time + 1 });
				check[location + 1] = time + 1;
			}
			if (location * 2 >= 0 && location * 2 < len && check[location * 2] > time) {
				queue.add(new int[] { location * 2, time });
				check[location * 2] = time;
			}

		}

		System.out.println(check[k]);

		scanner.close();
	}

}
