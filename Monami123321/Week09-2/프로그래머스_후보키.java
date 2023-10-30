import java.util.*;

class Solution {
	static HashSet<Integer> answerSet;

	static boolean check(int n) {
		for (int ans : answerSet) {
			if ((n & ans) == ans) {
				return false;
			}
		}
		return true;

	}

	public int solution(String[][] relation) {

		int row = relation.length;
		int col = relation[0].length;
		answerSet = new HashSet<>();

		for (int i = 0; i < (1 << col); i++) {
			HashSet<String> set = new HashSet<>();
			for (int j = 0; j < row; j++) {
				String tmp = "";

				for (int k = 0; k < col; k++) {
					if ((i & (1 << k)) > 0) {
						tmp += relation[j][k];
					}
				}
				set.add(tmp);
			}
			if (set.size() == row && check(i)) {
				answerSet.add(i);
			}

		}
		return answerSet.size();
	}
}