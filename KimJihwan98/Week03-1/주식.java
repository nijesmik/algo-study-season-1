package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 주식 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] stock = new int[N];
			long sum = 0;
			for(int i = 0; i < N; i++) {
				stock[i] = Integer.parseInt(st.nextToken());
			}
			int i = N-1;
			int max = stock[i];
			while(i >= 0) {
				if(stock[i] <= max) {
					sum += max-stock[i];
					i--;
				} else {
					max = stock[i];
					i--;
				}
			}
			System.out.println(sum);
		}
	}
}
