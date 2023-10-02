import java.util.Scanner;

public class 백준_6603_로또 {

	static StringBuilder sb;
	static int[] arr;
	static int k = 1;

	static void combination(boolean[] visited, int depth, int r) {

		if (r == 6) {

			for (int i = 0; i < visited.length; i++) {
				if (visited[i]) {
					sb.append(arr[i]).append(" ");
				}

			}
			sb.append("\n");

			return;

		}

		if (depth == k) {
			return;
		}

		visited[depth] = true;
		combination(visited, depth + 1, r + 1);
		visited[depth] = false;
		combination(visited, depth + 1, r);

	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		sb = new StringBuilder();

		while (true) {
			k = scanner.nextInt();
			if (k == 0) {
				break;
			}
			arr = new int[k];
			for (int i = 0; i < k; i++) {
				arr[i] = scanner.nextInt();

			}
			combination(new boolean[k], 0, 0);
			sb.append("\n");

		}
		System.out.println(sb);

		scanner.close();

	}

}