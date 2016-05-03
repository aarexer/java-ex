package sorting;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;


public class TestMergeSort
{
    @Test
    public void testMergeSort()
    {
        int [] tst = {1, 0, 3, 8, -2};
        Assert.assertEquals("Array must be [-2, 0, 1, 3, 8]: ", Arrays.toString(MergeSort.mergesort(tst, 0, tst.length)) , "[-2, 0, 1, 3, 8]");
    }
    @Test
    public void testMergeSort2()
    {
        int [] tst = {};
        Assert.assertEquals("Array must be []: ", Arrays.toString(MergeSort.mergesort(tst, 0, tst.length)) , "[]");
    }
    @Test
    public void testMergeSort3()
    {
        int [] tst = {1, 2, 0};
        Assert.assertEquals("Array must be [0, 1, 2]: ", Arrays.toString(MergeSort.mergesort(tst, 0, tst.length)) , "[0, 1, 2]");
    }

}