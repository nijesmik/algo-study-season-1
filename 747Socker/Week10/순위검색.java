package ssafybackup;
import java.util.*;
public class 순위검색 {

		public int[] solution(String[] info, String[] query) {
			int[] answer = new int[query.length];
			List<String> list = new ArrayList<>();

			for (String i : info) {
				list.add(i);
			}

			int idx = 0;
			for (String q : query) {
				String[] sq = q.replaceAll(" and ", " ").split(" ");
				int count = 0;
				for (String x : list) {
					String[] xx = x.split(" ");
					if ((sq[0].equals("-") || xx[0].equals(sq[0])) && (sq[1].equals("-") || xx[1].equals(sq[1]))
							&& (sq[2].equals("-") || xx[2].equals(sq[2])) && (sq[3].equals("-") || xx[3].equals(sq[3]))
							&& Integer.parseInt(xx[4]) >= Integer.parseInt(sq[4])) {
						count++;
					}
				}
				answer[idx] = count;
				idx++;
			}
			return answer;
		}
	}