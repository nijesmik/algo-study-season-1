package DATE1030;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BAEK19236 {

	static fish[][] sea;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sea = new fish[4][4];

		int a;
		int b;
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(bf.readLine());

			for (int j = 0; j < 4; j++) {
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken()) - 1;

				sea[i][j] = new fish(a, b);
			}
		}
		
		sea[0][0].shark = true;
		
		max = 0;
		sharkDfs(sea, 0, new pos(0,0));
		
		System.out.println(max);

	}
	
	static int max;
	static void sharkDfs(fish[][] sea, int sum, pos sharkP) {
		
		//지금 칸을 잡아먹어볼까요?
		int x = sharkP.x;
		int y = sharkP.y;
		sum += sea[x][y].num;
		sea[x][y].eat = true;
		
		max = Math.max(max, sum);
		
		fish[][] newSea = fishMov(sea);
		
		int dir = sea[x][y].dir;
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		
		newSea[x][y].shark = false;
		//새로운 상어의 위치를 찾아봅시다
		while(nx >= 0 && nx < 4 && ny >= 0 && ny <4 ) {
			
			if(!newSea[nx][ny].eat) {
				newSea[nx][ny].shark= true;
				sharkDfs(newSea, sum, new pos(nx, ny));
				newSea[nx][ny].shark = false;
			}
			
		 nx = nx + dx[dir];
		 ny = ny + dy[dir];
			
		}
		
		sea[x][y].eat = false;
		
		
	}

	static fish[][] fishMov(fish[][] sea) {

		//반환할 새로운 배열
				fish[][] newSea = new fish[4][4];

		pos[] posArr = new pos[17];

		int n;
		//물고기 순서대로 어디를 봐야하는지 기록하는 arr
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				//깊은 복사
				newSea[i][j] = new fish(sea[i][j]);
				
				//num위치 기록
				n = sea[i][j].num;
				posArr[n] = new pos(i, j);
			}
		}
		
		for(int i=1;i<17;i++) {
			pos p = posArr[i];
			
			
			fish now_fish = newSea[p.x][p.y];
			if (now_fish.eat)continue;
			
			int nx= 0;
			int ny =0;
			
			while(true) {
				
				nx = p.x+dx[now_fish.dir];
				ny = p.y+dy[now_fish.dir];
				
				//범위를 벗어날 경우 방향을 바꿔줍니다.
				if(nx < 0 || ny < 0 || nx >=4 || ny >= 4) {
					now_fish.dir = (now_fish.dir+1)%8;
					continue;
				}
				//상어이므로 방향을 바꿔줍니다.
				if(newSea[nx][ny].shark) {
					now_fish.dir = (now_fish.dir+1)%8;
					continue;
				}
				
				//이미 먹혔을 경우
				
				break;
			}
			
			//새로 가야할 곳의 fish를 임시 저장
			fish tmp = new fish(newSea[nx][ny]);
			
			// 바꿔준 값의 위치를 저장 (혹시 지금 넘버 이후 값일까봐)
			posArr[tmp.num].x = p.x;
			posArr[tmp.num].y = p.y;
			
			newSea[nx][ny] = new fish(now_fish);
			newSea[p.x][p.y] = new fish(tmp);
			
		}
		
		return newSea;
	}

	static int[] dx = new int[] { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = new int[] { 0, -1, -1, -1, 0, 1, 1, 1 };

	// num이 0 이면 상어
	static class fish {

		int dir;
		int num;
		boolean eat;
		boolean shark;

		fish(int num, int dir) {
			this.num = num;
			this.dir = dir;
			this.eat = false;
			this.shark = false;
		}
		
		fish(fish f){
			this.num = f.num;
			this.dir = f.dir;
			this.eat = f.eat;
			this.shark = f.shark;
		}
		
		public String toString() {
			return this.num + " " + this.dir;
		}

	}

	static class pos {
		int x;
		int y;

		pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
		

	}

}
