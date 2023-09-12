package DATE0907;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BAEK15682_RE {

	static public ArrayList<ArrayList<Integer>> arr;

	static boolean[] chk;

	static int[] tmpArr;
	static int[] uArr;

	static int Ucnt;

	public static void main(String[] args) throws Exception {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		// N, R, U
		int N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		Ucnt = Integer.parseInt(st.nextToken());

		arr = new ArrayList<>();
		for (int i = 0; i < N + 1; i++) {
			arr.add(new ArrayList<>());
		}

		chk = new boolean[N + 1];
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(bf.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			arr.get(u).add(v);
			arr.get(v).add(u);
		}

		StringBuilder sb = new StringBuilder();

		uArr = new int[Ucnt];
		for (int i = 0; i < Ucnt; i++) {
			int tmp = Integer.parseInt(bf.readLine());
			uArr[i] = tmp;
		}
		chk[0] = true;
		tmpArr = new int[N + 1];
		setPar(R);

		for (int k : uArr) {
			sb.append(tmpArr[k] + "\n");
		}

		System.out.println(sb);
	}

	public static void setPar(int par) {

		chk[par] = true;
		tmpArr[par] = 1;
		for (int i = 0; i < arr.get(par).size(); i++) {

			// 0은 의미 없는 노드
			if (arr.get(par).get(i) == 0)
				continue;
			// 이미 부모로 쓰였는지 확인
			if (chk[arr.get(par).get(i)]) {
				// 안쓰는 간선이므로 set으로 0으로 바꿔줌
				arr.get(par).set(i, 0);
				continue;
			}

			// 간선이라면 밑으로 내려가기
			setPar(arr.get(par).get(i));
			tmpArr[par] += tmpArr[arr.get(par).get(i)];
		}

	}

}
