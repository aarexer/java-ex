package search;

public class SequentialSearch {
    public static boolean search(int[] arr, int elem, int left, int right) {
        for (int i = left; i <= right; i++) {
            if (arr[i] == elem) {
                return true;
            }
        }
        return false;
    }
}
