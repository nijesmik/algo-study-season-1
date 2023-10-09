package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 숨바꼭질3 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		// 같을떄에 생각해야지!
		if (N == K)
			System.out.println(0);
		else {
			int[] map = new int[100001];
			Arrays.fill(map, Integer.MAX_VALUE);

			Queue<int[]> queue = new LinkedList<>();
			queue.offer(new int[] { N, 0 });
			while (!queue.isEmpty()) {
				int[] now = queue.poll();
				if (now[0] * 2 <= 100000 && map[now[0] * 2] > now[1]) {
					map[now[0] * 2] = now[1];
					queue.offer(new int[] { now[0] * 2, now[1] });
				}
				if (now[0] - 1 >= 0 && map[now[0] - 1] > now[1] + 1) {
					map[now[0] - 1] = now[1] + 1;
					queue.offer(new int[] { now[0] - 1, now[1] + 1 });
				}
				if (now[0] + 1 <= 100000 && map[now[0] + 1] > now[1] + 1) {
					map[now[0] + 1] = now[1] + 1;
					queue.offer(new int[] { now[0] + 1, now[1] + 1 });
				}
			}
			System.out.println(map[K]);
		}
	}
}
