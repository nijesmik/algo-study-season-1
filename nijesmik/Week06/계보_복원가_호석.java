package nijesmik.Week06;
import java.util.*;

public class 계보_복원가_호석 {
	static class Person {
		int degree;
		List<String> descendants = new ArrayList<>();
		List<String> children = new ArrayList<>();
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String[] name = new String[n];
		Map<String, Person> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			name[i] = sc.next();
			map.put(name[i], new Person());
		}
		int m = sc.nextInt();
		for (int i = 0; i < m; i++) {
			String child = sc.next();
			String parent = sc.next();
			map.get(child).degree += 1;
			map.get(parent).descendants.add(child);
		}
		Arrays.sort(name);
		Queue<String> q = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			if (map.get(name[i]).degree == 0) q.add(name[i]);
		}
		int size = q.size();
		sb.append(size+"\n");
		while (!q.isEmpty()) {
			String curname = q.poll();
			if (size-- > 0) sb.append(curname+" ");
			Person cur = map.get(curname);
			for (String next : cur.descendants) {
				Person descendant = map.get(next);
				descendant.degree -= 1;
				if (descendant.degree == 0) {
					cur.children.add(next);
					q.add(next);
				}
			}
		}
		sb.append("\n");
		for (String str : name) {
			sb.append(str+" ");
			List<String> list = map.get(str).children;
			Collections.sort(list);
			sb.append(list.size()+" ");
			for (String elem : list) {
				sb.append(elem+" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
}