package DATE1106;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class SWEA13501
{
	static int N;
	static int M;
	static int L;
	static int maxIdx;
	static int headIdx;
	static linkL[] arr;
	public static void main(String args[]) throws Exception
	{

		//System.setIn(new FileInputStream("res/sample_input.txt"));

		StringTokenizer st;
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T;
		T=Integer.parseInt(bf.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			st = new StringTokenizer(bf.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			maxIdx = 0;
			arr = new linkL[6001];
			st = new StringTokenizer(bf.readLine());
			for(int i=0;i<N;i++) {
				arr[i] = new linkL(Integer.parseInt(st.nextToken()),i+1);
			}
			arr[N-1].tail = -1;
			maxIdx = N;
			headIdx = 0;
			
			for(int m=0;m<M;m++) {
				st = new StringTokenizer(bf.readLine());
				char c = st.nextToken().charAt(0);
				switch(c) {
				case 'I':
					int idx = Integer.parseInt(st.nextToken());
					int val = Integer.parseInt(st.nextToken());
					insert(idx, val);
					break;
				case 'D':
				case 'C':
				}

			}
			System.out.println(Arrays.toString(arr));
		}
	}
	
	static void insert(int idx, int val) {
		
		if(idx == 0) {
			arr[maxIdx] = new linkL(val,headIdx);
			headIdx = maxIdx++;
			return;	
		}
		
		int now  = headIdx;
		int nxt = headIdx;
		int cnt = 0;
		while(cnt < idx) {
			cnt++;
			now = nxt;
			nxt = arr[now].tail;
		}
		
		arr[maxIdx] = new linkL(val,nxt);
		arr[now].tail = maxIdx++;
		
	}
	
	static void delete(int idx, int val) {

		
	}
	
	static class linkL{
		int val;
		int tail;
		
		
		linkL(int val, int tail){
			this.tail = tail;
			this.val = val;
		}
		linkL(linkL l){
			this.val = l.val;
			this.tail = l.tail;
		}


		@Override
		public String toString() {
			return "[val=" + val + "] = [" + tail + "]";
		}
		
		
	}
}