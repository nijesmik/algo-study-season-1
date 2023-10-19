import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class 적록색약 {
	static int N, ans1, ans2;
	static int[][] map;
	static boolean[][] visited1;
	static HashMap<Character, Integer> color;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		ans1 = 0;
		ans2 = 0;
		color = new HashMap<>();
		color.put('R', 0);
		color.put('G', 1);
		color.put('B', 2);
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for(int j= 0; j < N; j++) {
				char tmp = s.charAt(j);
				map[i][j] = color.get(tmp);
			}
		}
		visited1 = new boolean[N][N];
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(visited1[r][c]) continue;
				bfs1(r, c, map[r][c]);
				ans1++;
			}
		}
		visited1 = new boolean[N][N];
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(visited1[r][c]) continue;
				if(map[r][c] == 2) {
					bfs1(r, c, 2);
					ans2++;
				}
				else {
					bfs2(r, c);
					ans2++;
				}
			}
		}
		System.out.println(ans1 + " " + ans2);
	}
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	static void bfs1(int r, int c, int color_num) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {r, c, color_num});
		visited1[r][c] = true;
		while(!q.isEmpty()) {
			int[] tmp = q.poll();
			r = tmp[0];
			c = tmp[1];
			color_num = tmp[2];
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(!(nr>=0&&nr<N&&nc>=0&&nc<N)) continue;
				if(visited1[nr][nc]) continue;
				if(map[nr][nc] != color_num) continue;
				visited1[nr][nc] = true;
				q.add(new int[] {nr, nc, color_num});
			}
		}
	}
	
	static void bfs2(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {r, c});
		visited1[r][c] = true;
		while(!q.isEmpty()) {
			int[] tmp = q.poll();
			r = tmp[0];
			c = tmp[1];
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(!(nr>=0&&nr<N&&nc>=0&&nc<N)) continue;
				if(visited1[nr][nc]) continue;
				if(map[nr][nc] == 2) continue;
				visited1[nr][nc] = true;
				q.add(new int[] {nr, nc});
			}
		}
	}
}
