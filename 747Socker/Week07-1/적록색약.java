package pract;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_dfs_적녹색약 {

	static char[][] cArr;
	static int N;
	static char ori;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int[][] chk;
	
	static void dfs(int x, int y, char ori, char[][] arr) {
		for(int d=0; d<4; d++) {
			int nx = x+dx[d];
			int ny = y+dy[d];
			if(nx>=0 && nx<N && ny>=0 && ny<N && arr[nx][ny]==ori && chk[nx][ny]==0) {
				chk[nx][ny]=1;
				dfs(nx,ny,ori,arr);
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		char[][] arr = new char[N][N];
		cArr = new char[N][N];
		for(int i=0; i<N; i++) {
			String tmp = sc.next();
			for(int j=0; j<N; j++) {
				arr[i][j]=tmp.charAt(j);
			}
		}
		
		for(int i=0; i<arr.length; i++) {
			cArr[i]=arr[i].clone();
		}
		int cnt =0;
		int cCnt=0;
		chk = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				ori = arr[i][j];
				if(chk[i][j]==0) {
				chk[i][j]=1;
				dfs(i,j,ori,arr);
				cnt++;
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(cArr[i][j]=='R') cArr[i][j]='G';
			}
		}
		
		chk = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				ori = cArr[i][j];
				if(chk[i][j]==0) {
					chk[i][j]=1;
					dfs(i,j,ori,cArr);
					cCnt++;
				}
			}
		}
		System.out.println(cnt+" "+cCnt);
	}
}