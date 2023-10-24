package DATE1018;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BAEK16398_Kru {
	static int N;
	static int[][] edgArr;
	public static void main(String[] args) throws Exception {

		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		
		N = Integer.parseInt(bf.readLine());
		
		
		ArrayList<edge> edList = new ArrayList<>();
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j=0;j<N;j++) {
				
				if(j <= i) {
					st.nextToken();
				}else {
					edList.add(new edge(i,j, Integer.parseInt(st.nextToken())));
				}
			}
		}
		
		Collections.sort(edList);
		System.out.println(edList.toString());

		
		//find 메서드를 목적으로 하는 배열
		p = new int[N];
		for(int i=0;i<N;i++) {
			p[i] = i;
		}
		
		long sum = 0;
		int count = 0;
		for(edge e: edList) {
			
			//합집합 실패하면 
			if(!union(e.a, e.b))continue;
			
			sum += e.cost;
			if(++count == N-1)break;

			//System.out.println(Arrays.toString(p));
		}
		System.out.println(sum);
		
	}
	
	static int[] p;
	
	
	//부모 찾는 것
	static int find(int a) {
		if(p[a]==a) {
			return a;
		}
		return p[a] = find(p[a]);
	}
	
	//합치는 것
	static boolean union(int a, int b) {
		int aR = find(a);
		int bR = find(b);
		
		if(aR==bR) {
			return false;
		}
		
		p[bR] = aR;
		return true;
	}
	
	
	static class edge implements Comparable<edge>{
		int a;
		int b;
		int cost;
		
	public	edge(int a, int  b, int cost){
			
		if(a>b) {
			this.b = a;
			this.a = b;
			
		}else {
			this.a = a;
			this.b = b;
		}
		this.cost = cost;
		
		}

	@Override
	public int compareTo(edge o) {
	
		if(this.cost>o.cost) {
			return 1;
		}else if(this.cost<o.cost) {
			return -1;
		}else {
			return 0;
		}
		
	}
	
	public String toString() {
		return this.cost+"";
	}
	
	
	}

}
