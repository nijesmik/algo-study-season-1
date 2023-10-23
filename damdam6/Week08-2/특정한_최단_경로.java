package DATE1020;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;



public class BAEK1504 {
	
	static int N;
	static int E;
	static int[][] mtx ;
	public static void main(String[] args) throws Exception{
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		mtx = new int[N+1][N+1];
		
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int dis = Integer.parseInt(st.nextToken());
			mtx[a][b] = dis;
			mtx[b][a] = dis;
		}
		st = new StringTokenizer(bf.readLine());
		int p = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		
		
		int dis1 = 0;
		chk = true;
		//getShort(1,p)+getShort(p,q)+ getShort(q,N)
		dis1 = getShort(1,p)+getShort(p,q)+ getShort(q,N);
		if(!chk) {
			dis1 = 0;
		}
		
		int dis2 = 0;
		chk = true;
		dis2 = getShort(1,q)+getShort(q,p)+ getShort(p,N);
		
		if(!chk) {
			dis2 = 0;
		}
		
		if(dis1 ==0 || dis2==0) {
			
			if(dis1==0&&dis2==0) {
				min = -1;
			}else if(dis1 !=0) {
				min = dis1;
			}else if(dis2 != 0) {
				min = dis2;
			}
			
		}else {
			min = Math.min(dis1, dis2);
		}
		

		System.out.println(min);

	}
	
	static boolean chk;
	static int min;
	
	static int[] dx = new int[] {0,0,-1,1};
	static int[] dy = new int[] {1,-1,0,0};
	
	static int nowDis;
	
	static public int getShort(int a, int b) {
		if(a==b)return 0;
		
		PriorityQueue<node> pqu = new PriorityQueue<>();
		
		int[] path = new int [N+1];
		Arrays.fill(path, Integer.MAX_VALUE);
		
		pqu.add(new node(a,0));
		node tmp;
		while(!pqu.isEmpty()) {
			
			tmp = pqu.poll();
			for(int i=1;i<N+1;i++) {
				if(mtx[tmp.a][i]==0)continue;
				
				if(path[i] > tmp.w+mtx[tmp.a][i]) {
					path[i] = tmp.w+mtx[tmp.a][i];
					pqu.add(new node(i, path[i]));
				}
			}
		}
		
		if(path[b] == Integer.MAX_VALUE) {
			chk = false;
			path[b] = -1;
		}
		return path[b];
	}
	
	static class node implements Comparable<node>{
		int a;
		int w;
		
		node(int a, int w){

			this.a = a;
			this.w = w;
		}

		@Override
		public int compareTo(node o) {
			if(this.w > o.w)return 1;
			else if(this.w<o.w)return -1;
			return 0;
		}
		
		
	}
	
	
}