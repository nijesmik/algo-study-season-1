import java.util.ArrayList;
import java.util.Scanner;


public class 트리 {
	static int N, root, ans=0;
	static int[] parent;
	static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); 
		N = sc.nextInt();
		parent = new int[N];
		visited = new boolean[N];
		for(int i = 0; i < N; i++) {
			parent[i] = sc.nextInt();
			if(parent[i] == -1) root = i;
		}
		
		int d = sc.nextInt();
		
		delNode(d);
		countLeaf(root);
		System.out.println(ans);
		
	}
	public static void delNode(int d) {
		parent[d] = -2;
		visited[d] = true;
		
		for(int i = 0; i < N; i++) {
			if(parent[i] == d) delNode(i);
		}
	}
	
	public static void countLeaf(int idx) {
		boolean isLeaf = true;
		visited[idx] = true;
		if(parent[idx] != -2) {
			for(int i = 0; i < N; i++) {
				if(parent[i] == idx && visited[i] == false) {
					countLeaf(i);
					isLeaf = false;
				}
			}
			if(isLeaf) ans++;
		}
	}
}
