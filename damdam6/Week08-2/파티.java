package DATE1023;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BAEK1238 {
	
	//N명의 학생들 중 오고 가는데 가장 많은 시간을 소비하는 학생은 누구일지 구하여라.
	static int N; //학생수
	static int X; //파티위치
	static int M; //M개의 도로
	
	static LinkedList<edge>[] map;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		int start;
		int end;
		int time;
		map = new LinkedList[N+1];
		
		for(int i=1;i<N+1;i++) {
			map[i] = new LinkedList<>();
		}
		
	
		for(int i=0;i<M;i++) {
			 st = new StringTokenizer(bf.readLine());
			 start = Integer.parseInt(st.nextToken());
			 end = Integer.parseInt(st.nextToken());
			 time = Integer.parseInt(st.nextToken());
			 map[start].add(new edge(start,end, time));
		}
		
		
		int max = Integer.MIN_VALUE;
		for(int i=1;i<N+1;i++) {
			if(i==X)continue;
			int sum =  prim(i,X)+prim(X,i);
			max = Math.max(max,sum);
		}
		System.out.println(max);
		
	}
	//최단 거리 구하기
	
	static public int prim(int s, int e) {
		int[] vt = new int[N+1];
		Arrays.fill(vt, Integer.MAX_VALUE);
		PriorityQueue<edge> pqu = new PriorityQueue<>();
		//s에서 출발하는 간선을 모두 추가

		pqu.add(new edge(0,s,0));
		vt[s] = 0;
		edge tmp;
		while(!pqu.isEmpty()) {

			tmp = pqu.poll();
			

			
			
			if(map[tmp.end].size()==0)continue;
			
			for(edge o : map[tmp.end]) {
				
				if(vt[o.end]<= o.time+tmp.time ) {
					continue;
				}
				vt[o.end] = o.time+tmp.time;
				edge k = new edge(tmp.end, o.end, o.time+tmp.time);

				pqu.add( k);
				
			}
			
		}
				
		return vt[e];
	}
	
	static class edge implements Comparable<edge>{
		int start;
		int end;
		int time;
		edge(int start, int end, int time){
			this.start = start;
			this.end = end;
			this.time = time;
		}
		edge(int end, int time){
			this.end = end;
			this.time = time;
		}
		
		public String toString() {
			return this.start+" -> "+this.end+" = "+this.time;
		}
		public int compareTo(edge o) {
			if(this.time > o.time)return 1;
			else if(this.time < o.time)return -1;
			return 0;
		}
		
	}

}
