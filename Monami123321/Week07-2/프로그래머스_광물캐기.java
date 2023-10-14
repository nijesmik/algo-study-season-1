import java.util.*;

class Solution { // 끝까지 칠 수 있는 경우, 없는 경우 따로 처리해줘야 했음
	public int solution(int[] picks, String[] minerals) {
		int calNum = minerals.length / 5; // 5개씩 끊어서 피로도를 미리 계산
		if (minerals.length % 5 != 0) {
			calNum++;
		}
		boolean flag = false; // 끝까지 칠 수 있는지 없는지 확인하는 플래그
		if (picks[0] + picks[1] + picks[2] < calNum)
			flag = true;

		int[][] cal = new int[calNum][3]; // 다이아 철 돌 순서로 피로도 계산한 2차원 배열 만들기
		int diaTired = 0;
		int ironTired = 0;
		int stoneTired = 0;
		int tmpIndex = 0;
		for (int i = 0; i < minerals.length; i++) {
			switch (minerals[i]) {
			case "diamond":
				diaTired += 1;
				ironTired += 5;
				stoneTired += 25;
				break;

			case "iron":
				diaTired += 1;
				ironTired += 1;
				stoneTired += 5;
				break;

			case "stone":
				diaTired += 1;
				ironTired += 1;
				stoneTired += 1;
				break;
			}

			if (i % 5 == 4) {
				cal[tmpIndex][0] = diaTired;
				cal[tmpIndex][1] = ironTired;
				cal[tmpIndex++][2] = stoneTired;

				diaTired = 0;
				ironTired = 0;
				stoneTired = 0;
				continue;

			}
			if (i == minerals.length - 1) {
				cal[tmpIndex][0] = diaTired;
				cal[tmpIndex][1] = ironTired;
				cal[tmpIndex++][2] = stoneTired;
				// 마지막인덱스 ㅡ> 5의 배수 아니어도 저장
			}

		} // 피로도 계산한 cal 배열 입력 끝
		if (flag) { // 끝까지 칠 수 없는 경우, 마지막 것을 빼고 정렬해야 함
			Arrays.sort(cal, 0, calNum - 1, (a, b) -> {
				if (a[2] == b[2]) {
					return b[1] - a[1];
				} else {
					return b[2] - a[2];

				}
			});

		} else {
			Arrays.sort(cal, (a, b) -> {
				if (a[2] == b[2]) {
					return b[1] - a[1];
				} else {
					return b[2] - a[2];

				}
			}); // 돌로 캤을 때 피로도가 큰 순으로 정렬, 같으면 철 기준으로

		}

		// tmpIndex가 cal 원소 수 기억하고 있음
		int ans = 0;
		int now = 0;
		while (true) {

			if (now == tmpIndex || (picks[0] == 0 && picks[1] == 0 && picks[2] == 0)) {
				break;
			}
			if (picks[0] > 0) {
				ans += cal[now++][0];
				picks[0]--;
				continue;
			}
			if (picks[1] > 0) {
				ans += cal[now++][1];
				picks[1]--;
				continue;
			}
			if (picks[2] > 0) {
				ans += cal[now++][2];
				picks[2]--;
				continue;
			}

		}

		return ans;
	}
}