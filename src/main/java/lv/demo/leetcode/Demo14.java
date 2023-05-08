package lv.demo.leetcode;

/**
 * 最长公共前缀
 */
public class Demo14 {

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }

        int i = 0;
        while (true) {
            for (String s : strs) {
                if (s == null || s.length() <= i || s.charAt(i) != strs[0].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
            i++;
        }
    }
}
