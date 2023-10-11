package DATE1010;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BAEK13549_DP {
	
	static int N;
	static int K;
	static int[] arr;
	
	public static void main(String[] args) throws Exception {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[100001];
		Arrays.fill(arr, Integer.MAX_VALUE);
		arr[N] =0;
		
		//현재 점보다 뒤 -> 뒤로 가는 방법 밖에 없음
		for(int i=N-1;i>=0;i--) {
			arr[i] = N-i;
		}
		
		// arr[a]
		//짝수일 때 -> arr[a/2] or arr[a-1]+1
		//홀수일 때 -> arr[(a-1)/2]+1 or arr[(a+1)/2]+1 or arr[a-1]+1
		
		for(int i=N+1;i<100001;i++) {
			if(i%2==0) {
				
				arr[i] = Math.min(arr[i/2], arr[i-1]+1);
				
			}else {
				arr[i] = Math.min(arr[i-1]+1, arr[(i-1)/2]+1);
				arr[i] = Math.min(arr[i], arr[(i+1)/2]+1);
			}
		}
		System.out.println(arr[K]);
		
	}
	
	

}
