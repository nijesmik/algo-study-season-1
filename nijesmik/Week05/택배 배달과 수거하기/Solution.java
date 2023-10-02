import java.util.*;

class Solution {
    class House {
        int dist;
        int box;
        House(int d, int b) {
            dist = d;
            box = b;
        }
    }
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long ans = 0;
        Stack<House> del = new Stack<House>();
        Stack<House> pick = new Stack<House>();
        for (int i = 0; i < n; i++) {
            if (deliveries[i] != 0) del.push(new House(i+1, deliveries[i]));
            if (pickups[i] != 0) pick.push(new House(i+1, pickups[i]));
        }
        while (!del.isEmpty() || !pick.isEmpty()) {
            int delDist = del.isEmpty() ? 0 : del.peek().dist;
            int pickDist = pick.isEmpty() ? 0 : pick.peek().dist;
            ans += Math.max(delDist, pickDist) * 2;
            delivery(del, cap);
            delivery(pick, cap);
        }
        return ans;
    }
    void delivery(Stack<House> stack, int cap) {
        while (!stack.isEmpty() && cap > 0) {
            House node = stack.pop();
            cap -= node.box;
            if (cap < 0) {
                node.box = -cap;
                stack.push(node);
            }
        }
    }
}