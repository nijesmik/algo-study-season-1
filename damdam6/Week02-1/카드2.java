package DATE0904;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BAEK2164 {


	public static void main(String[] args) throws NumberFormatException, IOException {
		Queue<Integer> qu = new ArrayDeque<>();
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(bf.readLine());
		
		//qu에 삽입
		int cnt = 1;
		while(cnt <=N) {
			qu.add(cnt++);
		}
		
		while(qu.size()>1) {
			qu.poll();
			qu.add(qu.poll());
		}
		
		System.out.println(qu.poll());
	}
}
