package ssafybackup;

import java.util.ArrayList;
import java.util.Scanner;

public class 가장_긴_증가하는_부분_수열4 {

    static int[] seq;
    static Integer[] dp;
    static ArrayList<Integer> ans;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        seq = new int[N];
        dp = new Integer[N];
        ans = new ArrayList<Integer>();

        for (int i = 0; i < N; i++) {
            seq[i] = sc.nextInt();
        }

        for (int i = 0; i < N; i++) {
            LIS(i);
        }

        int max = dp[0];
        int maxIndex = 0;
        for (int i = 1; i < N; i++) {
            if (max < dp[i]) {
                max = dp[i];
                maxIndex = i;
            }
        }

        getLIS(maxIndex, seq[maxIndex]);
        System.out.println(max);
        for(int x: ans) System.out.print(x+" ");
    }

    static int LIS(int N) {

        if (dp[N] == null) {
            dp[N] = 1;    

            for (int i = N - 1; i >= 0; i--) {
                if (seq[i] < seq[N]) {
                    dp[N] = Math.max(dp[N], LIS(i) + 1);
                }
            }
        }
        return dp[N];
    }

    static void getLIS(int index, int value) {
        if (dp[index] == 1) {
            ans.add(value);
            return;
        }
        for (int i = index - 1; i >= 0; i--) {
            if (dp[index] - dp[i] == 1 && seq[i] < value) {
                getLIS(i, seq[i]);
                ans.add(value);
                break;
            }
        }
    }
}
