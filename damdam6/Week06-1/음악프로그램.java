package DATE1009;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BAEK2623 {

	public static void main(String[] args) throws Exception {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(bf.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] singer = new int[N + 1];
		
		LinkedList<Integer>[] link = new LinkedList[N+1];
		
		 for (int i = 0; i <= N; i++) {
	            link[i] = new LinkedList<>();
	        }

		for (int i = 0; i < M; i++) {

			st = new StringTokenizer(bf.readLine());

			int c = Integer.parseInt(st.nextToken());
			int front = 0;
			
			for (int j = 0; j < c; j++) {
				

				if (j == 0) {
					front = Integer.parseInt(st.nextToken());
					continue;
				}
				
				int con = Integer.parseInt(st.nextToken());
				singer[con]++;
				//특정 값을 삭제하면 어디 간선을 삭제해야하는지 확인
				//front - backbackback
				link[front].add(con);
				front = con;

			}

		}
		//System.out.println(Arrays.toString(singer));
		
		Deque<Integer> qu = new ArrayDeque<>();
		
		
		for(int i=1;i<N+1;i++) {
			if(singer[i]==0)qu.add(i);
		}
		
		
		int tmp;
		while(!qu.isEmpty()) {
			
			tmp = qu.poll();
			sb.append(tmp).append("\n");
			
			while(!link[tmp].isEmpty()) {
				int ed = link[tmp].poll();
				singer[ed]--;
				if(singer[ed]==0)qu.add(ed);
			}
			

			
		}
		if(sb.toString().split("\n").length < N) {
		    System.out.println(0); // or any other indication of an error
		} else {
		    System.out.println(sb);
		}
	}
	

}
