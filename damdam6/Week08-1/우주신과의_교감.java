package DATE1020;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BAEK1774 {
	
	static int N;
	static int M;
	static god[] arr;
	public static void main(String[] args) throws Exception{
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new god[N+1];
		
		arr[0] = new god(0,0); //쓰레기 값
		
		for(int i=1;i<N+1;i++) {
			st = new StringTokenizer(bf.readLine());
			arr[i] = new god(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));		
		}
		
		p = new int[N+1];
		
		for(int i=1;i<N+1;i++) {
			p[i]= i;
		}
		
		double disAll =0;
		
		int[] usedCnt = new int[N+1];
		int cnt = 0;
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a,b);
			
			if(usedCnt[a] != 1) {
				cnt++;
				usedCnt[a] =1;
			}
			if(usedCnt[b] != 1) {
				cnt++;
				usedCnt[b] =1;
			
			}
			
		}
		
		//전체 길이값 저장하기
		ArrayList<edge> edgeArr = new ArrayList<>();
		
		for(int i=1;i<N+1;i++) {
			for(int j=i+1;j<N+1;j++) {
				edgeArr.add(new edge(i,j));
				
			}
		}
		
		Collections.sort(edgeArr);
		
		
		for(edge e: edgeArr) {
			
			if(!union(e.a, e.b))continue;
			

			//System.out.println(e);
			disAll+=e.dis;
			
			if(cnt==N-1)break;
		}
		
		System.out.printf("%.2f",disAll);
		
	}
	
	static class edge implements Comparable<edge>{
		int a;
		int b;
		double dis;
		
		edge(int a, int b){
			if(a<b) {
				this.a = a;
				this.b = b;
			}else {
				this.a = b;
				this.b = a;
			}
			
			
			this.dis = Math.sqrt((Math.pow( arr[a].x - arr[b].x, 2) + Math.pow(arr[a].y-arr[b].y,2)));
			
		}

		@Override
		public int compareTo(edge o) {
			
			if(this.dis > o.dis) {
				return 1;
			}else if(this.dis < o.dis) {
				return -1;
			}
			
			return 0;
		}
		
		public String toString() {
			return "[a "+a+"(x"+arr[a].x+" y "+arr[a].y+") b "+b+"(x"+arr[b].x+" y "+arr[b].y+")dis "+dis+"]";
		}
	}
	
	static int[] p;
	static public boolean union(int a, int b) {
		
		if(find(b)==find(a))return false;
		
		p[find(a)] = find(b);
		
		return true;		
		
	}
	
	static public int find(int a) {
		
		if(a==p[a])return a;
		
				
		return p[a] = find(p[a]);
	}
	
	
	static class god{
		int x;
		int y;
		
		god(int x, int y){
			this.x = x;
			this.y = y;
		}
	}

}
