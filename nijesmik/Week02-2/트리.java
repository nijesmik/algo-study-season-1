import java.util.*;

public class 트리 {
	static boolean[][] tree;
	static int ans, n, root;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		tree = new boolean[n][n];
		for (int child = 0; child < n; child++) {
			int parent = sc.nextInt();
			if (parent == -1) root = child;
			else tree[parent][child] = true;
		}
		int delete = sc.nextInt();
		for (int i = 0; i < n; i++) {
			tree[i][delete] = false;
		}
		ans = 0;
		if (delete != root)
			dfs(root);
		System.out.println(ans);
	}
	static void dfs(int idx) {
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			if (tree[idx][i]) dfs(i);
			else cnt++;
		}
		if (cnt == n) ans++;
	}
}