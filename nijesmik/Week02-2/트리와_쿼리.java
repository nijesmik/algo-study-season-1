import java.util.*;

public class 트리와_쿼리 {
	static class Node {
		List<Integer> connect;
		int childCnt;
		
		Node() {
			connect = new ArrayList<>();
			childCnt = 1;
		}
	}
	static Node[] tree;
	public static void main(String[] args) {
		Scanner sc  = new Scanner(System.in);
		int n = sc.nextInt(), r = sc.nextInt(), q = sc.nextInt();
		tree = new Node[n+1];
		for (int i = 1; i <= n; i++) tree[i] = new Node();
		for (int i = 1; i < n; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			tree[a].connect.add(b);
			tree[b].connect.add(a);
		}
		dfs(r, 0);
		for (int i = 0; i < q; i++) {
			System.out.println(tree[sc.nextInt()].childCnt);
		}
	}
	static int dfs(int cur, int parent) {
		for (int child : tree[cur].connect) {
			if (child == parent) continue;
			tree[cur].childCnt += dfs(child, cur);
		}
		return tree[cur].childCnt;
	}
}