package nijesmik.Week10.순위_검색;

class Solution {
    int infosize, querysize;
    int[][] infomation;
    public int[] solution(String[] info, String[] query) {
        infosize = info.length;
        querysize = query.length;
        int[] answer = new int[querysize];
        infomation = getInfomation(info);
        for (int i = 0; i < querysize; i++) {
            answer[i] = getAnswer(query[i]);
        }
        return answer;
    }
    int getAnswer(String query) {
        int ans = 0;
        int[] condition = getCondition(query);
        for (int i = 0; i < infosize; i++) {
            if (compare(infomation[i], condition)) {
                ans++;
            }
        }
        return ans;
    }
    boolean compare(int[] info, int[] condition) {
        if (info[4] < condition[4]) {
            return false;
        }
        for (int i = 0; i < 4; i++) {
            if (condition[i] % info[i] > 0) {
                return false;
            }
        }
        return true;
    }

    int[] getCondition(String query) {
        int[] condition = new int[5];
        String[] tmp1 = query.split(" and ");
        for (int i = 0; i < 3; i++) {
            condition[i] = mapping(tmp1[i].charAt(0));
        }
        String[] tmp2 = tmp1[3].split(" ");
        condition[3] = mapping(tmp2[0].charAt(0));
        condition[4] = Integer.parseInt(tmp2[1]);
        return condition;
    }
    int mapping(char C) {
        if (C == '-')
            return 0;
        if (C == 'j' || C == 'b')
            return 2;
        if (C == 'p' || C == 'f' || C == 's')
            return 3;
        return 5; // C == 'c'
    }
    int[][] getInfomation(String[] info) {
        int[][] infomation = new int[infosize][5];
        for (int i = 0; i < infosize; i++) {
            String[] tmp = info[i].split(" ");
            for (int j = 0; j < 4; j++) {
                infomation[i][j] = mapping(tmp[j].charAt(0));
            }
            infomation[i][4] = Integer.parseInt(tmp[4]);
        }
        return infomation;
    }
}
