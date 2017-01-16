import flanagan.io.nio.FileUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FileUtilsDeleteDirNioTest {
    private final static String dirname = "test";
    private final static File directory = new File(dirname);
    private final static String dirnameNonWriteAccess = "test_write_access";
    private final static File nonWriteDirectory = new File(dirnameNonWriteAccess);

    @AfterClass
    public static void clearAll() {
        if (directory.exists()) {
            directory.delete();
        }

        if (nonWriteDirectory.exists()) {
            nonWriteDirectory.setWritable(true);
            nonWriteDirectory.delete();
        }
    }

    @Before
    public void createTestDir() {
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    @Test
    public void deleteFile() throws IOException {
        final String filename = "test.txt";
        final File file = new File(filename);

        Assert.assertEquals(true, file.createNewFile());
        Assert.assertEquals(true, file.exists());

        FileUtils.delete(filename);
        Assert.assertEquals(false, file.exists());
    }

    @Test
    public void deleteEmptyDirectory() throws IOException {
        FileUtils.delete(dirname);
        Assert.assertEquals(false, directory.exists());
    }

    @Test
    public void deleteDirectoryWithSingleFile() throws IOException {
        final File file = new File(String.format("%s/file.txt", dirname));

        Assert.assertEquals(true, file.createNewFile());
        Assert.assertEquals(true, file.exists());

        FileUtils.delete(dirname);
        Assert.assertEquals(false, directory.exists());
        Assert.assertEquals(false, file.exists());
    }

    @Test
    public void deleteDirectoryWithTwoFiles() throws IOException {
        final File file = new File(String.format("%s/file.test", dirname));
        final File file2 = new File(String.format("%s/file2.test", dirname));

        Assert.assertEquals(true, file.createNewFile());
        Assert.assertEquals(true, file.exists());

        Assert.assertEquals(true, file2.createNewFile());
        Assert.assertEquals(true, file2.exists());

        FileUtils.delete(dirname);
        Assert.assertEquals(false, directory.exists());
        Assert.assertEquals(false, file.exists());
        Assert.assertEquals(false, file2.exists());
    }

    @Test
    public void deleteDirectoryWithEmptyFlattenDirectory() throws IOException {
        final File flattenDir = new File(String.format("%s/dir", dirname));

        Assert.assertEquals(true, flattenDir.mkdir());
        Assert.assertEquals(true, flattenDir.exists());

        FileUtils.delete(dirname);
        Assert.assertEquals(false, directory.exists());
        Assert.assertEquals(false, flattenDir.exists());
    }

    @Test
    public void deleteDirectoryWithSingleFileAndNonEmptyDirectory() throws IOException {
        final File flattenDir = new File(String.format("%s/dir", dirname));
        final File file = new File(String.format("%s/dir/file.txt", dirname));

        Assert.assertEquals(true, flattenDir.mkdir());
        Assert.assertEquals(true, flattenDir.exists());

        Assert.assertEquals(true, file.createNewFile());
        Assert.assertEquals(true, file.exists());

        FileUtils.delete(dirname);
        Assert.assertEquals(false, directory.exists());
        Assert.assertEquals(false, flattenDir.exists());
        Assert.assertEquals(false, file.exists());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteDirectoryWithoutWriteAccess() throws IOException {
        Assert.assertEquals(true, nonWriteDirectory.mkdir());
        Assert.assertEquals(true, nonWriteDirectory.exists());
        Assert.assertEquals(true, nonWriteDirectory.setWritable(false));

        FileUtils.delete(dirnameNonWriteAccess);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteNonExistDirectory() throws IOException {
        final String dirname = "directory";
        final File nonExistDir = new File(dirname);

        Assert.assertEquals(false, nonExistDir.exists());

        FileUtils.delete(dirname);
    }
}
