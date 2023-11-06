package ssafybackup;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class 수열편집 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int tc=1; tc<=T; tc++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			int L = sc.nextInt();
			List<Integer> list = new LinkedList<>();
			
			for(int i=0; i<N; i++) {
				list.add(sc.nextInt());
			}
			
			for(int i=0; i<M;i++) {
				int n;
				int idx;
				String op = sc.next();
				switch(op) {
				case "I":
					idx = sc.nextInt();
					n = sc.nextInt();
					list.add(idx,n);
					break;
				case "D":
					idx = sc.nextInt();
					list.remove(idx);
					break;
				case "C":
					idx = sc.nextInt();
					n = sc.nextInt();
					list.set(idx, n);
					break;
				default:
					break;
				}
			}
			System.out.println(list.get(L));
		}
	}
}
