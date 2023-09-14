import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 트리와쿼리 {
	static int N, R, Q;
	static int[] connect, size;
	static ArrayList<Integer>[] tree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		size = new int[N+1];
		tree = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<>();
		}
		for(int i = 1; i < N; i++) {
			StringTokenizer tmp = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(tmp.nextToken());
			int b = Integer.parseInt(tmp.nextToken());
			tree[a].add(b);
			tree[b].add(a);
		}
		
		countNode(R, -1);
		for(int i = 0; i < Q; i++) {
			System.out.println(size[Integer.parseInt(br.readLine())]);
		}
	}
	
	static void countNode(int root, int parent) {
		size[root] = 1;
		
		for(int i : tree[root]) {
			if(i != parent) {
				countNode(i, root);
				size[root] += size[i];
			}
		}
	}
}
