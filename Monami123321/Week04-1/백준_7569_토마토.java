import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 백준_7569_토마토 {
	static class Node {
		int x, y, z, depth;

		public Node(int x, int y, int z, int depth) {
			super();
			this.x = x;
			this.y = y;
			this.z = z;
			this.depth = depth;
		}

	}

	static int[] dx = { 0, 0, -1, 1, 0, 0 }; // 상 하 좌 우 위 아래
	static int[] dy = { -1, 1, 0, 0, 0, 0 };
	static int[] dz = { 0, 0, 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int x = scanner.nextInt();
		int y = scanner.nextInt();
		int z = scanner.nextInt();

		int[][][] map = new int[z][y][x];
		boolean[][][] visited = new boolean[z][y][x];

		Queue<Node> queue = new LinkedList<>();

		for (int i = 0; i < z; i++) {
			for (int j = 0; j < y; j++) {
				for (int k = 0; k < x; k++) {
					map[i][j][k] = scanner.nextInt();
					if (map[i][j][k] == 1) {
						visited[i][j][k] = true;
						queue.add(new Node(k, j, i, 0));
					}

				}

			}

		}

		int t = 0;
		while (!queue.isEmpty()) {
			while (!queue.isEmpty() && queue.peek().depth == t) {
				Node node = queue.poll();
				int nodeX = node.x;
				int nodeY = node.y;
				int nodeZ = node.z;
				int depth = node.depth;
				
				map[nodeZ][nodeY][nodeX] = 1;
				
				

				for (int i = 0; i < 6; i++) {
					int nx = nodeX + dx[i];
					int ny = nodeY + dy[i];
					int nz = nodeZ + dz[i];

					if (nx < 0 || ny < 0 || nz < 0 || nx > x - 1 || ny > y - 1 || nz > z - 1 || visited[nz][ny][nx]
							|| map[nz][ny][nx] == -1) {
						continue;
					}
					visited[nz][ny][nx] = true; 
					queue.add(new Node(nx, ny, nz, depth + 1));

				}

			}
			t++;
		}
		
		boolean flag = true;
		outer:for (int i = 0; i < z; i++) {
			for (int j = 0; j < y; j++) {
				for (int k = 0; k < x; k++) {
					if(map[i][j][k] == 0) {
						flag = false;
						break outer;
					}
				}
				
			}
			
		}
		
		if(flag) {
			System.out.println(t-1);
		} else {
			System.out.println(-1);
		}
		

		scanner.close();
	}

}
