package DATE1106;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BAEK14002 {

	static int N;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		dpArr = new dp[N];
		dp(0);
		
		int tmp = 0;
		for(int i=0;i<N;i++) {
			if(dpArr[tmp].cnt <= dpArr[i].cnt) {
				tmp = i;
			}
		}
		sb.append(dpArr[tmp].cnt).append("\n");
		sb.append(dpArr[tmp].toString());
		
		//System.out.println(Arrays.toString(dpArr));
		System.out.println(sb);
		
	}
	
	static dp[] dpArr;
	public static void dp(int idx) {
		
	

		if(idx==N)return;
		dpArr[idx] = new dp(arr[idx],1);
		
		//System.out.println(Arrays.toString(dpArr));
		for(int i=0;i<idx;i++) {
			if(arr[idx] > dpArr[i].num && dpArr[i].cnt + 1 > dpArr[idx].cnt) {
					dpArr[idx].setDp(dpArr[i].cnt+1, arr[idx], dpArr[i].indexArr);
			}
		}
		
		dp(idx+1);
	}
	
	static class dp{
		ArrayList<Integer> indexArr;
		int num;
		int cnt;
		
		public dp(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
			indexArr = new ArrayList<>();
			indexArr.add(num);
		}
		public void setDp(int cnt, int num, ArrayList<Integer> idxArr) {
			this.num = num;
			this.cnt = cnt;
			indexArr.clear();
			for(int a : idxArr) {
				this.indexArr.add(a);
			}
			indexArr.add(num);
		}
		
//		public String toString() {
//		return num+" "+cnt+" "+indexArr.toString();
//		}

		@Override
		public String toString() {
			
			String str = indexArr.get(0)+"";
			for(int i = 1;i<indexArr.size();i++) {
				str += " "+indexArr.get(i);
			}
			return str;
		}
		
		
	}

}
