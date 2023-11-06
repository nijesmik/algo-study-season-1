import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


class Fish{
	int num;
	int dir;
	boolean eat;
	boolean isShark;
	public Fish(int num, int dir, boolean eat, boolean isShark) {
		this.num = num;
		this.dir = dir;
		this.eat = eat;
		this.isShark = isShark;
	}
	public Fish(Fish f) {
		this.num = f.num;
		this.dir = f.dir;
		this.eat = f.eat;
		this.isShark = f.isShark;
	}
}

class Sea {
	Fish[][] map;
	int size;
	int sr;
	int sc;
	public Sea(Fish[][] map, int size, int sr, int sc) {
		this.map = map;
		this.size = size;
		this.sr = sr;
		this.sc = sc;
	}
}

public class 청소년_상어 {
	static Fish[][] sea;
	static Fish shark;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sea = new Fish[4][4];
		for(int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j= 0; j < 4; j++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken())-1;
				sea[i][j] = new Fish(a, b, false, false);
			}
		}
		sea[0][0].eat = true;
		sea[0][0].isShark = true;
		System.out.println(bfs());
		
		
	}
	
	static int bfs() {
		int max = sea[0][0].num;
		Queue<Sea> q = new LinkedList<>();
		q.offer(new Sea(sea, sea[0][0].num, 0, 0));
		while(!q.isEmpty()){
			Sea now = q.poll();
			max = Math.max(max, now.size);
			Fish[][] nmap = move_fish(now.map);
			
			int sr = now.sr;
			int sc = now.sc;
			int sd = now.map[sr][sc].dir;
			
			int nr = sr + dr[sd];
			int nc = sc + dc[sd];
			while(nr >= 0 && nr < 4 && nc >=0 && nc <4) {
				if(!nmap[nr][nc].eat) {
					nmap[nr][nc].eat = true;
					nmap[nr][nc].isShark = true;
					nmap[sr][sc].isShark = false;
					Fish[][] rnMap = copyMap(nmap);
					q.offer(new Sea(rnMap, now.size+nmap[nr][nc].num, nr, nc));
					nmap[nr][nc].eat = false;
					nmap[nr][nc].isShark = false;
					nmap[sr][sc].isShark = true;
				}
				nr += dr[sd];
				nc += dc[sd];
			}
		}
		return max;
	}
	
	static Fish[][] copyMap(Fish[][] map) {
		Fish[][] tmp = new Fish[4][4];
		for(int i = 0; i < 4; i++) {
			for(int j=  0; j < 4; j++) {
				tmp[i][j] = new Fish(map[i][j]);
			}
		}
		return tmp;
	}
	
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, -1, -1, -1, 0, 1, 1, 1};
	static Fish[][] move_fish(Fish[][] stmp) {
		int idx = 1;
		while(idx <= 16) {
			loop :for(int r = 0; r < 4; r++) {
				for(int c = 0; c < 4; c++) {
					if(stmp[r][c].num == idx && !stmp[r][c].eat) {
						int d = stmp[r][c].dir;
						for(int i = 0; i< 8; i++) {
							int nr = r + dr[(d+i)%8];
							int nc = c + dc[(d+i)%8];
							
							if(!(nr<4&&nr>=0&&nc<4&&nc>=0)) continue;
							if(stmp[nr][nc].isShark) continue;
							if(stmp[nr][nc].eat) {
								stmp[nr][nc] = new Fish(stmp[r][c]);
								stmp[nr][nc].dir = (d+i)%8;
								stmp[r][c].eat = true;
								break loop;
							} else {
								Fish tmp = stmp[r][c];
								stmp[r][c] = new Fish(stmp[nr][nc]);
								stmp[nr][nc] = new Fish(tmp);
								stmp[nr][nc].dir = (d+i)%8;
								break loop;
							}
						}
					}
				}
			}
		idx++;
		} // while
		return stmp;
	} // move_fish
}