package nijesmik.Week09.후보키;

import java.util.*;

class Solution {
    int row, column;
    String[][] rel;
    List<Integer> candidate;

	public int solution(String[][] relation) {
        row = relation.length;
        column = relation[0].length;
        rel = relation;

        candidate = new ArrayList<>();
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int i = cur[1]; i < column; i++) {
                int cand = cur[0] | 1 << i;
                if (!isCandidate(cand)) q.add(new int[]{cand, i+1});
            }
        }
        return candidate.size();
    }

    boolean isCandidate(int cand) {
        for (int key : candidate) {
            if ((cand & key) == key) return true;
        }
        Set<String> set = new HashSet<>();
        String slash = "/";
        for (String[] data : rel) {
            String str = "";
            for (int i = 0; i < column; i++) {
                if ((cand >> i & 1) > 0) str += data[i] + slash;
            }
            set.add(str);
        }
        if (set.size() == row) {
            candidate.add(cand);
            return true;
        }
        return false;
    }
}