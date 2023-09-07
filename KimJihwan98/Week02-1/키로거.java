package Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 키로거 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
//			LinkedList<Character> ll = new LinkedList<>();
//			String s = br.readLine();
//			int idx = 0;
//			for(int i = 0; i < s.length(); i++) {
//				if(s.charAt(i) == '<') {
//					if(idx >0) idx--;
//				} else if(s.charAt(i) == '>') {
//					if(idx <= ll.size()-1) idx++;
//				} else if(s.charAt(i) == '-') {
//					if(!(idx==0)) {
//						ll.remove(--idx);
//					}
//				} else {
//					ll.add(idx, s.charAt(i));
//					idx++;
//				}
//			}
//			for(int i = 0; i < ll.size(); i++) {
//				System.out.print(ll.get(i));
//			}
			String s = br.readLine();
			Stack<Character> front = new Stack<>();
			Stack<Character> back = new Stack<>();
			for(int i = 0; i < s.length(); i++) {
				char tmp;
				if(s.charAt(i) == '<') {
					if(!front.isEmpty()) back.add(front.pop());
				} else if( s.charAt(i) == '>') {
					if(!back.isEmpty()) front.add(back.pop());
				} else if(s.charAt(i) == '-') {
					if(!front.isEmpty()) front.pop();
				} else {
					front.add(s.charAt(i));
				}
			}
			while(!back.isEmpty()) {
				front.add(back.pop());
			}
			StringBuilder tmp = new StringBuilder();
			while(!front.isEmpty()) {
				tmp.append(front.pop());
			}
			System.out.println(tmp.reverse());
		}
	}
}