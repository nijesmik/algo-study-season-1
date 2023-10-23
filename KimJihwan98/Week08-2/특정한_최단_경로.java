import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


class Node {
	int v;
	int w;
	
	public Node(int v, int w) {
		this.v = v;
		this.w = w;
	}
}

public class 특정한_최단_경로 {
	static int N, E, v1, v2;
	static final int IF = Integer.MAX_VALUE;
	static boolean flag;
	static List<Node>[] graph;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N+1];
		for(int i = 1; i < N+1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int sv = Integer.parseInt(st.nextToken());
			int ev = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[sv].add(new Node(ev, w));
			graph[ev].add(new Node(sv, w));
		}
		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());
		
		flag = false;
		int v1v2 = dijkstra(v1,v2);
		int min1 = dijkstra(1, v1) + v1v2 + dijkstra(v2, N);
		int min2 = dijkstra(1, v2) + v1v2 + dijkstra(v1, N);
		if(flag) System.out.println(-1);
		else System.out.println(min1>min2 ? min2 : min1);
	}
	
	public static int dijkstra(int sidx, int eidx) {
		boolean[] visited = new boolean[N+1];
		int[] dist = new int[N+1];
		Arrays.fill(dist, IF);
		dist[sidx] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
		pq.offer(new Node(sidx, 0));
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			if(visited[now.v]) continue;
			visited[now.v] = true;
			
			for(Node next : graph[now.v]) {
				if(dist[next.v] > dist[now.v] + next.w ) {
					dist[next.v] = dist[now.v] + next.w;
					
					pq.offer(new Node(next.v, dist[next.v]));
				}
			}
		}
		if(dist[eidx]==IF) flag = true;
		return dist[eidx];
	}
}
