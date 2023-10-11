package DATE1010;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BAEK21276 {
	static int N;
	static String[] arr;
	public static void main(String[] args) throws Exception{
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(bf.readLine());
		arr = bf.readLine().split(" ");
		
		Arrays.sort(arr);
		
		int M = Integer.parseInt(bf.readLine());
		
		
		node[] tree = new node[N];
		
		Map<String, Integer> map = new HashMap<>();
		
		for(int i=0;i<N;i++) {
			tree[i] = new node(arr[i], i);
			map.put(arr[i],i);
		}
		int idx;
		String name;
		String par;
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(bf.readLine());
			name = st.nextToken();
			par = st.nextToken();
			
			
			//부모값을 더함
			idx = map.get(name);
			tree[idx].par.add(par);
			
			//자식값을 더함
			idx = map.get(par);
			tree[idx].chd.add(name);
			
		}
		
		ArrayList<String> anc = new ArrayList<>();
		
		for(node n: tree) {
			n.parCnt = n.par.size();
			if(n.parCnt ==0) {
				anc.add(n.me);
			}
		}
		
		//최종 조상의 갯수
		sb.append(anc.size()+"\n");
		//조상 이름
		for(String s : anc) {
			sb.append(s+" ");
		}
		sb.append("\n");
		
		
		//자식노드 찾기
		for(int c=0;c<anc.size();c++) {
			Deque<node> qu = new ArrayDeque<>();
			//어휴...
			qu.add(tree[map.get(anc.get(c))]);
			tree[map.get(anc.get(c))].vt = true;
			
			node tmp;
			while(!qu.isEmpty()) {
				
				tmp = qu.poll();
				
				//chd를 여기서만 해도 되지 않나 싶긴함.
				tree[tmp.idx].realChd = new ArrayList<>();
				
				for(String chl : tmp.chd) {
					idx = map.get(chl);
					
					//이미 방문했으면 지나치기
					if(tree[idx].vt)continue;
					
					tree[idx].parCnt--; 
					
					if(tree[idx].parCnt==0) {
						tree[tmp.idx].realChd.add(tree[idx].me);
						qu.add(tree[idx]);
						tree[idx].vt = true;
					}
					
				}
				
				Collections.sort(tree[tmp.idx].realChd);
				
			}
			

		}
		
		
		
		
		for(node n : tree) {
			sb.append(n.me+" "+n.realChd.size());
			if(n.realChd!=null) {
				
				for(String s: n.realChd) {
					sb.append(" "+s);
				}
			}
			sb.append("\n");
			//System.out.println(n.me+" "+n.realChd.size()+" "+n.realChd.toString());

		}
		
		System.out.println(sb);
		
		//System.out.println(Arrays.toString(tree));
		
	}
	
	static class node{
		ArrayList<String> par;
		ArrayList<String> chd;
		ArrayList<String> realChd;
		String me;
		int idx;
		boolean vt;
		
		int parCnt;
		
		node(String me, int idx){
			this.idx = idx;
			this.me = me;
			par = new ArrayList<>();
			chd = new ArrayList<>();
		}
		
		public String toString() {
			return "par "+this.par.toString()+" \n me "+this.me+"\n chd "+this.chd.toString()+"\n";
		}
	}

}
