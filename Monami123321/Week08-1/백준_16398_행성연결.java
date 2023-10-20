import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_16398_행성연결 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		long[][] adjMatrix = new long[n + 1][n + 1];

		for (int i = 1; i < n + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < n + 1; j++) {
				adjMatrix[i][j] = Long.parseLong(st.nextToken());

			}

		} // 인접행렬 작성 끝

		long[] minEdges = new long[n + 1];
		for (int i = 1; i < minEdges.length; i++) {
			minEdges[i] = Long.MAX_VALUE;

		}
		boolean[] visited = new boolean[n + 1];
		minEdges[1] = 0;
		int pick = 0;
		long ans = 0;
		while (pick < n) {
			int minIndex = -1;
			long min = Long.MAX_VALUE;

			for (int i = 1; i < n + 1; i++) {
				if (visited[i])
					continue;
				if (minEdges[i] < min) {
					minIndex = i;
					min = minEdges[i];
				}

			}
			pick++;
			visited[minIndex] = true;
			ans += min;

			for (int i = 1; i < n + 1; i++) {
				if (visited[i] || adjMatrix[minIndex][i] == 0) {
					continue;
				}
				minEdges[i] = Math.min(minEdges[i], adjMatrix[minIndex][i]);

			}

		}
		System.out.println(ans);

		br.readLine();

	}

}
