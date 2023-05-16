package lv.demo.leetcode;

public class Demo209 {

    public static void main(String[] args) {
        System.out.println(minSubArrayLen(5, new int[]{2, 3, 1, 1, 1, 1, 1}));
    }

    public static int minSubArrayLen(int target, int[] nums) {
        int min = Integer.MAX_VALUE;
        int left = 0, right = 0, sum = nums[0];
        while (true) {
            if (sum < target) {
                if (++right < nums.length) {
                    sum += nums[right];
                    continue;
                } else {
                    break;
                }
            }
            if (left == right) {
                return 1;
            }
            min = Math.min(min, right - left + 1);
            left++;
            sum -= nums[left - 1];
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
