import java.util.*;

public class 음악프로그램 {
    static class Singer {
        int degree;
        List<Integer> next = new ArrayList<>();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int singer = sc.nextInt(), pd = sc.nextInt();
        Singer[] singers = new Singer[singer+1];
        for (int i = 1; i <= singer; i++) {
            singers[i] = new Singer();
        }
        for (int i = 0; i < pd; i++) {
            int n = sc.nextInt();
            int prev = sc.nextInt();
            for (int j = 0; j < n-1; j++) {
                int cur = sc.nextInt();
                singers[cur].degree += 1;
                singers[prev].next.add(cur);
                prev = cur;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= singer; i++) {
            if (singers[i].degree == 0) q.add(i);
        }
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
        while (!q.isEmpty()) {
            int now = q.poll();
			sb.append(now+"\n");
			cnt++;
            for (int i : singers[now].next) {
                singers[i].degree -= 1;
                if (singers[i].degree == 0) q.add(i);
            }
        }
		if (cnt == singer) System.out.print(sb);
		else System.out.println(0);
    }
}