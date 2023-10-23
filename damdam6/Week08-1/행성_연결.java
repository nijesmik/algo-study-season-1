package DATE1017;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.PriorityQueue;
import java.util.SortedMap;
import java.util.StringTokenizer;

public class BAEK16398 {
	static int N;
	static int[][] edgArr;
	public static void main(String[] args) throws Exception {
		ArrayList<Integer> arr = new ArrayList<>();
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		
		
		N = Integer.parseInt(bf.readLine());
		
		edgArr = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j=0;j<N;j++) {
				edgArr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		

		System.out.println(draw(0));
	}
	//시작점이 idx일때
	
	public static int draw(int idx) {
	
		int sum = 0;
		
		int[] vt = new int[N];
		PriorityQueue<edge> pqu = new PriorityQueue<>();
		
		vt[idx] = 1;
		for(int i=0;i<N;i++) {
			if(edgArr[idx][i]==0)continue;
			pqu.add(new edge(idx, i, edgArr[idx][i]));
		}
		
		edge tmp;
		while(!pqu.isEmpty()) {
			tmp = pqu.poll();
			if(vt[tmp.end]==1)continue;
			vt[tmp.end] = 1;
			sum += tmp.consum;
			for(int i=0;i<N;i++) {
				if(edgArr[tmp.end][i]==0)continue;
				if(vt[i]==1)continue;
				pqu.add(new edge(tmp.end, i, edgArr[tmp.end][i]));
			}
			
		}
		
		return sum;
	}
	
	static class edge implements Comparable<edge>{
		
		int start;
		int end;
		
		int consum;
		
		edge(int start, int  end, int consum){
			this.start = start;
			this.end = end;
			this.consum = consum;
		}

		@Override
		public int compareTo(edge o) {
			if(this.consum > o.consum) {
				return 1;
			}else if(this.consum < o.consum) {
				return -1;
			}
			return 0;
		}
		
		
		
	}

}
