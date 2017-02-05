package utils.io.streams;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ZipSeqInputStreamTest {
    private ZipSeqInputStream zip = new ZipSeqInputStream(getClass().getResourceAsStream("arch_1.zip"));
    private ZipSeqInputStream zip2 = new ZipSeqInputStream(getClass().getResourceAsStream("arch_2.zip"));

    private List<String> getAllStringsFromZip(ZipSeqInputStream zip) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(zip));
        final List<String> lst = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            lst.add(line);
        }

        return lst;
    }

    @Test
    public void readZipWithOneFileInside() throws Exception {
        final List<String> lst = getAllStringsFromZip(zip);

        Assert.assertEquals(lst.size(), 3);
        Assert.assertEquals(lst.get(0), "First file");
        Assert.assertEquals(lst.get(1), "hello 1");
        Assert.assertEquals(lst.get(2), "world 1");
    }

    @Test
    public void readZipWithThreeFilesInside() throws Exception {
        final List<String> lst = getAllStringsFromZip(zip2);

        Assert.assertEquals(lst.size(), 16);
        Assert.assertEquals(lst.get(0), "First file");
        Assert.assertEquals(lst.get(1), "hello 1");
        Assert.assertEquals(lst.get(2), "world 1");
        Assert.assertEquals(lst.get(3), "Second file");
        Assert.assertEquals(lst.get(4), "hello 2");
        Assert.assertEquals(lst.get(5), "world 2");
        Assert.assertEquals(lst.get(6), "get 2");
        Assert.assertEquals(lst.get(7), "out 2");
        Assert.assertEquals(lst.get(8), "test 2");
        Assert.assertEquals(lst.get(9), "Third file");
        Assert.assertEquals(lst.get(10), "hello 3");
        Assert.assertEquals(lst.get(11), "world 3");
        Assert.assertEquals(lst.get(12), "like 3");
        Assert.assertEquals(lst.get(13), "a boss 3");
        Assert.assertEquals(lst.get(14), "nothing 3");
        Assert.assertEquals(lst.get(15), "to speak 3");
    }
}
