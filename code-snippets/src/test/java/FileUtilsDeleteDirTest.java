import flanagan.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FileUtilsDeleteDirTest {

    @Test
    public void deleteFileInDirectory() throws IOException {
        final String name = "test.test";
        final File file = new File(name);

        Assert.assertEquals(true, file.createNewFile());
        Assert.assertEquals(true, file.exists());

        FileUtils.delete(name);
        Assert.assertEquals(false, file.exists());
    }

    @Test
    public void deleteEmptyDirectory() throws IOException {
        final String name = "test";
        final File dir = new File(name);

        Assert.assertEquals(true, dir.mkdir());
        Assert.assertEquals(true, dir.exists());

        FileUtils.delete(name);
        Assert.assertEquals(false, dir.exists());
    }

    @Test
    public void deleteDirectoryWithSingleFile() throws IOException {
        final String name = "test";
        final File dir = new File(name);
        final File file = new File(String.format("%s/file.test", name));

        Assert.assertEquals(true, dir.mkdir());
        Assert.assertEquals(true, dir.exists());

        Assert.assertEquals(true, file.createNewFile());
        Assert.assertEquals(true, file.exists());

        FileUtils.delete(name);
        Assert.assertEquals(false, dir.exists());
        Assert.assertEquals(false, file.exists());
    }

    @Test
    public void deleteDirectoryWithTwoFiles() throws IOException {
        final String name = "test";
        final File dir = new File(name);
        final File file = new File(String.format("%s/file.test", name));
        final File file2 = new File(String.format("%s/file2.test", name));

        Assert.assertEquals(true, dir.mkdir());
        Assert.assertEquals(true, dir.exists());

        Assert.assertEquals(true, file.createNewFile());
        Assert.assertEquals(true, file.exists());

        Assert.assertEquals(true, file2.createNewFile());
        Assert.assertEquals(true, file2.exists());

        FileUtils.delete(name);
        Assert.assertEquals(false, dir.exists());
        Assert.assertEquals(false, file.exists());
    }

    @Test
    public void deleteDirectoryWithSingleFileAndEmptyDirectory() throws IOException {
        final String name = "test";
        final File dir = new File(name);
        final File flattenDir = new File(String.format("%s/dir", name));

        Assert.assertEquals(true, dir.mkdir());
        Assert.assertEquals(true, dir.exists());

        Assert.assertEquals(true, flattenDir.mkdir());
        Assert.assertEquals(true, flattenDir.exists());

        FileUtils.delete(name);
        Assert.assertEquals(false, dir.exists());
        Assert.assertEquals(false, flattenDir.exists());
    }

    @Test
    public void deleteDirectoryWithSingleFileAndNonEmptyDirectory() throws IOException {
        final String name = "test";
        final File dir = new File(name);
        final File flattenDir = new File(String.format("%s/dir", name));
        final File file = new File(String.format("%s/dir/file.test", name));

        Assert.assertEquals(true, dir.mkdir());
        Assert.assertEquals(true, dir.exists());

        Assert.assertEquals(true, flattenDir.mkdir());
        Assert.assertEquals(true, flattenDir.exists());

        Assert.assertEquals(true, file.createNewFile());
        Assert.assertEquals(true, file.exists());

        FileUtils.delete(name);
        Assert.assertEquals(false, dir.exists());
        Assert.assertEquals(false, flattenDir.exists());
        Assert.assertEquals(false, file.exists());
    }
}
