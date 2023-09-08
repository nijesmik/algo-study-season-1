package DATE0904;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BAEK5397_stack {
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(bf.readLine());
		

		String str;
		char[] arr;
		for(int i=0;i<M;i++) {
			Stack<Character> stL = new Stack<>();
			Stack<Character> stR = new Stack<>();
			str = bf.readLine();
			arr = str.toCharArray();
			
			for(int j=0; j<arr.length;j++) {			
				if(arr[j]=='<') {
					if(!stL.isEmpty()) {
						stR.push(stL.pop());
					}
				}else if(arr[j]=='>') {
					if(!stR.isEmpty()) {
						stL.push(stR.pop());
					}
				}else if(arr[j]=='-') {
					if(!stL.isEmpty()) {
						stL.pop();
					}
				}else {
					stL.push(arr[j]);
				}

			}
			while(!stR.isEmpty()) {
				stL.push(stR.pop());
			}
			for(char c:stL)sb.append(c);
			sb.append("\n");
		}
		

		System.out.println(sb);
	}

}
