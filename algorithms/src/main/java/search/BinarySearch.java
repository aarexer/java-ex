package search;

public class BinarySearch {
    public static boolean search(int[] arr, int element) {
        if (arr.length == 0) {
            return false;
        }
        return search(arr, element, 0, arr.length - 1);
    }

    private static boolean search(int[] arr, int element, int left, int right) {
        if (left > right) {
            return false;
        }

        final int mid = (left + right) / 2;

        if (arr[mid] == element) {
            return true;
        } else if (arr[mid] > element) {
            return search(arr, element, left, right = mid - 1);
        } else {
            return search(arr, element, mid + 1, right);
        }
    }
}
