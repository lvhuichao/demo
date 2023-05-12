package lv.demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 * 时间复杂度：O(n2)
 * 排序+外层遍历+内层双指针
 * <p>
 * 关键是想明白内层为什么可以用双指针。
 */
public class Demo15 {

    public List<List<Integer>> threeSum(int[] arr) {
        Arrays.sort(arr);

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            if (i != 0 && arr[i] == arr[i - 1]) {
                continue;
            }

            int start = i + 1, end = arr.length - 1;
            while (start < end) {
                if (start != i + 1 && arr[start] == arr[start - 1]) {
                    start++;
                    continue;
                }

                int sum = arr[i] + arr[start] + arr[end];
                if (sum == 0) {
                    List<Integer> one = new ArrayList<>(3);
                    one.add(arr[i]);
                    one.add(arr[start]);
                    one.add(arr[end]);
                    result.add(one);

                    start++;
                    end--;
                }

                if (sum > 0) {
                    end--;
                }
                if (sum < 0) {
                    start++;
                }
            }
        }
        return result;
    }
}
