import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] profit;
	static int[][] map;
	static int N, answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		map = new int[N][2];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			map[i][0] = Integer.parseInt(st.nextToken());
			map[i][1] = Integer.parseInt(st.nextToken());
		}
		profit = new int[N+1];
		answer = get_profit();
		System.out.println(answer);
	}
	
	static int get_profit() {
		int max = 0;
		for(int i = 0; i <= N; i++) {
			if(max < profit[i]) max = profit[i];
			if(i==N) break;
			int a = map[i][0];
			int b = map[i][1];
			if(a+i > N) continue;
			
			if(profit[i+a] < max + b) profit[i+a] = max + b;
		}
		return max;
	}
}
