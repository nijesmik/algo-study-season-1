import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node2 {
	int num;
	int r;
	int c;
	public Node2(int num, int r, int c) {
		this.num = num;
		this.r = r;
		this.c = c;
	}
}

class Edge {
	int se;
	int ee;
	double w;
	public Edge(int se, int ee, double w) {
		this.se = se;
		this.ee = ee;
		this.w = w;
	}
}
public class 우주신과의_교감 {
	static int N, M, answer;
	static Node2[] gods;
	static List<Edge> edges;
	static boolean[] visited;
	static int[] p;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		answer = 0;
		gods = new Node2[N+1];
		for(int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine());
			gods[i] = new Node2(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		
		edges = new ArrayList<>();
		
		// 대표 선정
		p = new int[N+1];
		for(int i = 1; i < N+1; i++) {
			p[i] = i;
		}
		// 모든 노드가 연결되어있다고 생각
		for(int i = 1; i < N+1; i++) {
			for (int j = i; j < N+1; j++) {
				edges.add(new Edge(i, j, distance(i,j)));
			}
		}
		
		// 실제로 연결되어있는 노드들 방문처리
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int se = Integer.parseInt(st.nextToken());
			int ee = Integer.parseInt(st.nextToken());
			int px = findset(se);
			int py = findset(ee);
			
			if(px != py) {
				union(px, py);
			}
			
		}
		
		
	}
	
	static double distance(int god1, int god2) {
		return Math.sqrt(Math.pow(gods[god1].r - gods[god2].r, 2) + Math.pow(gods[god1].c - gods[god2].c, 2));
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
