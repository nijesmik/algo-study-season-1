package DATE0831;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BAEK2504 {

	public static void main(String[] args) throws Exception {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		char[] input = bf.readLine().toCharArray();
//		한 쌍의 괄호로만 이루어진 ‘()’와 ‘[]’는 올바른 괄호열이다.
//		만일 X가 올바른 괄호열이면 ‘(X)’이나 ‘[X]’도 모두 올바른 괄호열이 된다.
//		X와 Y 모두 올바른 괄호열이라면 이들을 결합한 XY도 올바른 괄호열이 된다.

		Stack<Character> stk = new Stack<>();
		Map<Character, Character> map = new HashMap<>();
		map.put(')', '(');
		map.put(']', '[');

		Map<Character, Integer> cal = new HashMap<>();
		cal.put('(', 2);
		cal.put('[', 3);

		int sum = 0;
		int i = 0;
		int[] arr = new int[16];
		//System.out.println(Arrays.toString(input));
		//System.out.println(Arrays.toString(arr));
		//System.out.println(stk.toString());
		
		while (i < input.length) {
			
			if (stk.isEmpty()) {
				
				stk.push(input[i++]);
				//System.out.println("비어서 집어넣음");
			} else if (input[i] == '(' || input[i] == '[') {
				stk.push(input[i++]);
				//System.out.println("여는 괄호");
			} else {
				//System.out.println("닫는 괄호");
				if (stk.peek() != map.get(input[i])) {
					sum = 0;
					break;
				}
				//System.out.println("------닫는 괄호의 while");
				while (!stk.isEmpty()&&stk.peek() == map.get(input[i])) {
					if(arr[stk.size()]==0) {
						arr[stk.size()-1] +=cal.get(stk.pop());
					}else {
						arr[stk.size()-1] += arr[stk.size()]*cal.get(stk.pop());
						arr[stk.size()+1]=0;
					}
					i++;
					//System.out.println();
					//System.out.println(Arrays.toString(arr));
					//System.out.println(stk.toString());
					//System.out.println();
				}
				//System.out.println("------------여기 while종료");
			}

			//System.out.println(Arrays.toString(arr));
			//System.out.println(stk.toString());
		}
		sum = arr[0];

		if (!stk.isEmpty()) {
			sum = 0;
		}
		System.out.println(sum);
	}

}
