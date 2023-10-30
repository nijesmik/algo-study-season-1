package pract2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class bj_re_dp_펠린드롬 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N+1];
		for(int i=1; i<=N; i++) {
			arr[i]=sc.nextInt();
		}
		
		boolean[][] dp = new boolean[N+1][N+1];
		
		for(int i=0; i<N; i++) {
			dp[i][i]=true;
			
		}
		for(int i=1; i<N; i++) {
			if(arr[i]==arr[i+1]) {
				dp[i][i+1]=true;
			}
			
		}
		
		for(int i=2; i<N; i++) {
			for(int j=1; i+j<=N;j++) {
				if(dp[j+1][j-1+i] && arr[j]==arr[i+j] ) {
					dp[j][j+i]=true;
				}
			}
		}
		int M = sc.nextInt();
		for(int i=0; i<M;i++) {
			int s = sc.nextInt();
			int e= sc.nextInt();
			System.out.println(dp[s][e] ? 1:0);
		}
	}
}
