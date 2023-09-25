package DATE0925;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BAEK6603 {
	static int k;
	static num[] arr;
	static int[] pick;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {

		sb = new StringBuilder();
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		k = Integer.parseInt(st.nextToken());

		do {

			arr = new num[k];
			pick = new int[k];
			for (int i = 0; i < k; i++) {
				arr[i] = new num(Integer.parseInt(st.nextToken()));
			}

			Arrays.sort(arr);

			com(0, 0);
			sb.append("\n");
			st = new StringTokenizer(bf.readLine());
			k = Integer.parseInt(st.nextToken());
		} while (k != 0);

		System.out.println(sb);
	}

	static class num implements Comparable<num> {
		int val;
		boolean pick;

		public num(int val) {
			super();
			this.val = val;
			this.pick = false;
		}

		@Override
		public int compareTo(num o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.val, o.val);
		}

	}

	public static void com(int n, int r) {

		if (r == 6) {
			for (int i = 0; i < k; i++) {
				if (arr[i].pick) {
					sb.append(arr[i].val + " ");
				}
			}
			sb.append("\n");
			return;
		} else if (n == k) {
			return;
		} else {

			arr[n].pick = true;
			com(n + 1, r + 1);
			arr[n].pick = false;
			com(n + 1, r);

		}

		// 사이즈 6인 부분 집합 구하는 방법
	}

}
