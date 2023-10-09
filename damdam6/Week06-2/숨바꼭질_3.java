package DATE1009;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BAEK13549 {
	
	static int N;
	static int K;
	
	public static void main(String[] args) throws Exception {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		Deque<pos> qu = new ArrayDeque<>();
		
		int[] vt = new int[100001];
		qu.add(new pos(N,0,true));
		vt[N] = 1;
		pos tmp = null;
all:		while(!qu.isEmpty()) {
			
			tmp = qu.poll();
			
			if(tmp.loc == K)break;
			
			int loc = tmp.loc;
			
			while(loc*2 <=100000 && vt[loc*2]!=1) {
				if(loc*2 == K) {
					break all;			
				}
				qu.add(new pos(loc*2,tmp.time,true));
				loc = loc*2;
				vt[loc] = 1;
			}
			
			//?? 아랫부분 순서를 바꾸면 틀림;;; 왜 그런지 확인 필요함
			if(tmp.loc-1>=0 && vt[tmp.loc-1]!=1) {
				vt[tmp.loc-1] = 1;
				qu.add(new pos(tmp.loc-1,tmp.time, false));
			}
			
			if(tmp.loc+1<=100000 && vt[tmp.loc+1]!=1) {
				vt[tmp.loc+1] = 1;
				qu.add(new pos(tmp.loc+1,tmp.time, false));
			}

				
		}
		
		System.out.println(tmp.time);
	}
	
	static class pos{
		int time;
		int loc;
		
		//true면 그대로
		 public pos(int loc, int time, boolean tf){
			this.loc = loc;
			if(tf) {
				this.time = time;
			}else {
				this.time = time+1;
			}
		}
	}

}
