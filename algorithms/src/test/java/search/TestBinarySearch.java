package search;

import org.junit.Assert;
import org.junit.Test;

public class TestBinarySearch {
    @Test
    public void shouldReturnTrueOnArrayWithOneElement() {
        final int[] arr = {1};
        Assert.assertEquals(BinarySearch.search(arr, 1), true);
    }

    @Test
    public void shouldReturnTrueOnArrayWithTwoElements() {
        final int[] arr = {1, 2};
        Assert.assertEquals(BinarySearch.search(arr, 1), true);
    }

    @Test
    public void shouldReturnFalseOnEmptyArray() {
        final int[] arr = {};
        Assert.assertEquals(BinarySearch.search(arr, 1), false);
    }

    @Test
    public void shouldReturnFalseOnArrayWhichDoesntContainElementInRightSide() {
        final int[] arr = {1, 2, 3, 4, 5};
        Assert.assertEquals(BinarySearch.search(arr, 7), false);
    }

    @Test
    public void shouldReturnFalseOnArrayWhichDoesntContainElementInLeftSide() {
        final int[] arr = {1, 2, 3, 4, 5};
        Assert.assertEquals(BinarySearch.search(arr, 0), false);
    }

    @Test
    public void shouldReturnTrueOnArrayWhichContainsElementInRightSide() {
        final int[] arr = {1, 2, 3, 4, 5, 6, 7};
        Assert.assertEquals(BinarySearch.search(arr, 7), true);
    }

    @Test
    public void shouldReturnTrueOnArrayWhichContainsElementInLeftSide() {
        final int[] arr = {1, 2, 3, 4, 5, 6, 7};
        Assert.assertEquals(BinarySearch.search(arr, 2), true);
    }
}
