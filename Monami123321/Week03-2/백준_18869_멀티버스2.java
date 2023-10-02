import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 백준_18869_멀티버스2 {
	static class Node implements Comparable<Node> {
		int id, scale;

		public Node(int id, int scale) {
			super();
			this.id = id;
			this.scale = scale;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.scale, o.scale);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken()); // 우주의 갯수
		int n = Integer.parseInt(st.nextToken()); // 행성의 갯수
		List<List<Node>> check = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			check.add(new ArrayList<>());
			for (int j = 0; j < n; j++) {
				check.get(i).add(new Node(j, Integer.parseInt(st.nextToken())));

			}
		}
		for (int i = 0; i < m; i++) {
			Collections.sort(check.get(i));

		}

		int ans = 0;

		for (int i = 0; i < m - 1; i++) {
			outer: for (int j = i + 1; j < m; j++) {
				for (int k = 1; k < n; k++) {
					if (check.get(i).get(k).id != check.get(j).get(k).id) {
						continue outer;
					}
					if ((check.get(i).get(k).scale > check.get(i).get(k - 1).scale) != (check.get(j)
							.get(k).scale > check.get(j).get(k - 1).scale)) {
						continue outer;
					}

				}
				ans++;
			}

		}
		System.out.println(ans);

		br.close();

	}

}
