package lv.demo.leetcode;

/**
 * 最长回文子串
 * <p>
 * 动态规划
 */
public class Demo5 {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("cbbd"));
    }

    private static String longestPalindrome(String s) {
        char[] chars = s.toCharArray();

        int start = 0, end = 0;
        boolean[][] array = new boolean[chars.length][chars.length];
        for (int i = 0; i < chars.length; i++) {
            array[i][i] = true;
        }
        for (int i = 0; i < chars.length - 1; i++) {
            array[i][i + 1] = chars[i] == chars[i + 1];
            if (array[i][i + 1]) {
                start = i;
                end = i + 1;
            }
        }

        for (int i = 2; i < chars.length; i++) {
            for (int j = 0; j < chars.length - i; j++) {
                //动态转移方程
                array[j][j + i] = array[j + 1][j + i - 1] && chars[j] == chars[j + i];
                if (array[j][j + i]) {
                    start = j;
                    end = j + i;
                }
            }
        }
        return s.substring(start, end + 1);
    }
}
