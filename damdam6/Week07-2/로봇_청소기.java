import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int M;
	
	static int[][] room;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st;
		
		
		st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(bf.readLine());
		int rx = Integer.parseInt(st.nextToken());
		int ry = Integer.parseInt(st.nextToken());
		int rdir = Integer.parseInt(st.nextToken());
		
		// 북(위)0  남(아래)2  동(오른쪽)1  서(왼쪽)3
		
		room = new int[N][M];
		
		//room 받음
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < M; j++) {
				room[i][j]= Integer.parseInt(st.nextToken());
			}
		}
		
		robot rb = new robot(rx, ry, rdir);
				
		clean(rb);

		int c =0;
		for (int i = 0; i < room.length; i++) {
			//System.out.println(Arrays.toString(room[i]));
			for (int j = 0; j < room[i].length; j++) {
				if(room[i][j]==3)c++;
			}
		}
		System.out.println(c);
		
		
	}
	
	static void clean(robot rb) {
		
		
		//1. 청소하기
		if(room[rb.posX][rb.posY]!=3) {
			room[rb.posX][rb.posY] = 3;
		}
		
		//2. 청소 안된 칸 있는지 확인
		boolean cleanTF = false;
		
		int dir = rb.dir;
		for(int i=0;i<4;i++) {
			int nx = rb.posX + dx[i];
			int ny = rb.posY +dy[i];
			
			if(nx>=0&&nx<N&&ny>=0&&ny<M&&room[nx][ny]==0) {
				cleanTF = true;
				break;
			}
			
		}
		
		if(!cleanTF) {
			int nx = rb.posX - dx[rb.dir];
			int ny = rb.posY - dy[rb.dir]; 
			
			if(nx<0||nx>=N||ny<0||ny>=M||room[nx][ny]==1) {
				return;
			}else {
				rb.posX = nx;
				rb.posY = ny;
				clean(rb);
			}
			return;
		}
		
		while(true) {
			//갈 곳이  있을 경우
			dir = (dir+3)%4;
			int nx = rb.posX + dx[dir];
			int ny = rb.posY + dy[dir]; 
			if(nx>=0&&nx<N&&ny>=0&&ny<M&&room[nx][ny]==0) {
				rb.posX = nx;
				rb.posY = ny;
				rb.dir = dir;
				clean(rb);
				break;
			}
		}

		
	}
	
	
	static int[] dx = new int[] {-1 ,0,1,0};
	static int[] dy = new int[] {0,1,0,-1};
	
	
	static class robot{
		int posX;
		int posY;
		int dir;
		
		robot(int x, int y, int dir){
			this.posX = x;
			this.posY = y;
			this.dir = dir;
		}
		
	}

}
