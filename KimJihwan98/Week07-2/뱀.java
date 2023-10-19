import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;


public class 뱀 {
	static int N, K, L;
	static int[][] map;
	static List<int[]> snake;
	static HashMap<Integer, Character> direction;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = 1;
		}
		
		direction = new HashMap<>();
		L = Integer.parseInt(br.readLine());
		for(int i = 0; i <L; i++) {
			st = new StringTokenizer(br.readLine());
			direction.put(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0));
		}
		
		snake = new ArrayList<>();
		int time = 0, r = 0, c = 0, d = 0;
		map[r][c] = -1;
		snake.add(new int[] {r, c});
		while(true) {
			time++;
			
			r = r + dr[d];
			c = c + dc[d];
			
			// 종료 조건
			if(!(r>=0&&r<N&&c>=0&&c<N)) break;
			if(map[r][c] == -1) break;
			
			// 사과 있어?
			if(map[r][c] == 1) {
				snake.add(new int[] {r, c});
			} else {
				snake.add(new int[] {r, c});
				int tr = snake.get(0)[0];
				int tc = snake.get(0)[1];
				map[tr][tc] = 0;
				snake.remove(0);
			}
			map[r][c] = -1;
			
			//방향전환
			if(direction.containsKey(time)) {
				if(direction.get(time)=='L') d = (d+3)%4;
				else d = (d+1)%4;
			}
		}
		System.out.println(time);
		
	}
}
