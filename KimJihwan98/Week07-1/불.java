import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int r;
	int c;
	int time;
	public Node(int r, int c, int time) {
		this.r = r;
		this.c = c;
		this.time = time;
	}
}

public class 불 {
	static int w, h, answer, firetime;
	static char[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int t = 1; t <= T; t++) {
			int r = 0, c = 0, time = 0;
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			map = new char[h][w];
			for(int i = 0; i < h; i++) {
				String s = br.readLine();
				for(int j = 0; j < w; j++) {
					map[i][j] = s.charAt(j);
					if(map[i][j] == '@') {
						r = i;
						c = j;
					}
				}
			}
			firetime = 0;
			answer = Integer.MAX_VALUE;
			bfs(r, c, time);
			if(answer == Integer.MAX_VALUE) System.out.println("IMPOSSIBLE");
			else System.out.println(answer);
		}
	}
	static boolean[][] visited;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	static void bfs(int r, int c, int time) {
		Queue<Node> q = new LinkedList<>();
		Queue<Node> fire_q= new LinkedList<>();
		visited = new boolean[h][w];
		q.add(new Node(r, c, time));
		while(!q.isEmpty()) {
			Node now = q.poll();
			r = now.r;
			c = now.c;
			time = now.time;
			if(time > answer) return;
			// 불 붙여
			if(time == firetime) {
				firetime++;
				for(int i = 0; i < h; i++) {
					for(int j = 0; j < w; j++) {
						if(map[i][j] == '*') {
							if(visited[i][j]) continue;
							visited[i][j] = true;
							for(int k = 0; k < 4; k++) {
								int di = i + dr[k];
								int dj = j + dc[k];
								if(!(di>=0&&di<h&&dj>=0&&dj<w)) continue;
								if(map[di][dj]=='.' || map[di][dj] == '@') {
									map[di][dj] = '*';
									visited[di][dj] = true;
								}
							}
						}
					}
				}
			}
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				//종료 조건
				if(!(nr>=0&&nr<h&&nc>=0&&nc<w)) {
					answer = Math.min(answer,  time+1);
					continue;
				}
				if(map[nr][nc]!='.') continue;
				map[nr][nc] = '@';
				q.add(new Node(nr, nc, time+1));
			}
		}
	}
}
