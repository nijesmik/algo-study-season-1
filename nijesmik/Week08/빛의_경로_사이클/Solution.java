package nijesmik.Week08.빛의_경로_사이클;

import java.util.*;

class Solution {
    List<Integer> ans;
    boolean[][][] visit;
    char[][] map;
    int r, c;
    int[] dr, dc;
    public int[] solution(String[] grid) {
        ans = new ArrayList<>();
        r = grid.length; c = grid[0].length();
        dr = new int[]{r-1, 0, 1, 0};
        dc = new int[]{0, 1, 0, c-1};
        visit = new boolean[r][c][4];
        map = new char[r][];
        for (int i = 0; i < r; i++) {
            map[i] = grid[i].toCharArray();
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                for (int dir = 0; dir < 4; dir++) {
                    findcycle(i, j, dir);
                }
            }
        }
        Collections.sort(ans);
        return ans.stream().mapToInt(a->a).toArray();
    }

    void findcycle(int i, int j, int dir) {
        int nr = i, nc = j, ndir = dir, cnt = 0;
        while (!visit[nr][nc][ndir]) {
            visit[nr][nc][ndir] = true;
            if (map[nr][nc] == 'L') {
                ndir = (ndir + 3) % 4;
            } else if (map[nr][nc] == 'R') {
                ndir = (ndir + 1) % 4;
            }
            nr = (nr + dr[ndir]) % r;
            nc = (nc + dc[ndir]) % c;
            cnt++;
        }
        if (nr == i && nc == j && ndir == dir && cnt > 0)
            ans.add(cnt);
    }
}