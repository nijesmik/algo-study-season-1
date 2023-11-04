class Solution {
	public int[] solution(String[] info, String[] query) {
//         int[][] infoChk = new int[info.length][5];
//         int[][] queryChk = new int[query.length][5];
//         int[] ans = new int[query.length];

//         for(int i = 0; i < infoChk.length; i++) {
//             String[] tmp = info[i].split(" ");
//             for(int j = 0; j < 4 ; j++) {
//                 infoChk[i][j] = tmp[j].charAt(0);

//             }
//             infoChk[i][4] = Integer.parseInt(tmp[4]);
//         }

//         for(int i = 0; i < queryChk.length; i++) {
//             String[] tmp = query[i].split(" ");
//             for(int j = 0; j <= 6 ; j+=2) {
//                 queryChk[i][j/2] = tmp[j].charAt(0);
//                 if(queryChk[i][j/2] == '-') {
//                     //queryChk[i][j/2] = (1 << 32) -1;
//                     queryChk[i][j/2] = 0;
//                 }

//             }
//             queryChk[i][4] = Integer.parseInt(tmp[7]);
//         }

//         for(int i = 0; i < queryChk.length; i++) {
//             info:for(int j = 0; j< infoChk.length; j++) {
//                 for(int k = 0; k<4;k++) {
//                     if((queryChk[i][k] ^ infoChk[j][k]) != 0 ) {
//                         continue info;
//                     }
//                 }
//                 if(infoChk[j][4] < queryChk[i][4]) {
//                     continue;
//                 }
//                 ans[i]++;   
//             }
//         }

		int[] ans = new int[query.length];

		for (int i = 0; i < query.length; i++) {
			String[] tmpQuery = query[i].split(" ");
			outer: for (int k = 0; k < info.length; k++) {
				String[] tmpInfo = info[k].split(" ");
				for (int j = 0; j <= 6; j += 2) {
					if (tmpQuery[j].equals("-")) {
						continue;
					}
					if (!tmpInfo[j / 2].equals(tmpQuery[j])) {
						continue outer;
					}

				}
				if (Integer.parseInt(tmpInfo[4]) < Integer.parseInt(tmpQuery[7])) {
					continue;
				}
				ans[i]++;

			}

		}
		return ans;
	}

}