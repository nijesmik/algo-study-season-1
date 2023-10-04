package DATE0926;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BAEK5567 {

	public static void main(String[] args) throws Exception {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(bf.readLine());
		int m = Integer.parseInt(bf.readLine());

		int[] vt = new int[n + 1];
		LinkedList<Integer>[] rel = new LinkedList[n + 1];
		for (int i = 0; i <= n; i++) {
			rel[i] = new LinkedList<>();
		}

		StringTokenizer st;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			rel[a].add(b);
			rel[b].add(a);
		}


		Deque<Integer> dqu = new ArrayDeque<>();

		int tmp = 1;
		vt[1] = 1;

		// 친구 한 다리
		while (!rel[tmp].isEmpty()) {
			int k = rel[tmp].poll();

			if (vt[k] != 1) {
				dqu.offer(k);
				vt[k] = 1;
			}
		}

		
		int cnt =0 ;
		while (!dqu.isEmpty()) {
			tmp = dqu.poll();
			cnt++;
			while (!rel[tmp].isEmpty()) {
				int k = rel[tmp].poll();

				if (vt[k] != 1) {
					cnt++;
					vt[k] = 1;

				}
			}
		}
		

		System.out.println(cnt);

	}

}
