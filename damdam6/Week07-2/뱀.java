import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BAEK3190 {
	
	static int N;
	static int K;
	
	static node[] appleArr;
	
	static int L;
	static play[] playArr;

	
	static int[][] board;
	static int[][] dirBoard;
	static node head;
	static node tail;
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		//N*N사이즈 놀이판
		N = Integer.parseInt(bf.readLine());
		board = new int[N][N];
		dirBoard = new int[N][N];
		
		//board : -1 뱀 / 0 기본 / 1 사과
		

		// 0,1,2,3 그 칸의 direction - head와 tail일 때 수정
		for(int i=0;i<N;i++) {
			Arrays.fill(dirBoard[i], 5);
		}
		
		//K개 사과 위치
		K = Integer.parseInt(bf.readLine());
		
		appleArr = new node[K];
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(bf.readLine());
			appleArr[i] = new node(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);
			board[appleArr[i].x][appleArr[i].y] = 1; //사과
		}
		
		

		//이동 정보 L회
		L = Integer.parseInt(bf.readLine());
		playArr = new play[L];
		for(int i=0;i<L;i++) {
			st = new StringTokenizer(bf.readLine());
			playArr[i] = new play(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0));
		}
		

		
		board[0][0] = 2; //뱀의 시작 위치
		
		snkDir = 1; // dx, dy배열의 1번 인덱스의 값을 가짐
		dirBoard[0][0] = snkDir;
		head = new node(0,0);
		tail = new node(0,0);

		answer = 0;
		goSnake(1, 0);
		System.out.println(answer);
		
		
	}
	
	static int snkDir;
	//오른쪽 회전 기준 - 위 오른 아래 좌
	static int[] dx = new int[] {-1, 0, 1, 0};
	static int[] dy = new int[] {0, 1, 0, -1};
	
	static int answer;
	//초마다 뱀을 그리는 메서드를 만들기
	public static void goSnake(int time, int playIdx) {
		
		
		//1. 머리를 다음 칸으로 위치시킴 snkDir확인
		int nx = head.x + dx[snkDir];
		int ny = head.y + dy[snkDir];
		
		//2. 벽or자기자신에 닿는지 확인 -> 종료조건
		
		//board[][]가 -1이면 뱀
		if(nx<0||nx>=N||ny<0||ny>=N||board[nx][ny] == 2) {
			answer = time;
			return;
		}
		
		//head값 갱신
		
		head.x = nx;
		head.y = ny;
		
		//3. 사과를 먹었는지 확인
		//4. 사과를 안먹었다면 꼬리 위치 dirBoard의 방향을 참고하여 이동
		if(board[nx][ny] != 1) {
			//원래 위치 갱신
			board[tail.x][tail.y] = 0;
			
			int dirIdx = dirBoard[tail.x][tail.y];
			//꼬리의 위치를 갱신
			tail.x = tail.x + dx[dirIdx];
			tail.y = tail.y + dy[dirIdx];
			
		}
		board[nx][ny] = 2;

		//playArr[playIdx]를 확인하고 dir을 바꿔줘야한다.
		
		if(playIdx < L && time == playArr[playIdx].time) {
			
			switch(playArr[playIdx].turn) {
			
			case 'L':
				snkDir = (snkDir-1+4)%4;
				break;
			case 'D':
				snkDir = (snkDir+1)%4;
				break;
			}		
			playIdx ++;
		}
		dirBoard[nx][ny] = snkDir;
		
		
		goSnake(time+1, playIdx);
		
	}
	
	
	
	
	static class node{
		int x;
		int y;
		
		node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static class play{
		int time;
		char turn;
		
		play(int time, char turn){
			this.time = time;
			this.turn = turn;
		}
	}

}
