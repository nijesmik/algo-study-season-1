package 이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

//진짜 이문제 왤케어려움
public class 멀티버스_Ⅱ {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[][] universe = new int[M][N];
		List<Integer>[] list = new ArrayList[M];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			Set<Integer> set = new HashSet<>();
			for(int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				universe[i][j] = num;
				set.add(num);
			}
			
			list[i] = new ArrayList<>(set);
			Collections.sort(list[i]);
		}
		
		for(int i = 0; i < M; i++) {
			for(int j = 0 ; j < N; j++) {
				universe[i][j] = Collections.binarySearch(list[i],  universe[i][j]);
			}
		}
		int cnt = 0;
		for(int i = 0; i < M; i++) {
			for(int j = i+1; j < M; j++) {
				if(Arrays.equals(universe[i],  universe[j])) cnt++;
			}
		}
		
		System.out.println(cnt);
		
	}
}
