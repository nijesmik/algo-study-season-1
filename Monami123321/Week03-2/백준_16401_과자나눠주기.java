import java.util.Scanner;

public class 백준_16401_과자나눠주기 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int m = scanner.nextInt();
		int n = scanner.nextInt();

		int[] cookies = new int[n];
		int maxHeight = Integer.MIN_VALUE;

		for (int i = 0; i < n; i++) {
			cookies[i] = scanner.nextInt();
			maxHeight = Math.max(maxHeight, cookies[i]);
		}

		int start = 1;
		int end = maxHeight;

		while (start <= end) {
			int mid = (start + end) / 2;
			int sum = 0;

			for (int i = 0; i < n; i++) {
				sum += cookies[i] / mid;
			}

			if (sum >= m) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		if (end == 0) {
			System.out.println(0);
			return;
		}
		int sum = 0;
		for (int i = 0; i < n; i++) {
			sum += cookies[i] / end;
		}

		if (sum >= m)
			System.out.println(end);
		else
			System.out.println(0);

		scanner.close();
	}
}