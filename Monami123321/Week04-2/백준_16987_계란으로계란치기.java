import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_16987_계란으로계란치기 {
	static class Node {
		int hp, power;

		public Node(int hp, int power) {
			super();
			this.hp = hp;
			this.power = power;
		}

	}

	static void dfs(boolean[] visited, int sum, int now) {

		if (now == -1) {
			ans = Math.max(sum, ans);
			return;

		}

		for (int i = 0; i < nodes.length; i++) {
			if (i == now)
				continue;
			if (visited[i])
				continue;
			// 1. 둘 다 안깨짐, 2. 둘 다 깨짐, 3. 치는 계란만 꺠짐, 4. 맞는 계란만 깨짐 && 부등호 주의

			// 맞는 계란만 깨질 때
			if (nodes[i].hp <= nodes[now].power && nodes[now].hp > nodes[i].power) {
				visited[i] = true;
				nodes[now].hp -= nodes[i].power;
				int next = -1;
				for (int j = now + 1; j < visited.length; j++) {
					if (!visited[j]) {
						next = j;
						break;
					}
				}

				dfs(visited, sum + 1, next);
				visited[i] = false;
				nodes[now].hp += nodes[i].power;
			} else if (nodes[i].hp > nodes[now].power && nodes[now].hp <= nodes[i].power) {// 치는 계란만 깨짐
				nodes[i].hp -= nodes[now].power;
				visited[now] = true;

				int next = -1;
				for (int j = now + 1; j < visited.length; j++) {
					if (!visited[j]) {
						next = j;
						break;
					}
				}
				dfs(visited, sum+1, next);
				visited[now] = false;
				nodes[i].hp += nodes[now].power;
			} else if (nodes[i].hp <= nodes[now].power && nodes[now].hp <= nodes[i].power) { // 치는 계란 맞는 계란 둘 다 깨짐
				visited[now] = true;
				visited[i] = true;

				int next = -1;
				for (int j = now + 1; j < visited.length; j++) {
					if (!visited[j]) {
						next = j;
						break;
					}
				}
				dfs(visited, sum + 2, next);
				visited[now] = false;
				visited[i] = false;

			} else { // 둘 다 안 깨짐
				nodes[i].hp -= nodes[now].power;
				nodes[now].hp -= nodes[i].power;

				int next = -1;
				for (int j = now + 1; j < visited.length; j++) {
					if (!visited[j]) {
						next = j;
						break;
					}
				}

				dfs(visited, sum, next);

				nodes[i].hp += nodes[now].power;
				nodes[now].hp += nodes[i].power;

			}

		}
		ans = Math.max(sum, ans);
		return;

	}

	static int n, ans;
	static Node[] nodes;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		ans = 0;

		nodes = new Node[n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int hp = Integer.parseInt(st.nextToken());
			int power = Integer.parseInt(st.nextToken());

			nodes[i] = new Node(hp, power);

		}

		dfs(new boolean[n], 0, 0);
		System.out.println(ans);

		br.close();

	}

}