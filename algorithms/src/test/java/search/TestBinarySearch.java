package search;

import org.junit.Assert;
import org.junit.Test;

public class TestBinarySearch {
    @Test
    public void shouldReturnZeroIndexOnArrayWithOneElement() {
        final int[] arr = {1};
        Assert.assertEquals(BinarySearch.search(arr, 1), 0);
    }

    @Test
    public void shouldReturnZeroIndexOnArrayWithTwoElementsWhenSoughtIsFirst() {
        final int[] arr = {1, 2};
        Assert.assertEquals(BinarySearch.search(arr, 1), 0);
    }

    @Test
    public void shouldReturnNegativeOnEmptyArray() {
        final int[] arr = {};
        Assert.assertEquals(BinarySearch.search(arr, 1), -1);
    }

    @Test
    public void shouldReturnNegativeOnArrayWhichDoesntContainElementInRightSide() {
        final int[] arr = {1, 2, 3, 4, 5};
        Assert.assertEquals(BinarySearch.search(arr, 7), -1);
    }

    @Test
    public void shouldReturnNegativeOnArrayWhichDoesntContainElementInLeftSide() {
        final int[] arr = {1, 2, 3, 4, 5};
        Assert.assertEquals(BinarySearch.search(arr, 0), -1);
    }

    @Test
    public void shouldReturnSixOnArrayWithSoughtElementInLastPosition() {
        final int[] arr = {1, 2, 3, 4, 5, 6, 7};
        Assert.assertEquals(BinarySearch.search(arr, 7), 6);
    }

    @Test
    public void shouldReturnOneOnArrayWhichSoughtElementInSecondPosition() {
        final int[] arr = {1, 2, 3, 4, 5, 6, 7};
        Assert.assertEquals(BinarySearch.search(arr, 2), 1);
    }
}
