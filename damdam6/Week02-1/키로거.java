package DATE0904;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class BAEK5397 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		LinkedList<Character> linkL;

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());

		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {

			linkL = new LinkedList<>();
			char[] charArr = (bf.readLine()).toCharArray();
			int idx = 0; // 다음에 들어가야 하는 위치
			for (int i = 0; i < charArr.length; i++) {

				if (charArr[i] == '-') {
					if(idx>=1) {
						linkL.remove(idx-1);
						idx--;
					}

				} else if (charArr[i] == '<') {
					if(idx>1) {
						idx--;
					}

				} else if (charArr[i] == '>') {
					if(idx<linkL.size()) {
						idx++;
					}

				} else {
					if (idx == linkL.size()) {
						linkL.addLast(charArr[i]);
					} else {
						linkL.add(idx, charArr[i]);
					}
					idx++;
				}
				
			}
			for(char c: linkL)sb.append(c);
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
