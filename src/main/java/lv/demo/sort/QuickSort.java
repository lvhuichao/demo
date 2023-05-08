package lv.demo.sort;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.RandomUtils;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            int[] arr = new int[RandomUtils.nextInt(20, 50)];
            //init
            for (int j = 0; j < arr.length; j++) {
                arr[j] = RandomUtils.nextInt(0, 50);
            }

            sort(arr, 0, arr.length - 1);
            Preconditions.checkArgument(SortAssert.isSort(arr));
            System.out.println(Arrays.toString(arr));
        }
    }

    private static void sort(int[] arr, int start, int end) {
        int s = start, e = end;

        int x = arr[start];
        while (start < end) {
            while (arr[end] >= x && start < end) {
                end--;
            }
            if (start < end) {
                swap(arr, start, end);
            }
            while (arr[start] <= x && start < end) {
                start++;
            }
            if (start < end) {
                swap(arr, start, end);
            }
        }
        if (s < start - 1) {
            sort(arr, s, start - 1);
        }
        if (start + 1 < e) {
            sort(arr, start + 1, e);
        }
    }

    private static void swap(int[] arr, int m, int n) {
        int temp = arr[m];
        arr[m] = arr[n];
        arr[n] = temp;
    }
}
