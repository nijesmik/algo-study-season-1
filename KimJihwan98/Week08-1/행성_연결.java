import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 크루스칼 프림 둘다해봐
public class 행성_연결 {
	static int N;
	static long answer;
	static List<int[]>[] graph;
	static boolean[] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				int w = Integer.parseInt(st.nextToken());
				if(i<=j) continue;
				graph[i].add(new int[] {i, j, w});
				graph[j].add(new int[] {j, i, w});
			}
		}
		
		visited = new boolean[N];
		answer = 0;
		
		prim();
		System.out.println(answer);
	}
	static void prim() {
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
		visited = new boolean[N];
		visited[0] = true;
		int num = 1;
		pq.addAll(graph[0]);
		while(num<N) {
			int[] tmp = pq.poll();
			if(visited[tmp[1]]) continue;
			answer += tmp[2];
			pq.addAll(graph[tmp[1]]);
			visited[tmp[1]] = true;
			num++;
		}
	}
}
