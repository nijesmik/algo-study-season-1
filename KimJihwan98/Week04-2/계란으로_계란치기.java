package 백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Egg {
	int S;
	int W;
	
	public Egg(int s, int w) {
		S = s;
		W = w;
	}	
}
public class 계란으로_계란치기 {
	static List<Egg> eggs;
	static int max, N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		eggs = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			eggs.add(new Egg(a, b));
		}
		dfs(0,0);
		System.out.println(max);
		
	}
	public static void dfs(int idx, int count) {
		if(idx == N) {
			max = Math.max(count,  max);
			return;
		}
		
		if(eggs.get(idx).S <= 0 || count == N-1) {
			dfs(idx+1, count);
			return;
		}
		int cnt = count;
		for(int i = 0; i < N; i++) {
			if(i == idx) continue;
			if(eggs.get(i).S <= 0) continue;
			
			eggs.get(idx).S -= eggs.get(i).W;
			eggs.get(i).S -= eggs.get(idx).W;
			
			if(eggs.get(i).S <= 0) count++;
			if(eggs.get(idx).S <= 0) count++;
			
			dfs(idx+1, count);
			eggs.get(idx).S += eggs.get(i).W;
			eggs.get(i).S += eggs.get(idx).W;
			count = cnt;
		}
	}
}