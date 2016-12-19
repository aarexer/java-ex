package sorting;

public class BubbleSort {
    /**
     * Simple bubble sort example.
     * @param arr array with elements.
     */
    public static void bubbleSort(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * Simple bubble sort, but with flag for performance optimization.
     * @param arr array with elements.
     */
    public static void bubbleSortWithFlag(int[] arr) {
        int len = arr.length;
        boolean flag;

        for (int i = 0; i < len - 1; i++) {
            flag = false;
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    flag = true;
                }
            }
            if (!flag) break;
        }
    }
}
