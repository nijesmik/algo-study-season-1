import java.io.*;
import java.util.*;

public class 과자_나눠주기 {
	static Integer[] snacks;
	static int n, m;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		snacks = new Integer[m];
		int start = 1, end = 0, result = 0;
		for (int i = 0; i < m; i++) {
			snacks[i] = Integer.parseInt(st.nextToken());
			end = Math.max(end, snacks[i]);
		}
		while (start <= end) {
			int mid = (start + end) / 2;
			if (check(mid)) {
				result = mid;
				start = mid + 1;
			}
			else end = mid - 1;
		}
		System.out.println(result);
	}
	static boolean check(int cmp) {
		int cnt = 0;
		for (int i = 0; i < m; i++) {
			cnt += snacks[m-1-i] / cmp;
			if (cnt >= n) return true;
		}
		return false;
	}
}