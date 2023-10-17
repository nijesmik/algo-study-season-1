package pract;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class bj_위상정렬_계보 {
	static class Point implements Comparable<Point>{
		String name;
		ArrayList<String> list;

		Point(String name, ArrayList<String> list) {
			this.name = name;
			this.list = list;
			Collections.sort(this.list);
		}

		@Override
		public int compareTo(Point o) {
			return this.name.compareTo(o.name);
		}

	}
	
	static void topoSort() {
		Queue<String> Q = new LinkedList<>();
		
		while(!Q.isEmpty()) {
			
		}
	}
	
	static List<String> pList;
	static List<Integer> lList[];
	static int point;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		point = sc.nextInt();
		
		pList = new ArrayList<>();
		for(int i=0; i<point; i++) {
			
		}
	}
}
