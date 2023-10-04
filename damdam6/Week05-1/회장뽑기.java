package DATE0926;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BAEK2660 {

	static int N;
	static ArrayList<LinkedList<Integer>> rel;

	public static void main(String[] args) throws Exception {

		StringBuilder sb = new StringBuilder();
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bf.readLine());

		rel = new ArrayList<>(N + 1);
		for (int i = 0; i <= N; i++) {
			rel.add(new LinkedList<>());
		}

		StringTokenizer st = new StringTokenizer(bf.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		while (a != -1) {

			// 친구 관계 연결리스트
			rel.get(a).add(b);
			rel.get(b).add(a);

			st = new StringTokenizer(bf.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
		}
		
		minSc = Integer.MAX_VALUE;
		
		arr = new ArrayList<>();
		for(int i=1;i<N+1;i++) {
			bfs(i);
		}
		Collections.sort(arr);
		
		sb.append(minSc+" "+arr.size()).append("\n");
		
		for(int l: arr)sb.append(l).append(" ");
		
		System.out.println(sb);

	}
	
	static int minSc;
	static ArrayList<Integer> arr;
	public static boolean bfs(int start) {
		
		//System.out.println("------"+start+"----");
		int cnt = 0;
		int[] vt = new int[N + 1];
		Deque<p> dqu = new ArrayDeque<>();

		dqu.add(new p(start, 0));
		vt[start] = 1;
		p tmp = null;
		while (!dqu.isEmpty()) {

			///System.out.println(dqu.toString());
			tmp = dqu.poll();
			cnt++;
			for (int a : rel.get(tmp.num)) {

				if (vt[a] != 1) {
					vt[a] = 1;
					dqu.offer(new p(a, tmp));
				}

			}

		}
		
		if(cnt==N) {
			if(minSc > tmp.stage) {
				minSc = tmp.stage;
				arr.clear();
				arr.add(start);
			}else if(minSc == tmp.stage) {
				arr.add(start);
			}

			return true;
		}
		return false;
	}

	static class p {
		int num;
		int stage;

		public p(int num, p p) {
			super();
			this.num = num;
			this.stage = p.stage + 1;
		}

		public p(int num, int stage) {
			super();
			this.num = num;
			this.stage = stage;
		}
		
		public String toString() {
			return "num "+num+" stage "+stage+"\n";
		}

	}

}
