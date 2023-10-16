package nijesmik.Week07.광물_캐기;

class Solution {
    int[] picks, minerals;
    int total, ans, n;
    int[][] map = {
        {1,1,1},
        {5,1,1},
        {25,5,1}
    };
    public int solution(int[] a, String[] b) {
        picks = a;
        for (int pick : picks) {
            total += pick;
        }
        n = b.length;
        minerals = new int[n];
        for (int i = 0; i < n; i++) {
            char c = b[i].charAt(0);
            if (c == 'd') minerals[i] = 0;
            else if (c == 'i') minerals[i] = 1;
            else minerals[i] = 2;
        }
        ans = Integer.MAX_VALUE;
        dfs(0, 0, 0);
        return ans;
    }

    void dfs(int cnt, int idx, int result) {
        if (result > ans) return;
        if (cnt == total || idx >= n) {
            ans = Math.min(ans, result);
            return;
        }
        for (int i = 0; i < 3; i++) {
            if (picks[i] == 0) continue;
            picks[i]--;
            int sum = 0;
            for (int j = 0; j < 5; j++) {
                if (idx+j < n)
                    sum += map[i][minerals[idx+j]];
            }
            dfs(cnt+1, idx+5, result + sum);
            picks[i]++;
        }
    }
}