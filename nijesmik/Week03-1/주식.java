import java.util.Scanner;

public class 주식 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for (int t = 0; t < tc; t++) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            while (n-- > 0)
                arr[n] = sc.nextInt();
            int cmp = 0; long sum = 0;
            for (int i : arr) {
                if (i > cmp)
                    cmp = i;
                else
                    sum += cmp - i;
            }
            System.out.println(sum);
        }
    }
}