package Week05;
import java.util.*;

public class 회장뽑기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] graph = new int[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(graph[i], 100);
			graph[i][i] = 0;
		}
		while (true) {
			int a = sc.nextInt() - 1;
			if (a < 0) break;
			int b = sc.nextInt() - 1;
			graph[a][b] = 1;
			graph[b][a] = 1;
		}
		for (int i = 0; i < n; i++) {
			for (int a = 0; a < n; a++) {
				for (int b = 0; b < n; b++) {
					int tmp = graph[a][i] + graph[i][b];
					graph[a][b] = Math.min(tmp, graph[a][b]);
				}
			}
		}
		int max;
		int min = 100;
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			max = 0;
			for (int j = 0; j < n; j++) {
				max = Math.max(max, graph[i][j]);
			}
			if (max < min) {
				list = new ArrayList<>();
				list.add(i);
				min = max;
			} else if (max == min) {
				list.add(i);
			}
		}
		System.out.printf("%d %d\n", min, list.size());
		for (int i : list) {
			System.out.print(++i+" ");
		}
	}
}