import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준_2170_선긋기 {
	static class Line implements Comparable<Line> {
		int st, ed;

		public Line(int st, int ed) {
			super();
			this.st = st;
			this.ed = ed;
		}

		@Override
		public int compareTo(Line o) {

			return Integer.compare(this.st, o.st);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		Line[] lines = new Line[n];
		for (int i = 0; i < lines.length; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			lines[i] = new Line(a, b);
		}
		Arrays.sort(lines);

		int s = lines[0].st;
		int e = lines[0].ed;
		int ans = e - s;
		for (int i = 1; i < lines.length; i++) {
			int ns = lines[i].st;
			int ne = lines[i].ed;

			if (ne <= e) {
				continue;
			} else if (e <= ns) {
				ans += ne - ns;
			} else {
				ans += ne - e;
			}

			s = ns;
			e = ne;

		}
		System.out.println(ans);

		br.close();
	}

}
