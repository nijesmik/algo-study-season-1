import java.io.*;
import java.util.*;

public class 멀티버스 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] planets = new int[n][m];
		for (int[] planet : planets) {
			st = new StringTokenizer(br.readLine());
			Set<Integer> set = new HashSet<>();
			for (int i = 0; i < m; i++) {
				int size = Integer.parseInt(st.nextToken());
				planet[i] = size;
				set.add(size);
			}
			List<Integer> list = new ArrayList<>(set);
			Collections.sort(list);
			for (int i = 0; i < m; i++) {
				planet[i] = Collections.binarySearch(list, planet[i]);
			}
		}
		int ans = 0;
		for (int i = 0; i < n-1; i++) {
			for (int j = i+1; j < n; j++) {
				if (Arrays.equals(planets[i], planets[j]))
					ans++;
			}
		}
		System.out.println(ans);
	}
}