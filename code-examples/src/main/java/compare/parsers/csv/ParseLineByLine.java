package compare.parsers.csv;

import java.io.IOException;

public interface ParseLineByLine {
    long parseLineByLine() throws IOException;

    long parseLineByLineWithBuffer(int bufferSize) throws IOException;
}
