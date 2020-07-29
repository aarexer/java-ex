package search;

public class BinarySearch {
    public static int search(final int[] arr, final int element) {
        if (arr.length == 0) {
            return -1;
        }

        return search(arr, element, 0, arr.length - 1);
    }

    private static int search(final int[] arr, final int element, final int left, final int right) {
        if (left > right) {
            return -1;
        }

        final int mid = (left + right) / 2;

        if (arr[mid] == element) {
            return mid;
        } else if (arr[mid] > element) {
            return search(arr, element, left, mid - 1);
        } else {
            return search(arr, element, mid + 1, right);
        }
    }

    public static int searchIter(final int[] arr, final int key) {
        if (arr.length == 0) {
            return -1;
        }

        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (arr[mid] == key) {
                return mid;
            } else if (arr[mid] < key) {
                left = mid + 1;
            } else if (arr[mid] > key) {
                right = mid - 1;
            }
        }

        return -1;
    }
}
