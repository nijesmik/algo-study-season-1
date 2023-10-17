package nijesmik.Week07;
import java.util.*;

public class 뱀 {
	static class Command {
		int time, rotate;
		Command (int t, char r) {
			time = t;
			if (r == 'L') rotate = 3;
			else rotate = 1;
		}
	}
	static Deque<int[]> snake;
	static int n;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt()+1;
		boolean[][] apple = new boolean[n][n];
		int k = sc.nextInt();
		for (int i = 0; i < k; i++) {
			apple[sc.nextInt()][sc.nextInt()] = true;
		}
		Queue<Command> cmds = new LinkedList<>();
		int l = sc.nextInt();
		for (int i = 0; i < l; i++) {
			cmds.add(new Command(sc.nextInt(), sc.next().charAt(0)));
		}
		snake = new ArrayDeque<>();
		snake.add(new int[]{1, 1});
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		int time = 0, dir = 1;
		while (true) {
			if (!cmds.isEmpty() && cmds.peek().time == time) {
				dir = (dir + cmds.poll().rotate) % 4;
			}
			time++;
			int[] head = snake.peekLast();
			int nr = head[0]+dr[dir], nc = head[1]+dc[dir];
			if (!check(nr, nc)) break;
			if (!apple[nr][nc]) snake.poll();
			else apple[nr][nc] = false;
			snake.add(new int[]{nr, nc});
		}
		System.out.println(time);
	}
	static boolean check(int r, int c) {
		if (r < 1 || r >= n || c < 1 || c >= n)
			return false;
		for (int[] body : snake) {
			if (r == body[0] && c == body[1])
				return false;
		}
		return true;
	}
}