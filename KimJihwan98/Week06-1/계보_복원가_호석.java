import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	String name;
	List<String> desc;
	int degree;
	public Node() {
	}
}
public class 계보_복원가_호석 {
	static StringBuilder sb;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Node[] names = new Node[N];
		for (int i = 0; i < N; i++) {
			names[i] = new Node();
		}
		String[] temp = br.readLine().split(" ");
		Arrays.sort(temp);
		HashMap<String, Integer> map = new HashMap<>();
		for(int i = 0; i < N; i++) {
			names[i].name = temp[i];
			map.put(temp[i], i);
			names[i].desc = new ArrayList<>();
		}
		
		int M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String descen = st.nextToken();
			String ances = st.nextToken();
			names[map.get(descen)].degree++;
			names[map.get(ances)].desc.add(descen);
		}
		
		
		// 위상정렬 이용해서 진입차수가 0이되면 부모이다를 할건데
		// 부모가 두개인 자식은?
		int cnt = 0;
		Queue<Node> q = new LinkedList<>();
		for(int i = 0; i < N; i++) {
			if(names[i].degree == 0) {
				cnt++;
				q.offer(names[i]);
			}
		}
        sb = new StringBuilder();
		sb.append(cnt + "\n");
		for(int i = 0; i < cnt; i++) {
			Node tmp = q.poll();
            sb.append(tmp.name + " ");
			q.offer(tmp);
		}
        sb.append("\n");
		while(!q.isEmpty()) {
			Node now = q.poll();
			int loopsize = now.desc.size();
			for(int i = 0; i < loopsize; i++) {
				String jason = now.desc.get(i);
				if(--names[map.get(jason)].degree != 0) {
					now.desc.remove(jason);
					loopsize--;
					i--;
				} else {
					q.offer(names[map.get(jason)]);
				}
			}
		}
		
		
		for(int i = 0; i < N; i++) {
            sb.append(names[i].name).append(" ").append(names[i].desc.size()).append(" ");
			Collections.sort(names[i].desc);
			names[i].desc.forEach(a->{
				sb.append(a).append(" ");
			});
            sb.append("\n");
		}
        System.out.print(sb);
	}
}
