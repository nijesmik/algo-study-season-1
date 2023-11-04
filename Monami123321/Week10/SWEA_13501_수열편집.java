import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_13501_수열편집 {

	static class LinkedList {
		Node head, tail;
		int size;

		Node get(int index) {
			Node curr = head;
			if (curr == null) {
				return null;
			}

			for (int i = 0; i < index; i++) {
				curr = curr.next;
			}
			return curr;

		}

		void insertFirst(int data) {
			Node node = new Node();
			node.data = data;
			if (head == null) {
				head = node;
				size++;
				return;

			}
			node.next = head;
			head = node;
			size++;
			return;

		}

		void insert(int index, int data) {
			if (index == 0) {
				insertFirst(data);
				return;

			}
			Node prev = get(index - 1);
			Node now = new Node();
			now.data = data;
			now.next = prev.next;
			prev.next = now;
			size++;

		}

		void update(int index, int data) {
			Node now = get(index);
			now.data = data;
			return;

		}

		void delete(int index) {
			if (index == 0) {
				if (head == null) {
					return;
				} else {
					head = head.next;
					size--;
					return;
				}

			}
			Node prev = get(index - 1);
			prev.next = prev.next.next;
			size--;
			return;

		}
	}

	static class Node {
		int data;
		Node next;

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int testCases = Integer.parseInt(br.readLine());

		tc: for (int tc = 1; tc <= testCases; tc++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			LinkedList linkedList = new LinkedList();

			for (int i = 0; i < n; i++) {

				linkedList.insert(i, Integer.parseInt(st.nextToken()));

			}

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				switch (st.nextToken()) {
				case "I":
					linkedList.insert(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

					break;
				case "C":
					linkedList.update(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

					break;
				case "D":
					linkedList.delete(Integer.parseInt(st.nextToken()));

					break;

				default:
					break;
				}

			}
			if (linkedList.size - 1 < l) {
				sb.append("#").append(tc).append(" ").append(-1).append("\n");
				continue tc;
			}
			sb.append("#").append(tc).append(" ").append(linkedList.get(l).data).append("\n");

		}
		System.out.print(sb);
		br.close();
	}

}
