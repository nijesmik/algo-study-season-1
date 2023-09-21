import java.io.*;
import java.util.*;

public class 선_긋기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] lines = new int[n][2];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			lines[i][0] = Integer.parseInt(st.nextToken());
			lines[i][1] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(lines, (a,b)-> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
		int sum = 0, lineEnd = lines[0][0];
		for (int[] line : lines) {
			if (line[0] > lineEnd) lineEnd = line[0];
			if (line[1] > lineEnd) {
				sum += line[1] - lineEnd;
				lineEnd = line[1];
			}
		}
		System.out.println(sum);
	}
}