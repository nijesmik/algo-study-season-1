class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        // 맨 뒷집부터 차례대로 배달하고, 수거도 같이
        // ㅡ> 맨 뒤 인덱스부터 순회, cap만큼 배달, 수거량을 늘리면서 확인 
        long ans = 0L;
        int deliverNow = 0;
        int pickupNow = 0;
        for(int i = n-1; i >= 0 ; i--) {
            int cnt = 0;
            while(deliveries[i]>deliverNow || pickups[i]>pickupNow) {
                cnt++;  // 각 인덱스의 수거량,배달량 넘어서야 다음으로 진행
                deliverNow += cap;
                pickupNow += cap;
            }
            // 할당량 채웠으면 해당 인덱스에서 발생한 거리 정답에 추가
            // 할당량만큼 현재 여유분 제거
            ans += (i+1)*cnt*2;
            deliverNow -= deliveries[i];
            pickupNow -= pickups[i];


        }
        return ans;


    }
}