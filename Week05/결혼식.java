package Week05;
import java.util.*;

public class 결혼식 {
	static class Friend {
		List<Integer> list = new ArrayList<>();
		boolean visit;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(), m = sc.nextInt();
		Friend[] friends = new Friend[n+1];
		for (int i = 0; i <= n; i++) {
			friends[i] = new Friend();
		}
		for (int i = 0; i < m; i++) {
			int a = sc.nextInt(), b = sc.nextInt();
			friends[a].list.add(b);
			friends[b].list.add(a);
		}
		int cnt = 0;
		friends[1].visit = true;
		Queue<Integer> q = new LinkedList<>(friends[1].list);
		for (int i = 0; i < 2; i++) {
			int size = q.size();
			while (size-- >0) {
				int idx = q.poll();
				if (friends[idx].visit) continue;
				friends[idx].visit = true;
				cnt++;
				for (int num : friends[idx].list) q.add(num);
			}
		}
		System.out.println(cnt);
	}
}