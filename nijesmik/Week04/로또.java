package nijesmik.Week04;
import java.util.Scanner;

public class 로또 {
	static int n;
	static StringBuilder sb;
	static String[] str;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sb = new StringBuilder();
		while (true) {
			str = sc.nextLine().split(" ");
			n = Integer.parseInt(str[0]);
			if (n == 0) break;
			dfs(0, 0, "");
			sb.append("\n");
		}
		System.out.println(sb);
	}
	static void dfs(int idx, int level, String s) {
		if (idx == n) {
			if (level == 6)
				sb.append(s+"\n");
			return;
		}
		dfs(idx+1, level+1, s+str[idx+1]+" ");
		dfs(idx+1, level, s);
	}
}