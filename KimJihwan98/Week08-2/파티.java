import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


class Road {
	int v;
	int w;
	public Road(int v, int w) {
		this.v = v;
		this.w = w;
	}
}

public class 파티 {
	static int N, M, X;
	static List<Road>[] bridge;
	static int[] time;
	static PriorityQueue<Road> pq;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		bridge = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			bridge[i] = new ArrayList<>();
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			bridge[a].add(new Road(b, c));
		}
		time = new int[N+1];
		int max = 0;
		for(int i = 1; i < N+1; i++) {
			time[i] = dijkstra(i, X) + dijkstra(X, i);
			if(max < time[i]) max = time[i];
		}
		System.out.println(max);
	}
	
	static int dijkstra(int sidx, int eidx) {
		boolean[] visited = new boolean[N+1];
		int[] dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		pq = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
		
		pq.add(new Road(sidx, 0));
		dist[sidx] = 0;
		while(!pq.isEmpty()) {
			Road now = pq.poll();
			if(visited[now.v]) continue;
			visited[now.v] = true;
			for(Road next : bridge[now.v]) {
				if(dist[next.v] > dist[now.v] + next.w) {
					dist[next.v] = dist[now.v] + next.w;
					
					pq.offer(new Road(next.v, dist[next.v]));
				}
			}
		}
		
		return dist[eidx];
	}
}
