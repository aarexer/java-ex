package sorting;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class TestBubbleSort {
    //// FIXME: 07.06.16 write normal tests
    @Test
    public void testBubbleSort() {
        int[] tst = {1, 0, 3, 8, -2};
        BubbleSort.bubbleSort(tst);
        Assert.assertEquals("Array must be [-2, 0, 1, 3, 8]: ", Arrays.toString(tst), "[-2, 0, 1, 3, 8]");
    }

    @Test
    public void testBubbleSortWithCounterFlag() {
        int[] tst = {1, 0, 3, 8, -2};
        BubbleSort.bubbleSortWithCounterFlag(tst);
        Assert.assertEquals("Array must be [-2, 0, 1, 3, 8]: ", Arrays.toString(tst), "[-2, 0, 1, 3, 8]");
    }

    @Test
    public void testBubbleSortWithFlag() {
        int[] tst = {1, 0, 3, 8, -2};
        BubbleSort.bubbleSortWithFlag(tst);
        Assert.assertEquals("Array must be [-2, 0, 1, 3, 8]: ", Arrays.toString(tst), "[-2, 0, 1, 3, 8]");
    }
}
