package parsers.csv;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVFileReader {
    private static final String[] EMPTY_ARRAY = new String[0];

    private final BufferedReader reader;
    private final String delimiter;
    private final List<String> lines;

    public CSVFileReader(File file, String delimiter) throws IOException {
        this.reader = new BufferedReader(new FileReader(file));
        this.delimiter = delimiter;
        this.lines = new ArrayList<>();

        getLines();
    }

    private List<String> getLines() throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }

        return lines;
    }

    public String[] getParsedLine(int index) {
        final String line = lines.get(index);
        String[] pl;

        if (line != null) {
            pl = line.split(delimiter);
        } else {
            pl = CSVFileReader.EMPTY_ARRAY;
        }

        return pl;
    }

    public void close() throws IOException {
        reader.close();
    }
}
