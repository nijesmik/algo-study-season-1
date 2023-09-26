import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int K;
	static int[] arr;
	static int[] ans;
	static void dfs(int L,int S) {
		if(L==6) {
			for(int x: ans) System.out.print(x+" ");
			System.out.println();
		}else {
			for(int i=S; i<K; i++) {
					ans[L]=arr[i];
					dfs(L+1,i+1);
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			K = sc.nextInt();
		if(K==0) break;
			arr = new int[K];
			ans = new int[6];
			for(int i=0; i<K;i++) {
				arr[i]=sc.nextInt();
			}
			
			dfs(0,0);
			System.out.println();
		}
	}
}
