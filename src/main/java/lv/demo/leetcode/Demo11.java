package lv.demo.leetcode;

/**
 * 双指针
 * <p>
 * 关键是理解思路，思路对了，代码非常简单
 */
public class Demo11 {

    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            maxArea = Math.max(maxArea, Math.min(height[left], height[right]) * (right - left));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
}
