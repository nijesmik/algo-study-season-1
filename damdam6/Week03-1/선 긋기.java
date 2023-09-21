package BAEK0921;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class line implements Comparable<line> {
	int start;
	int end;

	public line(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public boolean chkSet(int start, int end) {

		if (this.start <= start && start <= this.end) {

			if (this.end < end) {
				this.end = end;
			}
			return true;
		} else if (this.end >= end && end >= this.start) {
			if (this.start > start) {
				this.start = start;
			}
			return true;
		} else if (this.end < end && this.start > start) {
			this.start = start;
			this.end = end;
			return true;
		} else if (this.end >= end && this.start <= start) {
			return true;
		}

		return false;
	}

	public int getLong() {
		return Math.abs(this.end - this.start);
	}

	public String toString() {
		return "start " + this.start + " end " + this.end;
	}

	public int compareTo(line p) {
		if (this.start > p.start) {
			return 1;
		} else if (this.start < p.start) {
			return -1;
		} else {
			return 0;
		}
	}

}

public class BAEK2170 {

	public static void main(String[] args) throws Exception {

		StringBuilder sb = new StringBuilder();
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bf.readLine());
		ArrayList<line> real = new ArrayList<>();
		StringTokenizer st;
		allfor: for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			int start = Math.min(a, b);
			int end = Math.max(a, b);
			for (line q : real) {
				// 노드에 값을 할당했으면 -> 위의 반복문으로 돌아감
				if (q.chkSet(start, end)) {
					continue allfor;
				}
			}
			line c = new line(start, end);
			real.add(c);
		}

		int sum = 0;

		int minInt = -1;
		int maxInt = -1;

		Collections.sort(real);

		int idx = 0;
		while (idx < real.size() - 1) {

			// 첫번째거랑 두 번째꺼 비교

			// 두번째거 시작 지점이 첫번째꺼 끝보다 같거나 작으면
			if (real.get(idx + 1).start <= real.get(idx).end) {
				line a = real.get(idx);

				a.end = Math.max(a.end, real.get(idx + 1).end);

				real.set(idx, a);
				real.remove(idx + 1);
			} else {
				// 삭제를 안했으면 다음꺼보기
				idx++;
			}
		}

		// System.out.println(real.toString());
		for (line a : real) {
			sum += a.getLong();
		}

		System.out.println(sum);
	}

}
