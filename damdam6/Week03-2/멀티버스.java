package BAEK0921;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BAEK18869 {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(bf.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> arrset = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < M; i++) {
			arr.add(new ArrayList<Integer>());
			st = new StringTokenizer(bf.readLine());

			for (int j = 0; j < N; j++) {
				arr.get(i).add(Integer.parseInt(st.nextToken()));
			}
			Set<Integer> set = new HashSet<Integer>(arr.get(i));
			ArrayList<Integer> tmp = new ArrayList<Integer>(set);
			Collections.sort(tmp);

			
			for(int j=0;j<N;j++) {
				int searchResult = Collections.binarySearch(tmp, arr.get(i).get(j));
				
				arr.get(i).set(j, searchResult);

			}
		}
		
		int cnt=0;
		for(int i=0;i<M;i++) {
			for(int j=i+1;j<M;j++) {
				if(arr.get(i).equals(arr.get(j))) {
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}
}
