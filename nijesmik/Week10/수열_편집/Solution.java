package nijesmik.Week10.수열_편집;

import java.util.*;

public class Solution {
	static List<Integer> sequence;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			int L = sc.nextInt();
			sequence = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				sequence.add(sc.nextInt());
			}
			for (int i = 0; i < M; i++) {
				char cmd = sc.next().charAt(0);
				if (cmd == 'I') {
					insert(sc.nextInt(), sc.nextInt());
				} else if (cmd == 'D') {
					delete(sc.nextInt());
				} else {
					convert(sc.nextInt(), sc.nextInt());
				}
			}
			System.out.printf("#%d %d\n", t, getAnswer(L));
		}
	}
	static void insert(int idx, int num) {
		sequence.add(idx, num);
	}
	static void delete(int idx) {
		sequence.remove(idx);
	}
	static void convert(int idx, int num) {
		sequence.remove(idx);
		sequence.add(idx, num);
	}
	static int getAnswer(int idx) {
		if (idx < sequence.size()) {
			return sequence.get(idx);
		}
		return -1;
	}
}
