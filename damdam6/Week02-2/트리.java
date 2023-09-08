import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static public ArrayList<LinkedList<Integer>> arr;
	static public int cnt;
	static public int[] parArr;
	static public int delN;

	public static void main(String[] args) throws Exception {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bf.readLine());

		StringTokenizer st = new StringTokenizer(bf.readLine());

		arr = new ArrayList<>();

		parArr = new int[N];
		for (int i = 0; i < N; i++) {
			arr.add(new LinkedList<>());
		}
		int root = 0;

		// 자료 배치
		for (int i = 0; i < N; i++) {
			int par = Integer.parseInt(st.nextToken());
			parArr[i] = par;
			if (par == -1) {
				root = i;
				continue;
			}
			// ArrayList배열 값이 부모-> 자식 i칸을 true로
			arr.get(par).add(i);
		}

//		System.out.println(Arrays.toString(parArr));
//		System.out.println("----");
//		for(int i=0;i<arr.size();i++) {
//			System.out.println(i+"="+arr.get(i).toString());
//		}
//		System.out.println("----");
		cnt = 0;
		 delN = Integer.parseInt(bf.readLine());
		delChd(delN);
	 
		//parArr[delN] <- 부모노드
		
		cntLeaf(root);

		System.out.println(cnt-1);
	}

	// 삭제 메서드

	public static void delChd(int par) {
	
		while(arr.get(par).size() != 0) {
			delChd(arr.get(par).pollLast());
		}

		

	}

	public static void cntLeaf(int par) {
		if (arr.get(par).size() == 0) {
			cnt++;
		}

		int idx = arr.get(par).size()-1;
		while(idx>= 0) {
			if(arr.get(par).getLast()==delN &&arr.get(par).size()==1) {
				cnt++;
			}
			cntLeaf(arr.get(par).get(idx--));
		}
	}

}
