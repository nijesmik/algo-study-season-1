import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class 백준_21276_계보복원가호석 {
	static class Node implements Comparable<Node> {
		String name;
		int depth; // 시조로부터 얼마나 먼지
		List<Node> ascendants;
		List<Node> descendants;

		public Node(String name) {
			super();
			this.name = name;
			this.depth = 0;
			this.ascendants = new ArrayList<>();
			this.descendants = new ArrayList<>();
		}

		@Override
		public int compareTo(Node o) { // 사전순 정렬을 위해 name으로

			return this.name.compareTo(o.name);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine()); // 마을 인원 n

		StringTokenizer st = new StringTokenizer(br.readLine());

		Node[] nodes = new Node[n];
		HashMap<String, Integer> nodesIndex = new HashMap<>(); // 이름 : 인덱스 찾기
		for (int i = 0; i < nodes.length; i++) {
			String name = st.nextToken();
			nodes[i] = new Node(name);
			nodesIndex.put(name, i);

		}

		int m = Integer.parseInt(br.readLine()); // 기억하고 있는 정보의 갯수 m

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			String child = st.nextToken();
			String parent = st.nextToken();

			int childIndex = nodesIndex.get(child);
			int parentIndex = nodesIndex.get(parent);

			nodes[childIndex].ascendants.add(nodes[parentIndex]); // 자식 노드의 조상리스트에 parent노드 추가
			nodes[childIndex].depth++; // 시조로부터 한 깊이 추가
			nodes[parentIndex].descendants.add(nodes[childIndex]); // 조상 노드의 자손리스트에 child노드 추가

		}

		int familyNum = 0; // 시조의 수 ㅡ> 깊이가 0인 노드
		List<String> familyName = new ArrayList<>(); // 시조의 이름 저장할 리스트
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i].depth == 0) {
				familyNum++;
				familyName.add(nodes[i].name);

			}

		}

		Collections.sort(familyName); // 사전순 정렬
		sb.append(familyNum).append("\n"); // 가문의 개수 k 출력
		for (String tmp : familyName) {
			sb.append(tmp).append(" ");

		}
		sb.append("\n"); // 시조 이름 사전순으로 출력

		Arrays.sort(nodes);
		for (int i = 0; i < nodes.length; i++) {
			sb.append(nodes[i].name).append(" "); // 사전순 정리된 노드 이름
			int childNum = 0; // 바로 아래 자손 노드 수
			List<String> childName = new ArrayList<>(); // 자식노드 이름 정리 리스트
			for (int j = 0; j < nodes[i].descendants.size(); j++) { // i번째 노드의 j번째 자손 체크
				if (nodes[i].depth == nodes[i].descendants.get(j).depth - 1) { // 깊이 차이가 1이면 부모-자식
					childNum++;
					childName.add(nodes[i].descendants.get(j).name);
				}

			}
			sb.append(childNum).append(" "); // 자식 수 출력
			if (childNum == 0) {
				sb.append("\n");
				continue;
			}

			Collections.sort(childName); // 자식 이름 사전순으로 정렬

			for (String tmp : childName) {
				sb.append(tmp).append(" "); // 자식 이름 순서대로 출력
			}
			sb.append("\n");

		}
		
		System.out.println(sb);

		br.close();
	}

}
