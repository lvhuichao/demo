package lv.demo.leetcode;

public class Demo1143 {

    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null || text1.isEmpty() || text2.isEmpty()) {
            return 0;
        }
        char[] cs1 = text1.toCharArray();
        char[] cs2 = text2.toCharArray();

        int[][] dp = new int[cs1.length][cs2.length];
        dp[0][0] = cs1[0] == cs2[0] ? 1 : 0;

        for (int i = 1; i < cs2.length; i++) {
            dp[0][i] = dp[0][i - 1] == 1 || cs1[0] == cs2[i] ? 1 : 0;
        }
        for (int j = 1; j < cs1.length; j++) {
            dp[j][0] = dp[j - 1][0] == 1 || cs1[j] == cs2[0] ? 1 : 0;
        }
        //
        for (int i = 1; i < cs1.length; i++) {
            for (int j = 1; j < cs2.length; j++) {
                if (cs1[i] == cs2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[cs1.length - 1][cs2.length - 1];
    }
}
