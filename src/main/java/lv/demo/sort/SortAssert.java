package lv.demo.sort;

public class SortAssert {

    public static boolean isSort(int[] arr) {
        int prev = arr[0];
        for (int a : arr) {
            if (a < prev) {
                return false;
            }
            prev = a;
        }
        return true;
    }
}
