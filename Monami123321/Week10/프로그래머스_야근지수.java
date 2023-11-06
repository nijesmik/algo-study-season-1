import java.util.*;

class Solution { // 효율성 실패
	public long solution(int n, int[] works) {
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;

		for (int i = 0; i < works.length; i++) {
			max = max < works[i] ? works[i] : max;
			min = min > works[i] ? min : works[i];
		}

		int start = 0;
		int end = max;
		while (start <= end) {
			long sum = 0;
			int mid = (start + end) / 2;
			for (int i = 0; i < works.length; i++) {
				if (works[i] > mid) {
					sum += works[i] - mid;
				}
			}

			if (sum > n) {
				start = mid + 1;

			} else if (sum <= n) {
				end = mid - 1;
			}

		}

		for (int i = 0; i < works.length; i++) {
			if (works[i] > start) {
				n -= works[i] - start;
				works[i] = start;

			}

		}
		Arrays.sort(works);
		while (n > 0 && works[0] != 0) {
			int i = works.length - 1;
			while (i > 0 && works[i] == works[i - 1])
				i--;

			for (int j = works.length - 1; j >= i; j--) {
				if (n == 0) {
					break;
				}
				n--;
				works[j]--;
			}

			Arrays.sort(works);

		}

		long ans = Arrays.stream(works).map(a -> a * a).sum();

		return ans;

	}
}