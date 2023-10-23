import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 행성_연결_크루스칼 {
	static int N;
	static long answer;
	static int[][] edges;
	static int[] p;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		edges = new int[(N*N-N)/2][3];
		int nedge = 0;
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				int w = Integer.parseInt(st.nextToken());
				if(i<=j) continue;
				edges[nedge][0] = i;
				edges[nedge][1] = j;
				edges[nedge++][2] = w;
			}
		}
		Arrays.sort(edges, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
		p = new int[N];
		for(int i = 0; i < N; i++) {
			p[i] = i;
		}
		
		int e = 0;
		int pick = 0;
		while(e < edges.length) {
			int px = findset(edges[e][0]);
			int py = findset(edges[e][1]);
			
			if(px!=py) {
				union(px, py);
				answer += edges[e][2];
				pick++;
			}
			e++;
			if(pick == N-1) break;
		}
		System.out.println(answer);
	}
	
	static void union(int x, int y) {
		p[y] = x;
	}
	
	static int findset(int x) {
		if(x != p[x]) {
			p[x] = findset(p[x]);
		}
		return p[x];
	}
}
