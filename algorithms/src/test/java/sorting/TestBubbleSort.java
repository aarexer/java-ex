package sorting;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class TestBubbleSort {
    @Test
    public void testWithUnsortedArray() {
        final int[] tst = {1, 0, 3, 8, -2};
        BubbleSort.bubbleSort(tst);
        Assert.assertEquals("Array must be [-2, 0, 1, 3, 8]: ", Arrays.toString(tst), "[-2, 0, 1, 3, 8]");
    }

    @Test
    public void testWithSortedArray() {
        final int[] tst = {1, 2, 3, 4, 5};
        BubbleSort.bubbleSort(tst);
        Assert.assertEquals("Array must be [1, 2, 3, 4, 5]: ", Arrays.toString(tst), "[1, 2, 3, 4, 5]");
    }

    @Test
    public void testWithUnsortedArrayWithFlag() {
        final int[] tst = {1, 0, 3, 8, -2};
        BubbleSort.bubbleSortWithFlag(tst);
        Assert.assertEquals("Array must be [-2, 0, 1, 3, 8]: ", Arrays.toString(tst), "[-2, 0, 1, 3, 8]");
    }

    @Test
    public void testWithUnsortedArrayWithTwoElementsWithFlag() {
        final int[] tst = {1, 2, 3, 0, 5};
        BubbleSort.bubbleSortWithFlag(tst);
        Assert.assertEquals("Array must be [1, 2, 3, 0, 5]: ", Arrays.toString(tst), "[0, 1, 2, 3, 5]");
    }
}
