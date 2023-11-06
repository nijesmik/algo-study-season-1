package nijesmik.Week10.야근_지수;

import java.util.*;

class Solution {
    int[] works;
    int N;
    long ans = Long.MAX_VALUE;
    public long solution(int n, int[] worksParam) {
        works = worksParam;
        N = works.length;

        long total = 0;
        for (int i = 0; i < N; i++) {
            total += works[i];
        }

        if (total <= n) return 0;

        Arrays.sort(works);

        int start = 0, end = works[N-1];
        while (start <= end) {
            int mid = (start + end) / 2;
            if (compare(mid, n)) end = mid - 1;
            else start = mid + 1;
        }

        return ans;
    }
    boolean compare(int mid, int n) {
        long tmpAnswer = 0;
        int i = N;
        while (i-- > 0) {
            long tmp;
            if (works[i] <= mid || n == 0) {
                tmp = works[i];
                tmpAnswer += tmp * tmp;
                continue;
            }
            int piece = works[i] - mid;
            if (piece > n) piece = n;
            n -= piece;
            tmp = works[i] - piece;
            tmpAnswer += tmp * tmp;
        }
        if (tmpAnswer < ans) ans = tmpAnswer;
        if (n > 0) return true;
        return false;
    }
}