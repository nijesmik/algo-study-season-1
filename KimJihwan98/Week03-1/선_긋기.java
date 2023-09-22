package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 선긋기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] line = new int[N][2];
		for(int i = 0; i < N; i++) {
			int[] tmp = new int[2];
			StringTokenizer st = new StringTokenizer(br.readLine());
			tmp[0] = Integer.parseInt(st.nextToken());
			tmp[1] = Integer.parseInt(st.nextToken());
			line[i] = tmp;
		}
		Arrays.sort(line, (o1,o2) -> o1[0]== o2[0] ? o1[1] - o2[1] : o1[0]-o2[0]);
		
		int start = line[0][0];
		int end = line[0][1];
		int length = end - start;
		for(int i = 1; i < N; i++) {
			if(line[i][1] <= end) continue;
			else if(line[i][0] <= end) {
				length += line[i][1] - end;
			} else {
				length += line[i][1] - line[i][0];
			}
			end = line[i][1];
		}
		System.out.println(length);
	}
}
