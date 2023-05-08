package lv.demo.leetcode;

/**
 * 无重复字符的最长子串
 * <p>
 * 双指针
 */
public class Demo3 {

    public static void main(String[] args) {

    }

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] cs = s.toCharArray();
        int maxLength = 1;
        int m = 0, n = 1;

        while (n < cs.length) {
            int findIndex = find(cs, m, n, cs[n]);
            if (findIndex != -1) {
                //find
                maxLength = Math.max(maxLength, n - m);
                m = findIndex + 1;
            }
            n++;
        }
        return Math.max(maxLength, n - m);
    }

    private static int find(char[] cs, int startInclusive, int endExclusive, char c) {
        for (int i = startInclusive; i < endExclusive; i++) {
            if (cs[i] == c) {
                return i;
            }
        }
        return -1;
    }
}
