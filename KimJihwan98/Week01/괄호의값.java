package Stack;

import java.util.Scanner;
import java.util.Stack;

public class 괄호의값 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<Character> stack = new Stack<>();
        int answer = 0;
        int tmp = 1;
        char[] input = sc.next().toCharArray();
        for (int i = 0; i < input.length; i++) {
            if (input[i] == '(') {
                stack.add(input[i]);
                tmp *= 2;
            } else if (input[i] == '[') {
                stack.add(input[i]);
                tmp *= 3;
            } else if (input[i] == ')') {
                if (stack.isEmpty()) {
                    answer = 0;
                    break;
                }
                if (stack.peek() != '(') {
                    answer = 0;
                    break;
                } else if (input[i - 1] == '(') {
                    answer += tmp;
                    tmp /= 2;
                    stack.pop();
                } else {
                    tmp/= 2;
                    stack.pop();
                }
            } else if (input[i] == ']') {
                if (stack.isEmpty()) {
                    answer = 0;
                    break;
                }
                if (stack.peek() != '[') {
                    answer = 0;
                    break;
                } else if (input[i - 1] == '[') {
                    answer += tmp;
                    tmp /= 3;
                    stack.pop();
                } else {
                    tmp/= 3;
                    stack.pop();
                }
            }
        }
        if(!stack.isEmpty()) answer = 0;
        System.out.println(answer);

    }
}