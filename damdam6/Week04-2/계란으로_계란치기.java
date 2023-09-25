package DATE0925;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BAEK16987_re {
	
	
	static int N;
	static int[] br;
	static int[][] eggs;
	
	public static void main(String[] args) throws Exception{
		
		StringBuilder sb = new StringBuilder();
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(bf.readLine());

		
		br = new int[N];
		eggs = new int[N][2]; // s, w
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(bf.readLine());
			eggs[i][0] = Integer.parseInt(st.nextToken());
			eggs[i][1] = Integer.parseInt(st.nextToken());
		}
		
		max = 0;
		cnt = 0;
		dfs(0);
		System.out.println(max);
		
	}
	
	static int max;
	static int cnt;
	public static void dfs(int idx) {
		
		if(idx==N) {
			//egg갯수 확인
			max = Math.max(max, cnt);
			return;
		}
		
		//이미 깨졌을 경우 -> 다음 걸로 넘어감
		if(br[idx] == 1) {
			dfs(idx+1);
			return;
		}
		
		
		//쥐고 있는 것 제외한 다른 것들이 다 깨졌을 경우
		if(cnt>=N-1) {
			dfs(idx+1);
			return;
		}
		
		
		for(int i=0;i<N;i++) {
			if(i!=idx && br[i]!=1) {
				
				eggs[i][0] -= eggs[idx][1];
				eggs[idx][0] -= eggs[i][1];
				
				boolean chki = false;
				boolean chkidx = false;
				
				if(eggs[i][0] < 0) {
					cnt++;
					br[i] = 1;
					chki = true;
				}
				if(eggs[idx][0] < 0) {
					cnt++;
					br[idx] = 1;
					chkidx = true;
				}
				
				dfs(idx+1);
				
				
				eggs[i][0] += eggs[idx][1];
				eggs[idx][0] += eggs[i][1];
				
				if(chki) {
					br[i] = 0;
					cnt--;
				}
				if(chkidx) {
					br[idx] = 0;
					cnt--;
				}
			}
			
		}
	}
	
}
