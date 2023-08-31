package DATE0831;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class BAEK2493 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(bf.readLine());
		int[] arr = new int[N];
		int[] bb = new int[N];
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// index를 넣어보자 - 스택
		Stack<Integer> stk = new Stack<>();
		int i = N - 1;
		while (i >= 0) {
			if (stk.isEmpty()) {
				stk.push(i--);
			} else if (arr[stk.peek()] > arr[i]) {
				stk.push(i--);
			} else {

				while (!stk.isEmpty()&&i>=0&&arr[stk.peek()] < arr[i]) {
					bb[stk.pop()] = i+1;
				}

			}
		}
		while(!stk.isEmpty()) {
			bb[stk.pop()] = 0;
		}
	
		for(int c:bb)sb.append(c+" ");
		System.out.println(sb);
	}

}
