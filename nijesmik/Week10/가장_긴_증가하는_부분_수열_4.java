package nijesmik.Week10;
import java.util.*;

public class 가장_긴_증가하는_부분_수열_4 {
	static int maxSize, maxIdx;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Elem[] seq = new Elem[N+1];
		for (int i = 1; i <= N; i++) {
			seq[i] = new Elem(i, sc.nextInt());
		}
		maxIdx = 1;
		for (int i = 1; i <= N; i++) {
			for (int j = i+1; j <= N; j++) {
				seq[j].compare(seq[i]);
			}
		}
		System.out.println(maxSize+1);
		int idx = maxIdx;
		String ans = "";
		while (seq[idx].prevIdx > 0) {
			ans = seq[idx].value + " " + ans;
			idx = seq[idx].prevIdx;
		}
		ans = seq[idx].value + " " + ans;
		idx = seq[idx].prevIdx;
		System.out.println(ans);
	}
	static class Elem {
		int value, idx;
		int prevIdx;
		int size;

		Elem(int idx, int value) {
			this.idx = idx;
			this.value = value;
		}

		void compare(Elem o) {
			if (o.value >= this.value)
				return;
			if (o.size + 1 < this.size)
				return;
			prevIdx = o.idx;
			this.size = o.size + 1;
			if (this.size > maxSize) {
				maxSize = this.size;
				maxIdx = idx;
			}
		}
	}
}