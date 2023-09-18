package BAEK0918;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BAEK11501 {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(bf.readLine());
			int[] arr = new int[N];
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for(int i=0;i<N;i++) {
				arr[i]= Integer.parseInt(st.nextToken());
			}
			int tmpmax =arr[N-1];
			int rev=0;
			for(int i=N-2;i>=0;i--) {
				if(arr[i]>tmpmax) {
					tmpmax = arr[i];
					continue;
				}
				rev+=tmpmax-arr[i];
			}
		sb.append(rev).append("\n");	
		}
		
		System.out.println(sb);
	}

}
