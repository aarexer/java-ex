package compare.parsers.csv;

import java.io.IOException;

public interface ParserTesting {
    void parseLineByLineWithoutBuffer() throws IOException;
    void parseLineByLineWithBuffer() throws IOException;
}
