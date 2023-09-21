package BAEK0921;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BAEK16401_RE {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		st = new StringTokenizer(bf.readLine());
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int idx;
		Arrays.sort(arr);
		
	
		
		
		//기본 설정
		int s = 1;
		int e = arr[arr.length-1];
		int mid = (s+e)/2; // 절반
		int cnt =0;
		int cklong = 0;


			while(s<=e) {
				mid = (s+e)/2;
				if(mid == 0)break;
				
				cnt =0;
				//System.out.println("s "+s+" e "+e+" mid "+mid);
				//나눈 갯수
				for(int i=0;i<arr.length;i++) {
					cnt+= arr[i]/mid;
				}
				
				if(cnt>=M) {
					cklong = mid;
					s = mid+1;
				}else {
					e = mid -1;
				}
				
			}

			System.out.println(cklong);
		}
		
		

	}
	

	

