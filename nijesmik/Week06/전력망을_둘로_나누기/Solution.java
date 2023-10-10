package nijesmik.Week06.전력망을_둘로_나누기;
import java.util.*;

class Solution {
    int[][] map;
    int n;

    public int solution(int a, int[][] wires) {
        n = a;
        map = new int[n+1][n+1];
        for (int[] wire : wires) {
            map[wire[0]][wire[1]] = 1;
            map[wire[1]][wire[0]] = 1;
        }

        int min = n;
        for (int[] wire : wires) {
            map[wire[0]][wire[1]] = 0;
            map[wire[1]][wire[0]] = 0;
            min = Math.min(min, bfs());
            map[wire[0]][wire[1]] = 1;
            map[wire[1]][wire[0]] = 1;
        }

        return min;
    }

    int bfs() {
        int cnt = 1;
        boolean[] visit = new boolean[n+1];
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        visit[1] = true;
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int i = 1; i <= n; i++) {
                if (map[cur][i] == 0 || visit[i]) continue;
                q.add(i);
                visit[i] = true;
                cnt++;
            }
        }
        return Math.abs(n - cnt*2);
    }
}